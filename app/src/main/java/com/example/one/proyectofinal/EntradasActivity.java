package com.example.one.proyectofinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

public class EntradasActivity extends AppCompatActivity {

    ListViewAdapter adaptador;
    ListView lsentradas;
    Objetos obj;
    ArrayList<Objetos> arreglo=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lsentradas=(ListView)findViewById(R.id.lst_entrada);
        cargarEntradas();

    }

    /** Conexion por web para obtener registros de entradas**/
    public void cargarEntradas(){
        AsyncHttpClient client=new AsyncHttpClient();
        String url="http://192.168.1.106/proyectofinal/consulta_entradas.php";
        RequestParams parametros=new RequestParams();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                objDatos2(new String(responseBody));
                CargarLista();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    /** Rellenar Listview **/
    public void CargarLista(){
        adaptador = new ListViewAdapter(this, arreglo);
        lsentradas.setAdapter(adaptador);
    }

    /** Obtener datos de registro de entrada del Json respondido **/
    public void objDatos2(String response){

        try{
            JSONArray jsonArray=new JSONArray(response);
            String identrada,codpro,nompro,cantidad,fechaIn,fechaVenc;
            for(int i=0;i<jsonArray.length();i++){
                identrada= jsonArray.getJSONObject(i).getString("identrada");
                codpro= jsonArray.getJSONObject(i).getString("codpro");
                nompro= jsonArray.getJSONObject(i).getString("nompro");
                cantidad= jsonArray.getJSONObject(i).getString("cantidad");
                fechaIn= jsonArray.getJSONObject(i).getString("fechaIn");
                fechaVenc= jsonArray.getJSONObject(i).getString("fechaVenc");

                obj=new Objetos(identrada,codpro,nompro,cantidad,fechaIn,fechaVenc,"1");
                arreglo.add(obj);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
