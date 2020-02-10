package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Tarjetas extends AppCompatActivity {
private EditText numerotarj, numident, empresa;
private Button nuevabtn;
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
        setContentView(R.layout.activity_tarjetas);
        numerotarj = (EditText)findViewById(R.id.tarjtxt);
        numident=(EditText)findViewById(R.id.numsegtxt);
        empresa=(EditText)findViewById(R.id.empresatxt);
        nuevabtn=(Button)findViewById(R.id.nuevatarjbtn);
        nuevabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
                final String usu = prefs.getString("cedula","");
                sg.nuevaTarj(numerotarj.getText().toString(),numident.getText().toString(),empresa.getText().toString(),usu);
                numerotarj.setText("");
                numident.setText("");
                empresa.setText("");

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listamenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.listar:
                try {
                    Intent i = new Intent(this, tarjetalist.class);
                    startActivityForResult(i, 0);
                }catch(Exception e){

                }
                return true;
            case R.id.salirli:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
