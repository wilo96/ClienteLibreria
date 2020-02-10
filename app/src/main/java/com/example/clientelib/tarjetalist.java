package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class tarjetalist extends AppCompatActivity {
    private ServicioGet sg= new ServicioGet();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String tarjets;
    JSONObject jsonOb;
    String pro[];
    URL url;
    Bitmap bit;
    private  int num=1;
    Items it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetalist);

        ArrayList<itemsTarjeta> listaItems = new ArrayList<>();
        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        //listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
        // listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));
        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
        String cedu = prefs.getString("cedula","");
        tarjets=sg.getDataTarj(cedu);
        try {
            JSONArray jsonArray = new JSONArray(tarjets);

            for(int i = 0; i< jsonArray.length();i++){
                jsonOb = jsonArray.getJSONObject(i);
                //Log.d("Salida del Select ",jsonOb.optString("titulo"));

                //System.out.println("------------------------- "+jsonOb.toString());
                listaItems.add(new itemsTarjeta(jsonOb.getString("numTarj"),jsonOb.getString("codVerif"),jsonOb.getString("empTarj"),jsonOb.getString("codigo")));
                //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.listatarj);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdaptadorTarj(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
