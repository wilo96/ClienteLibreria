package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class directarj extends AppCompatActivity{
    ServicioGet sg = new ServicioGet();
    ArrayList<String> direc, tarj;
    ArrayAdapter<String> direcciones, tarjetas;
    Spinner listadirec, listatarj;
    String direccion, tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directarj);
        listadirec=(Spinner)findViewById(R.id.spinner_Direccion);
        listatarj=(Spinner)findViewById(R.id.spinner_Tarjeta);
        listadirec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spinner_Direccion:
                        //Almaceno el nombre de la fruta seleccionada
                        direccion = direc.get(position);

                        Toast.makeText(getApplicationContext(), "Direccion: " + direccion, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listatarj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(parent.getId()){
                    case R.id.spinner_Tarjeta:
                        tarjeta = tarj.get(position);

                        Toast.makeText(getApplicationContext(), "Tarjeta: " + tarjeta, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        direc= new ArrayList<String>();
        direc=sg.getDirecciones();
        tarj=new ArrayList<String>();
        tarj=sg.getTarjetas();
        direcciones= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, direc);
        listadirec.setAdapter(direcciones);
        tarjetas= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, tarj);
        listatarj.setAdapter(tarjetas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.prefactmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    int aceptar=0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.prefacitem:
                SharedPreferences prefs = getSharedPreferences("datosfin",   Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("direc", direccion);
                editor.putString("tarj", tarjeta);
                editor.commit();
                ventana();
                /*
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Aviso");
                dialogo1.setMessage("¿Desea confirmar la compra?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // sg.carrito(id.getText().toString(),cantidad.getText().toString(),precio.getText().toString(),descuento.getText().toString(),subtotal.getText().toString());

                        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
                        String cedu = prefs.getString("cedula","");
                        sg.precompraConf(cedu,1,1);


                        Toast.makeText(getApplicationContext(), cedu, Toast.LENGTH_LONG).show();
                        ventana();

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogo1.show();*/
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void ventana(){
        Intent i = new Intent(this, prefactura.class);
        startActivityForResult(i, 0);
    }

}
