package com.example.alex_.gestionequipos2.Modelos;

import android.graphics.Bitmap;

public class Equipo {

    private int id;
    private String nombre;
    private String imagen;
    private Bitmap foto;
    private int numJug;

    public int getNumJug() {
        return numJug;
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(int id, String nombre, Bitmap foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }

    public Equipo(int id, String nombre, int numJug) {
        this.id = id;
        this.nombre = nombre;
        this.numJug = numJug;
    }

    public Equipo(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen=imagen;
    }

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;

    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
