package com.example.one.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import com.example.one.proyectofinal.DataBase.Querys;

public class MainActivity extends AppCompatActivity {

    EditText usuario,password;
    private String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuario=(EditText) findViewById(R.id.edt_usuario);
        password=(EditText) findViewById(R.id.edt_password);
    }

    /** Validar ingreso de logueo **/
    public void onLogin(View V){

	String u = usuario.getText().toString();
        String p = password.getText().toString();

        String msj = getUser(u);        

        if(u.equals(user) && p.equals(pass)){

	    updateStatusLoginUP();	
            Intent intenta = new Intent(this, MenuActivity.class);
            finish();
            startActivity(intenta);

        }else{
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
        }
	usuario.setText("");
        password.setText("");    
    }

    /** Conexion por web para obtener usuario**/	
    public String getUser(String user){
        String resp = "";
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.106/proyectofinal/consulta_user.php?";
        RequestParams parametros = new RequestParams();
        parametros.put("user", user);

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                objDatos(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
		resp = "Usuario invalido";
		Log.e("tag", "onFailure");
            }
        });

        return resp;
    }

    /** Obtener datos del Json respondido**/
    public void objDatos(String response){
        try{
            JSONArray jsonArray=new JSONArray(response);
            for(int i=0; i<jsonArray.length(); i++){
                user = jsonArray.getJSONObject(i).getString("usu_usuario");
                pass = jsonArray.getJSONObject(i).getString("usu_clave");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
   
    /** Actualizar estado para indicar que la sesion esta abierta **/
    public void updateStatusLoginUP(){
        Querys query = new Querys(this);
        query.updateStatus(true);
        query.querysClose();
    }

}
