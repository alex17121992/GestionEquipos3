package com.example.alex_.gestionequipos2.Modelos;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Jugador {

    private int id;
    private String nombre;
    private String apellidos;
    private String nombreDeport;
    private int edad;
    private double altura;
    private double peso;
    private String demarcPri;
    private String demarcSec;
    private String pie;
    private int lesion;
    private String equipoPro;
    private Bitmap f;
    private String tipo;
    private int idEquip;
    private String imagen64;
    private byte[] imagenBlob;
    private Bitmap foto;


    public Jugador(String nombre, String apellidos, String nombreDeport, int edad, double altura, double peso, String demarcPri, String demarcSec, String pie, int lesion, String equipoPro, String tipo, int idEquip, String imagen64) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDeport = nombreDeport;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.demarcPri = demarcPri;
        this.demarcSec = demarcSec;
        this.pie = pie;
        this.lesion = lesion;
        this.equipoPro = equipoPro;
        this.tipo = tipo;
        this.idEquip = idEquip;
        this.imagen64 = imagen64;
    }

    public Jugador(int id, String nombreDeport, String nombre,String imagen64){
        this.id=id;
        this.nombreDeport=nombreDeport;
        this.nombre=nombre;
        this.imagen64=imagen64;
    }

    public Jugador(String nombreDeport) {
        this.nombreDeport = nombreDeport;
    }

    private void convertirImagen() {
        if(!imagen64.equals("")) {
            foto = BitmapFactory.decodeFile(imagen64);
            if(foto==null){
                byte[] decodedString = Base64.decode(imagen64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                foto = Bitmap.createScaledBitmap(decodedByte, 100, 100, false);
            }
        }
    }

    public Jugador(String nombre, String apellidos, String nombreDeport, int edad, double altura, double peso, String demarcPri, String demarcSec, String pie, int lesion, String equipoPro, Bitmap f, String tipo, int idEquip) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDeport = nombreDeport;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.demarcPri = demarcPri;
        this.demarcSec = demarcSec;
        this.pie = pie;
        this.lesion = lesion;
        this.equipoPro = equipoPro;
        this.f = f;
        this.tipo = tipo;
        this.idEquip = idEquip;
    }

    public Jugador(int id,String nombre, String apellidos, String nombreDeport, int edad, double altura, double peso, String demarcPri, String demarcSec, String pie, int lesion, String equipoPro, String f, String tipo, int idEquip) {
        this.id=id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDeport = nombreDeport;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.demarcPri = demarcPri;
        this.demarcSec = demarcSec;
        this.pie = pie;
        this.lesion = lesion;
        this.equipoPro = equipoPro;
        this.imagen64= f;
        this.tipo = tipo;
        this.idEquip = idEquip;
    }

    public Jugador(String nombre, String apellidos, String nombreDeport, int edad, double altura, double peso, String demarcPri, String demarcSec, String pie, int lesion, String equipoPro, String tipo, int idEquip) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDeport = nombreDeport;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.demarcPri = demarcPri;
        this.demarcSec = demarcSec;
        this.pie = pie;
        this.lesion = lesion;
        this.equipoPro = equipoPro;
        this.tipo = tipo;
        this.idEquip = idEquip;
    }

    public Jugador(int id, String nombre, String nombreDeport) {
        this.id = id;
        this.nombre = nombre;
        this.nombreDeport = nombreDeport;
    }

    public Jugador(int id,String nombre, String apellidos, String nombreDeport, int edad, double altura, double peso, String demarcPri, String demarcSec, String pie, int lesion, String equipoPro, String tipo, int idEquip) {
        this.id=id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDeport = nombreDeport;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.demarcPri = demarcPri;
        this.demarcSec = demarcSec;
        this.pie = pie;
        this.lesion = lesion;
        this.equipoPro = equipoPro;
        this.tipo = tipo;
        this.idEquip = idEquip;
    }

    public Bitmap getF() {
        convertirImagen();
        return foto;
    }

    public void setF(Bitmap foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreDeport() {
        return nombreDeport;
    }

    public void setNombreDeport(String nombreDeport) {
        this.nombreDeport = nombreDeport;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDemarcPri() {
        return demarcPri;
    }

    public void setDemarcPri(String demarcPri) {
        this.demarcPri = demarcPri;
    }

    public String getDemarcSec() {
        return demarcSec;
    }

    public void setDemarcSec(String demarcSec) {
        this.demarcSec = demarcSec;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public int getLesion() {
        return lesion;
    }

    public void setLesion(int lesion) {
        this.lesion = lesion;
    }

    public String getEquipoPro() {
        return equipoPro;
    }

    public void setEquipoPro(String equipoPro) {
        this.equipoPro = equipoPro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(int idEquip) {
        this.idEquip = idEquip;
    }

    public String getImagen64() {
        return imagen64;
    }

    public void setImagen64(String imagen64) {
        this.imagen64 = imagen64;
    }

}
