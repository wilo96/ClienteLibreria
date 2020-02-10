package com.example.clientelib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class prefactura extends AppCompatActivity {
    private ServicioGet sg = new ServicioGet();
    private TableLayout tabla;
    private TableRow detalle;
    private TextView subt, total, usuario, direc, telefonol, cedula, descuentopt;
    double subtotalpago, totalpago, descuentopago;

    String datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefactura);
        tabla=(TableLayout) findViewById(R.id.productosdetalles);
        subt=(TextView)findViewById(R.id.subtotaltxt);
        total=(TextView)findViewById(R.id.totaltxt);
        usuario=(TextView)findViewById(R.id.nomcabetxt);
        direc=(TextView)findViewById(R.id.dircabetxt);
        telefonol=(TextView)findViewById(R.id.telfcabetxt);
        cedula=(TextView)findViewById(R.id.ceducabtxt);
        descuentopt=(TextView)findViewById(R.id.descuentotxt);
        String direccion= getFromSharedPreferences("direc","tarj");
        System.out.println("usuario de shared preferences"+direccion);
        String datoscab[]=direccion.split(";");
        direc.setText(datoscab[0]);
        SharedPreferences prefs = getSharedPreferences("pasamos",   Context.MODE_PRIVATE);
        String cedu = prefs.getString("cedula","");
        String nombre = prefs.getString("nombre","");
        String telefono = prefs.getString("telefono","");
        cedula.setText(cedu);
        usuario.setText(nombre);
        telefonol.setText(telefono);
        datos = sg.precompra(cedu);


        //Toast.makeText(getApplicationContext(),"Aqui mandaremos y pagamos "+direccion,Toast.LENGTH_LONG).show();
        String [] prods=datos.split(";");
        for(String prod:prods){
            List<String> pele=new ArrayList<String>();
            System.out.println(prod);
            String [] producto=prod.split(",");
            for(String elemp:producto){
                pele.add(elemp);
            }

            TableRow row = new TableRow(this);
            String titulo=pele.get(4);
            int cantidad=Integer.parseInt(pele.get(1));
            double precio=Double.valueOf(pele.get(2));
            double descuento = Double.valueOf(pele.get(3));
            double valdesc = (precio * (descuento/100))*cantidad;
            double subtotal = cantidad * precio;
            subtotalpago=subtotalpago+subtotal;
            descuentopago=descuentopago+valdesc;
            TextView tv= new TextView(this);
            tv.setText(titulo.substring(1,titulo.length()-1));
            tv.setGravity(Gravity.LEFT);
            TextView tv1= new TextView(this);
            tv1.setText(String.valueOf(cantidad));
            tv1.setGravity(Gravity.LEFT);
            TextView tv2= new TextView(this);
            tv2.setText(String.valueOf(precio));
            tv2.setGravity(Gravity.RIGHT);
            TextView tv3= new TextView(this);
            tv3.setText(String.format("%.2f", valdesc));
            tv3.setGravity(Gravity.RIGHT);
            TextView tv4= new TextView(this);
            tv4.setText(String.valueOf(subtotal));
            tv4.setGravity(Gravity.RIGHT);
            row.addView(tv);
            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            row.addView(tv4);
            tabla.addView(row);

        }
        totalpago=(subtotalpago-descuentopago)+10;
        subt.setText(String.valueOf(subtotalpago));
        total.setText(String.valueOf(totalpago));
        descuentopt.setText(String.valueOf(descuentopago));
        //System.out.println(datos);

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
            case R.id.salirco:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void ventana(){
        Intent i = new Intent(this, Productos.class);
        startActivityForResult(i, 0);
    }

    private String getFromSharedPreferences(String direc, String tarj ) {
        SharedPreferences prefs = getSharedPreferences("datosfin", Context.MODE_PRIVATE);
        String direcc = prefs.getString("direc", "");
        String tarje = prefs.getString("tarj", "");
        return direcc + ";" + tarje;
    }

}

