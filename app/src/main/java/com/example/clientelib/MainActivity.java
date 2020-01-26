package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button iniciar;
    private Button registrarse;
    private EditText correo;
    private EditText contrasena;
    Servicio servicio;
    ServicioGet sg = new ServicioGet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = (Button) findViewById(R.id.iniciobtn);
        registrarse = (Button) findViewById(R.id.registrobtn);
        correo = (EditText) findViewById(R.id.corrertxt);
        contrasena = (EditText) findViewById(R.id.contraseñatxt);
        final String correos= correo.getText().toString();
        final String contra= contrasena.getText().toString();
        String p1="correos="+correos;
        String p2="conttra="+contra;
        //servicio= new Servicio(this,"http://192.168.100.17:8080/proyectoInterciclo/srv/Usuarios/buscarusu",correos, contra);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(correo.getText().toString()+"   "+contrasena.getText().toString());
                sg.getData( correo.getText().toString(),contrasena.getText().toString());
                String values=sg.getData( correo.getText().toString(),contrasena.getText().toString());
                if(values != null)
                {
                    //Toast.makeText(getApplicationContext(),values,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(),Productos.class);
                    startActivityForResult(i,0);

                }
                else{

                }


                //servicio.execute();

            }
        });

    }
}

