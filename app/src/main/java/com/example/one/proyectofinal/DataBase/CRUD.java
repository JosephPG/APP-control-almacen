package com.example.one.proyectofinal.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CRUD {

    private AdminSQLiteOH ad;
    private SQLiteDatabase db;

    public CRUD(Context context) {
        this.ad = new AdminSQLiteOH(context);
    }

    public void abrirCRUD() {
        db = ad.getWritableDatabase();
    }

    public void closeCRUD() {
        this.ad.close();
    }

    public Cursor selectLine(String sql) {
        Cursor data = null;
        try {
            data = db.rawQuery(sql, null);
        }catch(Exception e) {
            Log.d("Query::SelectLine",String.valueOf(e));
        }
        return data;
    }

    public int gUpdate(String tabla, String[][] datos, String condicion) {
        int result = 0;
        ContentValues contenedor = new ContentValues();
        try{
            for(int i = 0; i < datos.length; i++){
                contenedor.put(datos[i][0], datos[i][1]);
            }
            result = db.update(tabla, contenedor, condicion, null);
        }catch(Exception e) {
            Log.d("Query::Update",String.valueOf(e));
        }
        return result;
    }


}
