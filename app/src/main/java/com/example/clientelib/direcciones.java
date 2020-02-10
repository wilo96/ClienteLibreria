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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class direcciones extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText calleprin, callesec, numcasa, prov, ciud;
    private Button nuevabtn;
    private ServicioGet sg;
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
        setContentView(R.layout.activity_direcciones);
        calleprin = (EditText) findViewById(R.id.calleptxt);
        callesec = (EditText) findViewById(R.id.callestxt);
        numcasa = (EditText) findViewById(R.id.numcasatxt);
        ciud = (EditText) findViewById(R.id.ciudtxt);
        prov = (EditText) findViewById(R.id.provtxt);
        nuevabtn=(Button)findViewById(R.id.nuevadirecbtn);
        sg= new ServicioGet();


        nuevabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
                final String usu = prefs.getString("cedula","");
                sg.nuevaDirec(calleprin.getText().toString(),callesec.getText().toString(), numcasa.getText().toString(),ciud.getText().toString(),prov.getText().toString(),usu);
                Toast.makeText(getApplicationContext(),"Dirección Insertada", Toast.LENGTH_LONG).show();
                calleprin.setText("");
                callesec.setText("");
                numcasa.setText("");
                ciud.setText("");
                prov.setText("");
                //ventana();
            }
        });





    }
    private void ventana(){
        Intent i = new Intent(this, Productos.class);
        startActivityForResult(i, 0);
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
                    Intent i = new Intent(this, listaDirec.class);
                    startActivityForResult(i, 0);
                }catch(Exception e){

                }
                return true;
            case R.id.salirdr:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
