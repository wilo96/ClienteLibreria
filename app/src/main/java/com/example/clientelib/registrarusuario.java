package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrarusuario extends AppCompatActivity {
    EditText cedula, nombres, telefono, correo, contrasena, repcontrasena;
    Button registro;
    ServicioGet sg= new ServicioGet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarusuario);
        cedula=(EditText)findViewById(R.id.cedregtxt);
        nombres=(EditText)findViewById(R.id.nombresregtxt);
        telefono=(EditText)findViewById(R.id.telfregtxt);
        correo=(EditText)findViewById(R.id.correoregtxt);
        contrasena=(EditText)findViewById(R.id.contraseñregatxt);
        repcontrasena=(EditText)findViewById(R.id.repitcontraseñatxt);
        registro=(Button)findViewById(R.id.registrobtnreg);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contrasena.getText().toString().equals(repcontrasena.getText().toString())) {
                    sg.nuevoUsu(cedula.getText().toString(), nombres.getText().toString(), telefono.getText().toString(), contrasena.getText().toString(), correo.getText().toString());
                    Toast.makeText(getApplicationContext(),"Usuario Ingresado",Toast.LENGTH_LONG).show();
                    contrasena.setText("");
                    repcontrasena.setText("");
                    nombres.setText("");
                    correo.setText("");
                    telefono.setText("");
                    cedula.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"La contraseña esta incorrecta",Toast.LENGTH_LONG).show();
                    contrasena.setText("");
                    repcontrasena.setText("");
                }

            }
        });

    }
}
