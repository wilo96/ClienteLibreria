package com.example.clientelib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<Items> listaItems;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView texto1, texto2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.foto1);
            texto1=itemView.findViewById(R.id.Texto1);
            texto2=itemView.findViewById(R.id.Texto2);
        }
    }

    public Adaptador(ArrayList<Items> listItems){
        listaItems=listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items curentItem = listaItems.get(position);

        holder.imageView.setImageResource(curentItem.getmImageResources());
        holder.texto1.setText(curentItem.getmText1());
        holder.texto2.setText(curentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }
}
