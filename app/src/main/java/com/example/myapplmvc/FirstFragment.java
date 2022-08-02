package com.example.myapplmvc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplmvc.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment {


    private ListView listview;
    List<Personas>lst;
    private ArrayList<String> names;
    FloatingActionButton fab;
    ListView listView;

    ArrayList<String>listado;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listview=(ListView) view.findViewById(R.id.id_listView);
        CustomAdapter ada= new CustomAdapter(getContext(),GetData());
        listview.setAdapter(ada);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Personas p=lst.get(i);
                Toast.makeText(getContext(), p.Nombre, Toast.LENGTH_SHORT).show();
                Bundle bundle =new Bundle();
                bundle.putInt("id",p.id);
                bundle.putString("Nombre",p.Nombre);
                bundle.putString("Apellido",p.Apellido);
                getParentFragmentManager().setFragmentResult("persona",bundle);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });



         //Inflate the layout for this fragment
        return view;

        //binding = FragmentFirstBinding.inflate(inflater, container, false);
        //return binding.getRoot();
    }

    private List<Personas> GetData() {
        lst=new ArrayList<>();

        BaseHelper helper= new BaseHelper(getActivity(),"Demo",null,1);
        SQLiteDatabase db= helper.getReadableDatabase();
        String sql="select Id,Nombre,Apellido from Personas;";
        Cursor c=db.rawQuery(sql,null);
        Log.d("ESTO ES C", "C : " + c);
        if (c.moveToFirst()){
            do {

                lst.add(new Personas(c.getInt(0),R.drawable.hombre,c.getString(1),c.getString(2)));

            }while (c.moveToNext());
        }
        db.close();
        return lst;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void CargarListado(){



    }

    private ArrayList<String> ListaPersona(){
        ArrayList<String> datos=new ArrayList<String>();
        BaseHelper helper= new BaseHelper(getActivity(),"Demo",null,1);
        SQLiteDatabase db= helper.getReadableDatabase();
        String sql="select Id,Nombre,Apellido from Personas;";
        Cursor c=db.rawQuery(sql,null);
        Log.d("ESTO ES C", "C : " + c);
        if (c.moveToFirst()){
            do {
                String linea=c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;

    }

}