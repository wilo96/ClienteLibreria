package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tarjetas extends AppCompatActivity {
private EditText numerotarj, numident, empresa;
private Button nuevabtn;
private ServicioGet sg= new ServicioGet();
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
}
