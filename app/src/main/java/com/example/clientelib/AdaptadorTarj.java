package com.example.clientelib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AdaptadorTarj extends RecyclerView.Adapter<AdaptadorTarj.ViewHolder> {
    int num=1;
    private ArrayList<itemsTarjeta> listaItems;
    private ServicioGet sg = new ServicioGet();
    private static WeakReference<Context> co;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView numt,id,ident, empre;
        public ImageButton eliminar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numt=itemView.findViewById(R.id.tarjtxtnum);
            ident=itemView.findViewById(R.id.numsegtxtnum);
            empre=itemView.findViewById(R.id.empresatxtnom);
            id=itemView.findViewById(R.id.idtarjetal);
            eliminar=itemView.findViewById(R.id.elimttbtntar);

        }
    }

    public AdaptadorTarj(ArrayList<itemsTarjeta> listItems){
        listaItems=listItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_tarjeta, parent, false);
        AdaptadorTarj.ViewHolder vh= new AdaptadorTarj.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final itemsTarjeta curentItem = listaItems.get(position);
        holder.id.setText(curentItem.getId());
        holder.numt.setText(curentItem.getNumtarj());
        holder.ident.setText(curentItem.getIdentif());
        holder.empre.setText(curentItem.getEmpresa());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sg.elimittar(holder.id.getText().toString());
                Intent intent = new Intent (v.getContext(), tarjetalist.class);
                v.getContext().startActivity(intent);
            }
        });


    }




    @Override
    public int getItemCount() {
        return listaItems.size();
    }


}
