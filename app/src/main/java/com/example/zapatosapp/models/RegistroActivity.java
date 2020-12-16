package com.example.zapatosapp.models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zapatosapp.R;
import com.example.zapatosapp.models.CalzadoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistroActivity extends AppCompatActivity {

    private EditText et_registro_size, et_registro_brand;
    private Button btn_registro_guardar;
    private CalzadoModel model;
    protected FirebaseFirestore db;
    private String TAG = "LFNOT";
    final private String collection = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        db = FirebaseFirestore.getInstance();
        et_registro_brand = findViewById(R.id.et_registro_brand);
        et_registro_size = findViewById(R.id.et_registro_size);
        btn_registro_guardar = findViewById(R.id.btn_registro_guardar);

        btn_registro_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand, size;
                size = et_registro_size.getText().toString();
                brand = et_registro_brand.getText().toString();

                model = new CalzadoModel(size, brand);


                db.collection(collection)
                        .add(model)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_LONG).show();
                                goToMain();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(getApplicationContext(), "No se guardó correctamente: " + e.getMessage() , Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

    }

    private void goToMain(){
        Intent listar = new Intent(this, MainActivity.class);
        startActivity(listar);
    }
}
