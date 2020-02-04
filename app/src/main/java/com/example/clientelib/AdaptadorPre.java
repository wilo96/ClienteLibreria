package com.example.clientelib;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorPre extends RecyclerView.Adapter<AdaptadorPre.ViewHolder> {
    int num=1;
    private ArrayList<ItemsPre> listaItems;




    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EditText cantidad;
        public TextView titulo,id,precio, subtotal;
        public ImageButton eliminar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo =itemView.findViewById(R.id.nombre);
            id=itemView.findViewById(R.id.idtxt2);
            precio=itemView.findViewById(R.id.preciotxt2);
            eliminar=itemView.findViewById(R.id.elimbtn);
            cantidad=itemView.findViewById(R.id.cantidadtxt2);
            subtotal=itemView.findViewById(R.id.subtotal);

        }
    }

    public AdaptadorPre(ArrayList<ItemsPre> listItems){
        listaItems=listItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_pre, parent, false);
        AdaptadorPre.ViewHolder vh= new AdaptadorPre.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ItemsPre curentItem = listaItems.get(position);

        holder.titulo.setText(curentItem.getTitulo());
        holder.precio.setText(String.valueOf(Double.valueOf(curentItem.getPrecio()) - (Double.valueOf(curentItem.getPrecio())*(Double.valueOf(curentItem.getDescuento())/100))));
        holder.id.setText(curentItem.getId());
        holder.cantidad.setText(curentItem.getCantidad());
        holder.subtotal.setText(String.valueOf(Double.valueOf(Double.valueOf(curentItem.getPrecio()) - (Double.valueOf(curentItem.getPrecio())*(Double.valueOf(curentItem.getDescuento())/100)))*Double.valueOf(curentItem.getCantidad())));

    }




    @Override
    public int getItemCount() {
        return listaItems.size();
    }


}
