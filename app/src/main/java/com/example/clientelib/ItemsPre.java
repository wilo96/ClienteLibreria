package com.example.clientelib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;

public class ItemsPre extends AppCompatActivity {
    private String titulo;
    private String id, precio, descuento, subtotal, idcar;
    private String cantidad;

    public ItemsPre( String titulos, String ids, String precios, String cantidades, String descuentos, String subtotales, String idcars)
    {
        titulo =titulos;
        id=ids;
        precio=precios;
        cantidad=cantidades;
        descuento=descuentos;
        subtotal=subtotales;
        idcar = idcars;

    }
    public String getCantidad(){return cantidad;}
    public String getTitulo() {
        return titulo;
    }
    public String getId(){
        return id;
    }
    public String getPrecio(){
        return precio;
    }
    public String getDescuento(){ return descuento;}
    public String getIdcar(){ return idcar;}
    public String getSubtotal(){
        return subtotal;
    }

}
