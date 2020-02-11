package com.example.clientelib;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
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
    AdaptadorBusca prodbus = new AdaptadorBusca();
    Adaptador prod = new Adaptador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        carrito=(ImageButton)findViewById(R.id.añadirbtn);
        like=(ImageButton)findViewById(R.id.likebtn);
        compartir=(ImageButton)findViewById(R.id.compartirbtn);



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
        Toast.makeText(getApplicationContext(),"Bienvenid@ "+usuario,Toast.LENGTH_LONG).show();

        //System.out.println(principal.cargarPreferencias()); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
        final String cedu = prefs.getString("cedulas","");
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.Precompra:
                try {
                    Intent i = new Intent(this, precompra.class);
                    i.putExtra("ce",cedu);
                    startActivityForResult(i, 0);
                }catch(Exception e){

                }
                return true;
            case R.id.Direccion:
                try {
                    Intent i = new Intent(this, direcciones.class);
                    i.putExtra("ce",cedu);
                    startActivityForResult(i, 0);
                }catch(Exception e){

                }
                return true;
            case R.id.Tarjeta:
                try {
                    Intent i = new Intent(this, Tarjetas.class);
                    i.putExtra("ce",cedu);
                    startActivityForResult(i, 0);
                }catch(Exception e){

                }
                return true;
            case R.id.Buscarmenu:
                try{

                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    final EditText edittext = new EditText(getApplicationContext());
                    alert.setMessage("Libro a Buscar");
                    alert.setTitle("Buscar Libro");

                    alert.setView(edittext);

                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //What ever you want to do with the value
                            Editable busco = edittext.getText();
                            //OR
                            String buscarl = edittext.getText().toString();
                            sg.getBuscarLibro(Integer.parseInt(buscarl));
                            Intent i = new Intent(getApplicationContext(),ResBuscar.class);
                            i.putExtra("id",buscarl);
                            startActivity(i);

                        }
                    });

                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });

                    alert.show();
                }catch(Exception e){

                }
                return true;
            case R.id.salirm:
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getFromSharedPreferences(String usuario) {
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences sharedPref=getBaseContext().getSharedPreferences("guardarUsuario", MODE_PRIVATE);
        //String user= sharedPref.getString("usuario","");
        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
        String user = prefs.getString("nombre","");
        return user;
    }



}