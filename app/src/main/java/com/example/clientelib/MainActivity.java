package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button iniciar;
    private Button registrarse;
    private EditText correo;
    private EditText contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar = (Button) findViewById(R.id.iniciobtn);
        registrarse = (Button) findViewById(R.id.registrobtn);
        correo = (EditText) findViewById(R.id.corrertxt);
        contrasena = (EditText) findViewById(R.id.contraseñatxt);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Productos.class);
                startActivityForResult(i,0);
            }
        });
    }
}

