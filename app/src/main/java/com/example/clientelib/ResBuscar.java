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
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ResBuscar extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton carrito;
    private ImageButton like;
    private ImageButton compartir;
    private TextView cantidad;
    private MainActivity principal;

    ServicioGet sg = new ServicioGet();
    String prods;
    JSONObject jsonOb;
    String pro[];
    URL url;
    Bitmap bit;
    private int num = 1;
    Items it;
    int cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_buscar);
        carrito = (ImageButton) findViewById(R.id.añadirbtnb);
        like = (ImageButton) findViewById(R.id.likebtnb);
        compartir = (ImageButton) findViewById(R.id.compartirbtnb);


        cantidad = (TextView) findViewById(R.id.cantidadtxtb);
        cod= Integer.parseInt(getIntent().getStringExtra("id"));
        ArrayList<itemsBuscar> listaItems = new ArrayList<>();
        //listaItems.add(new Items(R.drawable.ic_android1,"Linea 1","Linea 2"));
        //listaItems.add(new Items(R.drawable.ic_announcement,"Linea 3","Linea 4"));
        // listaItems.add(new Items(R.drawable.ic_android1,"Linea 5","Linea 6"));

        prods = sg.getBuscarLibro(cod);
        try {
            JSONArray jsonArray = new JSONArray(prods);
            SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
            String usuariocli = prefs.getString("cedula","");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonOb = jsonArray.getJSONObject(i);
                bit = BitmapFactory.decodeStream((InputStream) new URL(jsonOb.optString("imagen")).getContent());
                //Log.d("Salida del Select ",jsonOb.optString("titulo"));

                //System.out.println("------------------------- "+jsonOb.toString());
                listaItems.add(new itemsBuscar(bit, jsonOb.optString("titulo"), jsonOb.optString("autor"), jsonOb.optString("codigo"), jsonOb.optString("editorial"), jsonOb.optString("precio"), jsonOb.optString("stock"), jsonOb.optString("descuento"),usuariocli));
                //Toast.makeText(getApplicationContext(),prods,Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.listaBusc);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdaptadorBusca(listaItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //System.out.println(principal.cargarPreferencias());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.Precompra:
                try {
                    Intent i = new Intent(this, precompra.class);
                    startActivityForResult(i, 0);
                } catch (Exception e) {

                }
                return true;
            case R.id.Direccion:
                try {
                    Intent i = new Intent(this, direcciones.class);
                    startActivityForResult(i, 0);
                } catch (Exception e) {

                }
                return true;
            case R.id.Tarjeta:
                try {
                    Intent i = new Intent(this, Tarjetas.class);
                    startActivityForResult(i, 0);
                } catch (Exception e) {

                }
                return true;
            case R.id.Buscarmenu:
                try {

                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    final EditText edittext = new EditText(getApplicationContext());
                    alert.setMessage("Libro a Buscar");
                    alert.setTitle("Buscar Libro");

                    alert.setView(edittext);

                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //What ever you want to do with the value
                            Editable busco = edittext.getText();
                            //OR
                            String buscarl = edittext.getText().toString();
                            sg.getBuscarLibro(Integer.parseInt(buscarl));
                        }
                    });

                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });

                    alert.show();
                } catch (Exception e) {

                }
            case R.id.salirm:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}



