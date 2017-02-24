package com.example.one.proyectofinal.DataBase;

import android.content.Context;
import android.database.Cursor;


public class Querys {

    private CRUD crud;
    private final String name_tabla = "login_data";
    private final String sql_id = "616";
    private final String sesionup = "1";
    private final String sesiondown = "0";

    public Querys(Context context) {
        this.crud = new CRUD(context);
        this.crud.abrirCRUD();
    }

    public void querysClose() {
        this.crud.closeCRUD();
    }


    public boolean getStatus(){
        boolean status = false;
        Cursor data = crud.selectLine("Select status from " + name_tabla + " where id = " + this.sql_id);
        if(data.moveToFirst()){
            if(data.getInt(0) == 1) {
                status = true;
            }
        }
        return status;
    }

    public void updateStatus(boolean init) {
        String[][] data = new String[1][2];
        data[0][0] = "status";
        data[0][1] = (init == true)? this.sesionup: this.sesiondown;
        String sql = "id = "+this.sql_id;
        if (crud.gUpdate(this.name_tabla, data, sql) != 1) {
            throw new IllegalArgumentException("Querys::updateStatus");
        }
    }





}
