package com.example.zapatosapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.zapatosapp.R;
import com.example.zapatosapp.models.CalzadoModel;
import com.example.zapatosapp.models.CalzadoModel;

import java.util.ArrayList;
import java.util.Random;

public class CalzadoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CalzadoModel> list;

    public CalzadoAdapter(Context context, ArrayList<CalzadoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CalzadoModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.nota_item, viewGroup, false);
        }

        TextView item_size, item_brand, item_fecha;
        ImageView item_imagen;
        String size, brand;

        item_imagen = view.findViewById(R.id.item_imagen);
        item_size = view.findViewById(R.id.item_size);
        item_brand = view.findViewById(R.id.item_brand);
        item_fecha = view.findViewById(R.id.item_fecha);

        //Forma 1
        size = list.get(i).getSize().trim();
        Log.i("INFONA", size);
        if(size.equals("")){
            size = "(Sin talla)";
        }
        item_size.setText(size);

        //Forma 2
        item_size.setText(list.get(i).getSize());

        item_brand.setText(list.get(i).getBrand());

        item_fecha.setText("10/10/20");



        //Forma dinámica
        // Iconos
        String[] iconos = { "ic_accessibility_new_white_18dp", "ic_account_circle_white_18dp", "ic_android_white_18dp", "ic_pregnant_woman_white_18dp"};
        Random r =new Random();
        int randomNumber = r.nextInt(iconos.length);

        int icono = view.getResources().getIdentifier("com.example.myapplication:drawable/" + iconos[randomNumber], null, null);
        item_imagen.setImageResource(icono);

        // Colores
        String[] colores = {"#ff33b5e5", "#ffff8800", "#ff99cc00", "#ffff4444", "#ff0099cc", "#ff669900", "#ffaa66cc", "#ffffbb33"};
        randomNumber = r.nextInt(colores.length);
        view.setBackgroundColor(Color.parseColor(colores[randomNumber]));


        //Forma estática
        //Icono 1
        //Drawable img = ResourcesCompat.getDrawable(view.getResources(), R.drawable.ic_android_white_18dp, null);
        //item_imagen.setImageDrawable(img);

        //Icono 2
        //item_imagen.setImageResource(R.drawable.ic_pregnant_woman_white_18dp);

        //Color
        //view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_light));

        return view;
    }
}

