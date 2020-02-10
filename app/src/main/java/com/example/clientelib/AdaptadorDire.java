package com.example.clientelib;

import android.app.Activity;
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

public class AdaptadorDire extends RecyclerView.Adapter<AdaptadorDire.ViewHolder> {
    int num=1;
    private ArrayList<itemsDirec> listaItems;
    private ServicioGet sg = new ServicioGet();
    private static WeakReference<Context> co;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EditText callep, calles, numcasa, prov, ciud;
        public TextView id;
        public ImageButton eliminar;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.iddir);
            callep=itemView.findViewById(R.id.callep);
            calles=itemView.findViewById(R.id.calles);
            numcasa=itemView.findViewById(R.id.numcasa);
            prov=itemView.findViewById(R.id.prov);
            ciud=itemView.findViewById(R.id.ciudad);
            eliminar=itemView.findViewById(R.id.elimbtnlistd);

        }
    }

    public AdaptadorDire(ArrayList<itemsDirec> listItems){
        listaItems=listItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_direc, parent, false);
        AdaptadorDire.ViewHolder vh= new AdaptadorDire.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final itemsDirec curentItem = listaItems.get(position);
        holder.id.setText(curentItem.getId());
        holder.callep.setText(curentItem.getCallep());
        holder.calles.setText(curentItem.getCalles());
        holder.numcasa.setText(curentItem.getNumcasa());
        holder.ciud.setText(curentItem.getCiuda());
        holder.prov.setText(curentItem.getProvi());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sg.elimitdir(holder.id.getText().toString());
                Intent intent = new Intent (v.getContext(), listaDirec.class);
                v.getContext().startActivity(intent);
            }
        });



    }




    @Override
    public int getItemCount() {
        return listaItems.size();
    }


}
