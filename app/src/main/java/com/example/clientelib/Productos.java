package com.example.clientelib;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button carrito;
    private ImageButton like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        ArrayList<Items> listaItems = new ArrayList<>();
        listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
        listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));

        recyclerView = findViewById(R.id.listaProds);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adaptador(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        carrito=(Button)findViewById(R.id.añadirtxt);
        like=(ImageButton)findViewById(R.id.likebtn);

    }


}