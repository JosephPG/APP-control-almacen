package com.example.one.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarActivity extends AppCompatActivity {

    TextView codigop,nombrep,preciop;
    Spinner otipo,sdia,sanno,smes,scliente;
    EditText ecantidad;

    String codigo_barra;
    String ingreso_salida;
    String registrar_cliente;
    String fecha_actual;

    String[] clientes={"Seleccionar","Seleccionar","",""};
    String[] opcion={"Ingreso","Salida"};
    String[] anno={"2016","2017","2018","2019","2020","2021","2022","2023"};
    String[] mes={"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] dia={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onBarcode();

        codigop = (TextView) findViewById(R.id.txv_codigo);
        nombrep = (TextView) findViewById(R.id.txv_nombre);
        preciop = (TextView) findViewById(R.id.txv_precio);
        ecantidad = (EditText) findViewById(R.id.edt_cantidad);
        scliente = (Spinner) findViewById(R.id.spn_cliente);
	otipo = (Spinner) findViewById(R.id.spn_tipo);
	sdia = (Spinner) findViewById(R.id.spn_dia);
	smes = (Spinner) findViewById(R.id.spn_mes);
	sanno = (Spinner) findViewById(R.id.spn_anno);

	/** Rellenar spinners **/
        ArrayAdapter adaptador4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
	ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcion);
	ArrayAdapter adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dia);
	ArrayAdapter adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mes);
	ArrayAdapter adaptador3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, anno);
        scliente.setAdapter(adaptador4);
        otipo.setAdapter(adaptador);
        sdia.setAdapter(adaptador1);
        smes.setAdapter(adaptador2);
        sanno.setAdapter(adaptador3);


        otipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

		 /** Validar el tipo de ingreso o salida **/
                 ingreso_salida = parent.getItemAtPosition(position).toString();
                 if (ingreso_salida.equals("Ingreso")) {
                    sdia.setEnabled(true);
                    smes.setEnabled(true);
                    sanno.setEnabled(true);
                    scliente.setEnabled(false);

                 }else{
                        cargarClientes();
                        sdia.setEnabled(false);
                        smes.setEnabled(false);
                        sanno.setEnabled(false);
                        scliente.setEnabled(true);
                      }
                  } 

             @Override
             public void onNothingSelected(AdapterView<?> parent) {}

            });

		
            scliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
  
                /** Seleccion de cliente **/
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int posicion = position + 1;
                    registrar_cliente = Integer.toString(posicion);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


    }

    /** Iniciar zxing  **/	
    public void onBarcode() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    /** Obtener resultado de zxing **/
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            codigo_barra = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Obtener();

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /** Conexion por web para obtener producto **/
    public void Obtener(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.106/proyectofinal/consulta_producto.php?";
        RequestParams parametros = new RequestParams();
        parametros.put("codigo", codigo_barra);
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                objDatos(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
		Log.e("tag", "onFailure");
 		Toast.makeText(this, "Producto no encontrado", Toast.LENGHT_SHORT).show();
            }
        });
    }

    /** Obtener datos de producto del Json respondido **/
    public void objDatos(String response){

        try{
            JSONArray jsonArray=new JSONArray(response);
            String cod="",nom="",precio="";
            for(int i=0;i<jsonArray.length();i++){
                cod = jsonArray.getJSONObject(i).getString("codpro");
                nom = jsonArray.getJSONObject(i).getString("nompro");
                precio=jsonArray.getJSONObject(i).getString("precio");
            }
            codigop.setText(cod);
            nombrep.setText(nom);
            preciop.setText(precio);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** Conexion por web para obtener clientes **/ 
    public void cargarClientes(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.106/proyectofinal/consulta_cliente.php";
        RequestParams parametros=new RequestParams();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                objDatos2(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
		Log.e("tag", "onFailure");
            }
        });
    }

    /** Obtener datos de clientes del Json respondido **/ 
    public void objDatos2(String response){

        try{
            JSONArray jsonArray=new JSONArray(response);
            String nombre,apellido;
            for(int i=0;i<jsonArray.length();i++){
                nombre= jsonArray.getJSONObject(i).getString("nombclien");
                apellido= jsonArray.getJSONObject(i).getString("apeclien");
                clientes[i]=nombre+" "+apellido;
            }

            ArrayAdapter adaptador4=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,clientes);
            scliente.setAdapter(adaptador4);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** Conexion por web para almacenar datos **/
    public void onGuardar(View v){
        String cantidad=ecantidad.getText().toString();
        String vencimiento_cliente="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fecha_actual = sdf.format(new Date());

        AsyncHttpClient client=new AsyncHttpClient();
        String url="";

        RequestParams parametros=new RequestParams();
        parametros.put("codigo_barra",codigo_barra);
        parametros.put("cantidad",cantidad);
        parametros.put("fecha_actual", fecha_actual);

        if(ingreso_salida.equals("Ingreso")){
            String dia= sdia.getSelectedItem().toString();
            String mes= smes.getSelectedItem().toString();
            String anno= sanno.getSelectedItem().toString();
            vencimiento_cliente= anno+"-"+mes+"-"+dia;

            url="http://192.168.1.106/proyectofinal/registrar_entradas.php?";

        }else{
            vencimiento_cliente=registrar_cliente;
            url="http://192.168.1.106/proyectofinal/registrar_salidas.php?";
        }
        parametros.put("vencimiento_cliente", vencimiento_cliente);

        client.post(url,parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                String resultado=new String(bytes);
                String str1=resultado.trim();


                if(str1.equals("1")) {
                    Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_LONG).show();
                    Salir();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void Salir(){
        super.onBackPressed();
    }



}
