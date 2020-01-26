package com.example.clientelib;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button carrito;
    private ImageButton like;
    private MainActivity principal;
    ServicioGet sg = new ServicioGet();
    String prods;
    JSONObject jsonOb;
    String pro[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        ArrayList<Items> listaItems = new ArrayList<>();

        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        //listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));
        prods=sg.getDataProds();
        try {
            JSONArray jsonArray = new JSONArray(prods);

            for(int i = 0; i< jsonArray.length();i++){
                jsonOb = jsonArray.getJSONObject(i);
                Log.d("Salida del Select ",jsonOb.optString("titulo"));
                System.out.println("------------------------- "+jsonOb.toString());
                listaItems.add(new Items(jsonOb.optInt("imagen"),jsonOb.optString("titulo"),jsonOb.optString("autor")));
                //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //pro=prods.split("}");
        //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
        recyclerView = findViewById(R.id.listaProds);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adaptador(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        carrito=(Button)findViewById(R.id.añadirtxt);
        like=(ImageButton)findViewById(R.id.likebtn);
        String usuario= getFromSharedPreferences("usuario");
        System.out.println("usuario de shared preferences"+usuario);
        Toast.makeText(getApplicationContext(),"Bienvenid@"+usuario,Toast.LENGTH_LONG).show();

    }
    private String getFromSharedPreferences(String usuario) {
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref=getBaseContext().getSharedPreferences("guardarUsuario", MODE_PRIVATE);
        String user= sharedPref.getString("usuario","");
        System.out.println(user);
        return user;
    }


}