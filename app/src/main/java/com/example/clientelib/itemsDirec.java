package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class itemsDirec extends AppCompatActivity {

    private String Callep, Calles, Numcasa, Provi, Ciuda;
    private String id;

    public itemsDirec( String callep, String calles, String numcasa, String prov, String ciudad, String idcars)
    {
        Calles = calles;
        Callep=callep;
        Numcasa=numcasa;
        Provi=prov;
        Ciuda=ciudad;
        id= idcars;

    }
    public String getCallep(){return Callep;}
    public String getCalles() {
        return Calles;
    }
    public String getId(){
        return id;
    }
    public String getNumcasa(){
        return Numcasa;
    }
    public String getProvi(){ return Provi;}
    public String getCiuda(){ return Ciuda;}
}
