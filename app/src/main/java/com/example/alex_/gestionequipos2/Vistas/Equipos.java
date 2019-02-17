package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Modelos.Equipo;
import com.example.alex_.gestionequipos2.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;



public class Equipos extends Fragment {

    private Equipo e;
    private BdJugador bd;
    private TextInputEditText nom;
    private Uri ruta;
    private ImageView img;
    private String imagen64;

    public Equipos() {

    }

    public static Equipos newInstance() {
        Equipos fragment = new Equipos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_equipos, container, false);
        nom = (TextInputEditText)v.findViewById(R.id.txtEquipo);
        FloatingActionButton anadir=(FloatingActionButton)v.findViewById(R.id.anadirEquipo);
        img = (ImageView)v.findViewById(R.id.imgEquipo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        bd = new BdJugador(getContext(),"BDEquipos",null,6);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre;
                nombre = nom.getText().toString();
                if (nombre.equals("")) {
                    Toast.makeText(getContext(), "Introduce un nombre valido", Toast.LENGTH_LONG).show();
                } else {
                    e = new Equipo(nombre);
                    long res = bd.insertarEquipo(e);
                    Toast.makeText(getContext(), "Se ha insertado " + res + " equipo/os", Toast.LENGTH_LONG).show();
                    //Intent i = new Intent(getContext(),ListaEquipos.class);
                    //startActivity(i);
                    ListaEquipos l = new ListaEquipos();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,l).commit();
                }
            }
        });

        return v;
    }

    public void cargarImagen()
    {
        Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i,10);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK)
        {
            try {
                ruta = data.getData();

                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), ruta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                img.setImageBitmap(selectedImageBitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                imagen64 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
