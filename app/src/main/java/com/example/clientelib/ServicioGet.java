package com.example.clientelib;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ServicioGet {

    public String getData(String correo, String contra)
    {
        String datosres = null;
        String url ="http://192.168.100.17:8080/proyectoInterciclo/srv/Usuarios/buscarusu?correo="+correo+"&contra="+contra;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL urlc = null;
        HttpURLConnection con;

        try{
            urlc= new URL(url);
            con = (HttpURLConnection) urlc.openConnection();

            con.setRequestMethod("GET");

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inutline;
            StringBuffer response = new StringBuffer();

            String json ="";
            String respuestas[];

            while((inutline = br.readLine()) != null)
            {
                response.append(inutline);
            }

            json=response.toString();
            respuestas=json.substring(10,(json.length()-1)).split(",");
            for(int i =0; i<respuestas.length;i++){
                int fin = respuestas[i].length();
                respuestas[i]=respuestas[i].substring(respuestas[i].indexOf("=")+1, fin);
                datosres=respuestas[i]+";";
                System.out.println(respuestas[i]);

            }
            System.out.println("Esto manda "+json);
            //JSONArray jsonArray=null;
            //jsonArray = new JSONArray(json);

            /*for(int i = 0; i< jsonArray.length();i++){
                JSONObject jsonOb = jsonArray.getJSONObject(i);
                Log.d("Salida del Select ",jsonOb.optString("nombres"));
                System.out.println("------------------------- "+jsonOb.toString());
            }*/

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return datosres;

    }
    
}
