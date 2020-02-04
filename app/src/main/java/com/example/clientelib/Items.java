package com.example.clientelib;

import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuInflater;

public class Items {
    private Bitmap mImageResources;
    private String titulo;
    private String autor;
    private String descuento;
    private String stock, id, precio, editorial;
    private String cantidad;


    public Items(Bitmap imageResource, String titulos, String autors, String ids, String editorials, String precios, String stocks, String descuentos)
    {
        mImageResources=imageResource;
        titulo =titulos;
        autor =autors;
        id=ids;
        editorial=editorials;
        precio=precios;
        stock=stocks;
        descuento=descuentos;

    }

    public Bitmap getmImageResources() {
        return mImageResources;
    }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor()
        {
            return autor;
        }
        public String getId(){
            return id;
        }
        public String getDescuento(){
            return descuento;
        }
        public String getStock(){
            return stock;
        }
        public String getPrecio(){
            return precio;
        }
        public String getEditorial(){
            return editorial;
        }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
