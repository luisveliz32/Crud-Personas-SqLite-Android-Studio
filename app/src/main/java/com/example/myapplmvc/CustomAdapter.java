package com.example.myapplmvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class CustomAdapter extends BaseAdapter {
    Context contex;
    List<Personas> lst;

    public CustomAdapter(Context contex, List<Personas> lst) {
        this.contex = contex;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView_id;
        TextView textView_nombre;
        TextView textView_apellido;

        Personas p=lst.get(i);
        if (view==null){
            view= LayoutInflater.from(contex).inflate(R.layout.listview_persona,null);
        }

        imageView_id=view.findViewById(R.id.imageView_id);
        textView_nombre=view.findViewById(R.id.textView_nombre);
        textView_apellido=view.findViewById(R.id.textView_apellido);

        imageView_id.setImageResource(p.imagen);
        textView_nombre.setText(p.Nombre);
        textView_apellido.setText(p.Apellido);

        return view;
    }
}
