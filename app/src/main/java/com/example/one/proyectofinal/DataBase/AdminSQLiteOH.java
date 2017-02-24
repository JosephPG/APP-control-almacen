package package com.example.one.proyectofinal.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Base de datos para indicar estado del Logueo **/
public class AdminSQLiteOH extends SQLiteOpenHelper{
    private static final String DBNAME = "LOGIN";

    private final String id_table = "id integer primary key,";
    private final String status = "status integer";

    private final String id_data = "616,";
    private final String status_data = "0";

    private final String sql = "create table login_data("+id_table+status+")";

    private final String consulta = "insert into login_data (id, status) values("+id_data+status_data+")";

    public AdminSQLiteOH(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(consulta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
