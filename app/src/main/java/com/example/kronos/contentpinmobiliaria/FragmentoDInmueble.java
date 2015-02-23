package com.example.kronos.contentpinmobiliaria;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class FragmentoDInmueble extends Fragment {

    TextView tvTituloDetalle;
    ImageView iv;
    View v;

    public FragmentoDInmueble() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragmento_dinmueble, container, false);
        iv =(ImageView) v.findViewById(R.id.imageView);
        return v;
    }

    public void setText(String s){
        this.tvTituloDetalle = (TextView)v.findViewById(R.id.tvTituloDetalle);
        this.tvTituloDetalle.setText(s);
    }
}
