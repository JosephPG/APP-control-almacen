package com.example.one.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/** Items a rellenar en Listview **/
public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Objetos> arreglo;
    LayoutInflater inflater;

    public ListViewAdapter(Context context,ArrayList<Objetos> arreglo) {
        this.context = context;
        this.arreglo=arreglo;
    }

    @Override
    public int getCount() {
        return arreglo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView id,productod,cantidadd,ind,extrad;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.fila_lista, parent, false);


        id= (TextView) itemView.findViewById(R.id.txt_id);
        productod = (TextView) itemView.findViewById(R.id.txt_productod);
        cantidadd = (TextView) itemView.findViewById(R.id.txt_cantidadd);
        ind = (TextView) itemView.findViewById(R.id.txt_fingresod);
        extrad = (TextView) itemView.findViewById(R.id.txt_fvend);

        id.setText("Codigo  "+arreglo.get(position).getId());
        productod.setText(arreglo.get(position).getCodpro() + "     " + arreglo.get(position).getNompro());
        cantidadd.setText("Cantidad  "+arreglo.get(position).getCantidad());
        ind.setText("Ingreso   "+arreglo.get(position).getFecha());

        if(arreglo.get(position).getIdentificador().equals("0")) {
            extrad.setText("Cliente   "+arreglo.get(position).getExtra());
        }else {
            extrad.setText("Vence      "+arreglo.get(position).getExtra());
        }




        return itemView;
    }

}
