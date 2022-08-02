package com.example.myapplmvc;

public class Personas {
    public int id;
    public int imagen;
    public  String Nombre;
    public  String Apellido;

    public Personas(int id,int imagen, String nombre, String apellido) {
        this.id = id;
        this.imagen = imagen;
        Nombre = nombre;
        Apellido = apellido;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
