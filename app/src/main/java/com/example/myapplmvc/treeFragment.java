package com.example.myapplmvc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class treeFragment extends Fragment {




    EditText nombre,apellido;
    Button guardar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tree, container, false);
        nombre=(EditText) view.findViewById(R.id.nombrem);
        apellido=(EditText) view.findViewById(R.id.apellidom);
        guardar=(Button) view.findViewById(R.id.buttonSecond);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                guardar(nombre.getText().toString(),apellido.getText().toString());
                NavHostFragment.findNavController(treeFragment.this)
                        .navigate(R.id.action_treeFragment_to_FirstFragment);
            }

        });
        // Inflate the layout for this fragment
        return view;
    }
    private void  guardar(String Nombre,String Apellido ){
        BaseHelper helper=new BaseHelper(getActivity(),"Demo" ,null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        try {
            ContentValues c=new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Apellido",Apellido);
            db.insert("Personas",null,c);
            db.close();
            Toast.makeText(getActivity(), "Registro Insertado "+c, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}