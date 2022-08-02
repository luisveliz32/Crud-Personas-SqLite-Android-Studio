package com.example.myapplmvc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplmvc.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
EditText nombre,apellido;
Button guardar;
int id;
String rnombre,rapellido;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre=(EditText) view.findViewById(R.id.nombrem);
        apellido=(EditText) view.findViewById(R.id.apellidom);
        getParentFragmentManager().setFragmentResultListener("persona", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                 id = result.getInt("id");
                rnombre=result.getString("Nombre");
                rapellido=result.getString("Apellido");
                nombre.setText(rnombre);
                apellido.setText(rapellido);

            }
        });


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(nombre.getText().toString(),apellido.getText().toString(),id);
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        binding.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(id);
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void  modificar(String Nombre,String Apellido ,int id){
        BaseHelper helper=new BaseHelper(getActivity(),"Demo" ,null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
       try {
            String sql="update Personas set Nombre='"+Nombre+"',Apellido='"+Apellido+"' Where id="+id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(getActivity(), "Registro Modificado con exito ", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
    private void  eliminar(int id){
        BaseHelper helper=new BaseHelper(getActivity(),"Demo" ,null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
         try {
        String sql="delete from Personas  Where id="+id;
        db.execSQL(sql);
        db.close();
        Toast.makeText(getActivity(), "Registro Eliminado ", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

}