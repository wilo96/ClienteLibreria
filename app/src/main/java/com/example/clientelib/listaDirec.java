package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class listaDirec extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ServicioGet sg= new ServicioGet();
    String direcc;
    JSONObject jsonOb;
    String pro[];
    URL url;
    Bitmap bit;
    private  int num=1;
    Items it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direc);

        ArrayList<itemsDirec> listaItems = new ArrayList<>();
        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        //listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
        // listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));

        direcc=sg.getDataDirec();
        System.out.println(direcc);
        try {
            JSONArray jsonArray = new JSONArray(direcc);

            for(int i = 0; i< jsonArray.length();i++){
                jsonOb = jsonArray.getJSONObject(i);

                listaItems.add(new itemsDirec(jsonOb.getString("callePrinc"), jsonOb.getString("calleSec"), jsonOb.getString("numCasa"),jsonOb.getString("provincia"),jsonOb.getString("ciudad"),jsonOb.getString("codigo")));
                //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.listaDirecciones);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdaptadorDire(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
