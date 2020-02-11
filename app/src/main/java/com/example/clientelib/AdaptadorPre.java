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
    private ServicioGet sg = new ServicioGet();




    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EditText cantidad;
        public TextView titulo,id,precio, subtotal, idcar, descu, ceducli;
        public ImageButton eliminar, modificar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo =itemView.findViewById(R.id.nombre);
            id=itemView.findViewById(R.id.idtxt2);
            idcar=itemView.findViewById(R.id.idcar);
            precio=itemView.findViewById(R.id.preciotxt2);
            eliminar=itemView.findViewById(R.id.elimbtn);
            cantidad=itemView.findViewById(R.id.cantidadtxt2);
            subtotal=itemView.findViewById(R.id.subtotal);
            modificar=itemView.findViewById(R.id.modifbtn);
            descu=itemView.findViewById(R.id.desc);
            ceducli=itemView.findViewById(R.id.cedulaclibp);

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
        holder.idcar.setText(curentItem.getIdcar());
        holder.cantidad.setText(curentItem.getCantidad());
        holder.ceducli.setText(curentItem.getCeducli());
        holder.descu.setText(curentItem.getDescuento());
        holder.subtotal.setText(String.valueOf(Double.valueOf(Double.valueOf(curentItem.getPrecio()) - (Double.valueOf(curentItem.getPrecio())*(Double.valueOf(curentItem.getDescuento())/100)))*Double.valueOf(curentItem.getCantidad())));
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sg.elimitcarrito(holder.idcar.getText().toString());
                //Intent intent = new Intent (v.getContext(), precompra.class);
                //v.getContext().startActivity(intent);
                System.out.println("Esto es lo que elimina "+holder.idcar.getText().toString());
            }
        });
        holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sg.modifcarrito(holder.ceducli.getText().toString(),holder.id.getText().toString(),holder.cantidad.getText().toString(),holder.precio.getText().toString(),holder.descu.getText().toString(), holder.idcar.getText().toString());
                //Intent intent = new Intent (v.getContext(), precompra.class);
                //v.getContext().startActivity(intent);
                System.out.println("Esto es lo que pasa a modificar "+holder.ceducli.getText().toString()+";"+holder.id.getText().toString()+";"+holder.cantidad.getText().toString()+";"+holder.precio.getText().toString()+";"+holder.descu.getText().toString()+";"+holder.idcar.getText().toString());
            }
        });

    }




    @Override
    public int getItemCount() {
        return listaItems.size();
    }


}
