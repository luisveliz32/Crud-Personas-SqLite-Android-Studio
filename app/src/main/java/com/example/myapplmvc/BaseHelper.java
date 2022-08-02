package com.example.myapplmvc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseHelper extends SQLiteOpenHelper {

    String tabla="CREATE TABLE PERSONAS(ID INTEGER  PRIMARY KEY ,NOMBRE TEXT,APELLIDO TEXT );";
    String a="INSERT INTO Personas (Nombre, Apellido)VALUES ('Roberto carlos', 'Fernandez cazon');";
    String a1="INSERT INTO Personas (Nombre, Apellido)VALUES ('Nicolas Rodrigo', 'Paz Calvimonte');";
    String a2="INSERT INTO Personas (Nombre, Apellido)VALUES ('Alvaro Roberto', 'Cerrudo Fernandez');";
    String a3="INSERT INTO Personas (Nombre, Apellido)VALUES ('Juan Carlos', 'Jalil Cazon');";
    String a4="INSERT INTO Personas (Nombre, Apellido)VALUES ('Luis Fernando', 'Oliva Perez');";
    String a5="INSERT INTO Personas (Nombre, Apellido)VALUES ('Miguel Adolfo', 'Pedraza Espada');";
    String a6="INSERT INTO Personas (Nombre, Apellido)VALUES ('Mariana Marisel', 'Sustacha Gira');";
    String a7="INSERT INTO Personas (Nombre, Apellido)VALUES ('Francisco Carlos', 'Villa Espada');";
    String a8="INSERT INTO Personas (Nombre, Apellido)VALUES ('Gloria Selena', 'Ortis Morales');";
    String a9="INSERT INTO Personas (Nombre, Apellido)VALUES ('Rosa Esperanza', 'Cazon Fernandez');";

    public BaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
        db.execSQL(a);
        db.execSQL(a1);
        db.execSQL(a2);
        db.execSQL(a3);
        db.execSQL(a4);
        db.execSQL(a5);
        db.execSQL(a6);
        db.execSQL(a7);
        db.execSQL(a8);
        db.execSQL(a9);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table personas");
        db.execSQL(tabla);
    }
}
