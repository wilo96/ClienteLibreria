package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class itemsTarjeta extends AppCompatActivity {
    private String id, numtarj,identif, empresa;

    public itemsTarjeta( String numt, String ident, String empresas,String idcars)
    {
        id= idcars;
        numtarj=numt;
        identif=ident;
        empresa=empresas;


    }
    public String getIdentif(){return identif;}
    public String getNumtarj() {
        return numtarj;
    }
    public String getId(){
        return id;
    }
    public String getEmpresa(){return empresa;}
}
