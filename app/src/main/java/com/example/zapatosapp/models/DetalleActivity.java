package com.example.zapatosapp.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.zapatosapp.R;
import com.example.zapatosapp.models.CalzadoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class DetalleActivity extends AppCompatActivity {

    private EditText et_detalle_size, et_detalle_brand;
    private Button  btn_upgrade;
    protected FirebaseFirestore db;
    private String TAG = "LFNOT";
    final private String collection = "notes";
    private CalzadoModel model;
    private DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        db = FirebaseFirestore.getInstance();

        et_detalle_size = findViewById(R.id.et_registro_size);
        et_detalle_brand = findViewById(R.id.et_detalle_brand);
        btn_upgrade= findViewById(R.id.btn_upgrade);


        String id = getIntent().getStringExtra("id");
        if (id != null) {
            et_detalle_size.setText(id);
            updateUI(id);
        } else {
            et_detalle_size.setText("No recibimos nada");
        }



    }




    private void updateUI(String id){
        documentReference = db.collection(collection).document(id);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            model = document.toObject(CalzadoModel.class);
                            model.setFbId(document.getId());
                            if(model != null){
                                et_detalle_size.setText(model.getSize());
                                et_detalle_brand.setText(model.getBrand());
                            }
                            //updateModel(model);

                        }
                    }

                });
        btn_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateModel(model);
            }
        });

    }


    private void updateModel(CalzadoModel model){
        model.setSize(et_detalle_size.getText().toString());
        model.setBrand(et_detalle_brand.getText().toString());

        String cont = et_detalle_size.getText().toString();
        model.setBrand(cont);
        documentReference = db.collection(collection).document(model.getFbId());
        documentReference.set(model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "Dio");
                        }
                    }
                });

    }
}
