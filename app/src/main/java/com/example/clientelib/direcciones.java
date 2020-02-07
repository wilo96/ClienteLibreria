package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class direcciones extends AppCompatActivity {

    private EditText calleprin, callesec, numcasa, prov, ciud;
    private Button nuevabtn;
    private ServicioGet sg;


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
                ventana();
            }
        });
    }
    private void ventana(){
        Intent i = new Intent(this, Productos.class);
        startActivityForResult(i, 0);
    }
}
