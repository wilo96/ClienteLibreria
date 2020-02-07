package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tarjetas extends AppCompatActivity {
private EditText numerotarj, numident, empresa;
private Button nuevabtn;
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

            }
        });
    }
}
