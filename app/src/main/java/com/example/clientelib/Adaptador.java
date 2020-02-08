package com.example.clientelib;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>  {
    int num=1;
    private ArrayList<Items> listaItems;
    private MainActivity ma= new MainActivity();
    private ServicioGet sg= new ServicioGet();
    public Context con;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public EditText cantidad;
        public ImageView imageView;
        public TextView titulo, autor, id, descuento, precio, editorial, stock;
        public ImageButton mas, menos,carro, like, compartir;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.foto1);
            titulo =itemView.findViewById(R.id.Texto1);
            autor =itemView.findViewById(R.id.Texto2);
            id=itemView.findViewById(R.id.idtxt);
            descuento=itemView.findViewById(R.id.descuentotxt);
            precio=itemView.findViewById(R.id.preciotxt);
            editorial=itemView.findViewById(R.id.editorialtxt);
            stock=itemView.findViewById(R.id.stocktxt);
            carro=itemView.findViewById(R.id.añadirbtn);
            like=itemView.findViewById(R.id.likebtn);
            compartir=itemView.findViewById(R.id.compartirbtn);
            cantidad=itemView.findViewById(R.id.cantidadtxt);

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Items curentItem = listaItems.get(position);

        holder.imageView.setImageBitmap(curentItem.getmImageResources());
        holder.titulo.setText(curentItem.getTitulo());
        holder.autor.setText(curentItem.getAutor());
        holder.stock.setText(curentItem.getStock());
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
                sg.carrito(posicion,cantidad,precio,descuento,String.valueOf(subtL));
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
