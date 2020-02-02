package com.example.clientelib;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Productos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton carrito;
    private ImageButton like;
    private ImageButton compartir;
    private TextView cantidad;
    private MainActivity principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        carrito=(ImageButton)findViewById(R.id.añadirbtn);
        like=(ImageButton)findViewById(R.id.likebtn);
        compartir=(ImageButton)findViewById(R.id.compartirbtn);
        car = (FloatingActionButton)findViewById(R.id.floating);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), precompra.class);
                startActivityForResult(i,0);
            }
        });
        cantidad=(TextView)findViewById(R.id.cantidadtxt);

        ArrayList<Items> listaItems = new ArrayList<>();
        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        //listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
       // listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));

        recyclerView = findViewById(R.id.listaProds);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adaptador(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        String usuario= getFromSharedPreferences("usuario");
        System.out.println("usuario de shared preferences"+usuario);
        Toast.makeText(getApplicationContext(),"Bienvenid@"+usuario,Toast.LENGTH_LONG).show();
        //System.out.println(principal.cargarPreferencias()); 
    }

    private String getFromSharedPreferences(String usuario) {
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref=getBaseContext().getSharedPreferences("guardarUsuario", MODE_PRIVATE);
        String user= sharedPref.getString("usuario","");
        return user;
    }


}