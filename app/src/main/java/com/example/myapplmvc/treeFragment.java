package com.example.myapplmvc;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class treeFragment extends Fragment {




    EditText nombre,apellido;
    Button guardar;
    ImageView imagen;
    Uri imageuri;

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
        checkExternalStoragePermission();
        imagen=(ImageView) view.findViewById(R.id.imgen_id) ;
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(35,50)
                        .getIntent(getContext());
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);*/
                PickFromCamera();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void PickFromCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"TITULO DE LA IMAGEN");
        values.put(MediaStore.Images.Media.DESCRIPTION,"DESCRIPCIOIN DE LA IMAGEM");

        imageuri=getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(camera_intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.activity(imageuri)
                    .start(getActivity());
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == Activity.RESULT_OK) {
                    //imageuri=result.getUri();
                    imagen.setImageURI(imageuri);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Toast.makeText(getActivity(), "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                }

        }else {
            Toast.makeText(getActivity(), ""+requestCode, Toast.LENGTH_SHORT).show();
        }
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
    private void checkExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //Log.e(TAG, "Permission not granted WRITE_EXTERNAL_STORAGE.");
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        225);
            }
        }if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Log.e(TAG, "Permission not granted CAMERA.");
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        226);
            }
        }

    }

}