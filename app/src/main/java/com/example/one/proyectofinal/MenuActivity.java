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
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.one.proyectofinal.DataBase.Querys;

public class MenuActivity extends AppCompatActivity {
   ListView lista;
   String[] opciones={"Registrar entrada/salida","Consultar entradas","Consultar salidas","Cerrar sesión"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista=(ListView) findViewById(R.id.lst_menu);

        ArrayAdapter adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opciones);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                if(item.equals("Registrar entrada/salida")){
                    Registrar();
                }else if(item.equals("Consultar entradas")){
                    Entradas();
                }else if(item.equals("Consultar salidas")){
                    Salidas();
                }else{
                    onSalir();
                }

            }
        });
    }

    /** Redirige a RegistrarActivity **/
    public void Registrar(){
        Intent intenta=new Intent(this, RegistrarActivity.class);
        startActivity(intenta);
    }

    /** Redirige a EntradasActivity **/
    public void Entradas(){
        Intent intenta=new Intent(this, EntradasActivity.class);
        startActivity(intenta);
    }
   
    /** Redirige a SalidasActivity **/
    public void Salidas(){
        Intent intenta=new Intent(this, SalidasActivity.class);
        startActivity(intenta);
    }
  
    /** Destruir sesión **/
    public void onSalir(){
        updateStatusLoginDOWN()
        Intent intent = new Intent(MainActivity.class);
        finish();
        startActivity(intent);
    }
  
    /** Actualizar estado para indicar que sesión esta cerrada **/
    public void updateStatusLoginDOWN(){
        Querys query = new Querys(this);
        query.updateStatus(false);
        query.querysClose();
    }

}
