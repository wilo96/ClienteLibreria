package com.example.clientelib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class precompra extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton carrito;
    private ImageButton like;
    private ImageButton compartir;
    private EditText cantidad;
    private MainActivity principal;
    private TextView descuento, id, precio, subtotal;
    ServicioGet sg = new ServicioGet();
    String prods;
    JSONObject jsonOb;
    String pro[];
    String[] parteA,parteB;
    URL url;
    Bitmap bit;
    private  int num=1;
    int aceptarnada=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precompra);
        carrito=(ImageButton)findViewById(R.id.añadirbtn);
        like=(ImageButton)findViewById(R.id.likebtn);
        compartir=(ImageButton)findViewById(R.id.compartirbtn);
        descuento=(TextView)findViewById(R.id.descuentotxt);
        id=(TextView)findViewById(R.id.idtxt2);
        precio=(TextView)findViewById(R.id.preciotxt2);
        subtotal=(TextView)findViewById(R.id.subtotal);
        cantidad=(EditText) findViewById(R.id.cantidadtxt2);

        ArrayList<ItemsPre> listaItems = new ArrayList<>();
       // listaItems.add(new ItemsPre(R.drawable.ic_android1,"Linea 1","Linea 2","Linea 3","Linea 4"));
       // listaItems.add(new ItemsPre(R.drawable.ic_android1,"Linea 5","Linea 6","Linea 7","Linea 8"));
        // listaItems.add(new ItemsPre(R.drawable.ic_android1,"Linea 9","Linea 10","Linea 11","Linea 12"));

        prods=sg.precompra();
        System.out.println("Esto me imprime de lo que tengo en carrito"+prods);
        parteA=prods.split(";");

        //respuestas=json.substring(10,(json.length()-1)).split(",");
        for(int i =0; i<parteA.length;i++){
            parteB=parteA[i].split(",");
            for(int z=1;z<parteB.length;z++)
            {
                System.out.println(parteB[z]);
            }
            try {
                listaItems.add(new ItemsPre(parteB[4].substring(1, parteB[4].length() - 1), parteB[6], parteB[2], parteB[1], parteB[3], String.valueOf(Integer.parseInt(parteB[1]) * Double.valueOf(parteB[2]))));
            }catch(Exception e)
            {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Aviso");
                dialogo1.setMessage("Aún no hay productos seleccionados");
                dialogo1.setCancelable(true);
                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // sg.carrito(id.getText().toString(),cantidad.getText().toString(),precio.getText().toString(),descuento.getText().toString(),subtotal.getText().toString());
                        aceptarnada=1;
                       // SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
                       // String cedu = prefs.getString("cedula","");
                      //  sg.precompraConf(cedu,1,1);
                      //  Toast.makeText(getApplicationContext(), cedu, Toast.LENGTH_LONG).show();

                    }
                });
                if(aceptarnada==1) {
                    Intent in = new Intent(this, Productos.class);
                    startActivityForResult(in, 0);
                }
                dialogo1.show();
            }


           //listaItems.add(new ItemsPre(parteB[4].substring(1,parteB[4].length()-1),parteB[6],parteB[2],parteB[1], parteB[7]));
        }
        /*try {
            JSONArray jsonArray = new JSONArray(prods);

            for(int i = 0; i< jsonArray.length();i++){
                jsonOb = jsonArray.getJSONObject(i);
                bit = BitmapFactory.decodeStream((InputStream)new URL(jsonOb.optString("imagen")).getContent());
                //Log.d("Salida del Select ",jsonOb.optString("titulo"));
                //System.out.println("------------------------- "+jsonOb.toString());
                listaItems.add(new Items(bit,jsonOb.optString("titulo"),jsonOb.optString("autor"),jsonOb.optString("codigo"),jsonOb.optString("editorial"),jsonOb.optString("precio"),jsonOb.optString("stock"),jsonOb.optString("descuento")));
                //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/
        recyclerView = findViewById(R.id.listaPre);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdaptadorPre(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        /*String usuario= getFromSharedPreferences("usuario");
        System.out.println("usuario de shared preferences"+usuario);
        Toast.makeText(getApplicationContext(),"Bienvenid@"+usuario,Toast.LENGTH_LONG).show();
        //System.out.println(principal.cargarPreferencias());*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.confirmarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
int aceptar=0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.confiCompra:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Aviso");
                dialogo1.setMessage("¿Desea confirmar la compra?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // sg.carrito(id.getText().toString(),cantidad.getText().toString(),precio.getText().toString(),descuento.getText().toString(),subtotal.getText().toString());

                        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
                        String cedu = prefs.getString("cedula","");
                        sg.precompraConf(cedu,1,1);


                        Toast.makeText(getApplicationContext(), cedu, Toast.LENGTH_LONG).show();
                        ventana();

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogo1.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getFromSharedPreferences(String usuario) {
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref=getBaseContext().getSharedPreferences("guardarUsuario", MODE_PRIVATE);
        String user= sharedPref.getString("usuario","");

        return user;
    }

    private void ventana(){
        Intent i = new Intent(this, Productos.class);
        startActivityForResult(i, 0);
    }


}