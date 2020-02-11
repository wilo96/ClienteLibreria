package com.example.clientelib;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorBusca extends RecyclerView.Adapter<AdaptadorBusca.ViewHolder>  {
    int num=1;
    private ArrayList<itemsBuscar> listaItems;
    private ServicioGet sg= new ServicioGet();
    private String cedulacliente;

    public AdaptadorBusca() {

    }

    public String getCedulacliente() {
        return cedulacliente;
    }

    public void setCedulacliente(String cedulacliente) {
        this.cedulacliente = cedulacliente;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EditText cantidad;
        public ImageView imageView;
        public TextView titulo, autor, id, descuento, precio, editorial, stock, cedulacl, idca;
        public ImageButton mas, menos,carro, like, compartir;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.foto1b);
            titulo =itemView.findViewById(R.id.Texto1b);
            cedulacl=itemView.findViewById(R.id.cedulaclib);
            autor =itemView.findViewById(R.id.Texto2b);
            idca=itemView.findViewById(R.id.idcar);
            id=itemView.findViewById(R.id.idtxtb);
            descuento=itemView.findViewById(R.id.descuentotxtb);
            precio=itemView.findViewById(R.id.preciotxtb);
            editorial=itemView.findViewById(R.id.editorialtxtb);
            stock=itemView.findViewById(R.id.stocktxtb);
            carro=itemView.findViewById(R.id.añadirbtnb);
            like=itemView.findViewById(R.id.likebtnb);
            compartir=itemView.findViewById(R.id.compartirbtnb);
            cantidad=itemView.findViewById(R.id.cantidadtxtb);

        }
    }

    public AdaptadorBusca(ArrayList<itemsBuscar> listItems){
        listaItems=listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_buscar, parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final itemsBuscar curentItem = listaItems.get(position);

        holder.imageView.setImageBitmap(curentItem.getmImageResources());
        holder.titulo.setText(curentItem.getTitulo());
        holder.autor.setText(curentItem.getAutor());
        holder.stock.setText(curentItem.getStock());
        holder.cedulacl.setText(curentItem.getCedula());
        holder.editorial.setText(curentItem.getEditorial());
        holder.precio.setText(curentItem.getPrecio());
        holder.descuento.setText(curentItem.getDescuento());
        holder.id.setText(curentItem.getId());
        holder.carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posicion= curentItem.getId();
                String cantidad = holder.cantidad.getText().toString();
                String precio = curentItem.getPrecio();
                String descuento = curentItem.getDescuento();
                double desc = Double.valueOf(descuento), cant = Double.valueOf(cantidad);
                double prec = Double.valueOf(precio);
                double valL=prec-(prec*(desc/100));
                double subtL = valL*cant;

                System.out.println("Esta es la posicion que estaba seleccionando "+posicion+" "+cant+" "+desc+" "+prec+" "+String.format("%.2f", valL)+" "+String.format("%.2f", subtL));
                sg.carrito(posicion,cantidad,precio,descuento,String.valueOf(subtL),holder.cedulacl.getText().toString());
            }
        });
        holder.compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
                con.startActivity(Intent.createChooser(intent, "Share with"));*/
                Intent intent = new Intent (v.getContext(), compartir.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }


}
