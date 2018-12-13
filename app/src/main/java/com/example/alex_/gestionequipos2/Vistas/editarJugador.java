package com.example.alex_.gestionequipos2.Vistas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class editarJugador extends AppCompatActivity implements ListaJugadores.OnFragmentInteractionListener,listaJugador.OnFragmentInteractionListener,listaScouting.OnFragmentInteractionListener
{

    private EditText etNDeportivoS;
    private ImageView ivFotoScoutingS;
    private EditText etNombreS;
    private EditText etApellidosS;
    private EditText etEdadS;
    private EditText etAlturaS;
    private EditText etPesoS;
    private EditText etPDominanteS;
    private EditText etDemarcacionS;
    private FloatingActionButton anadir;
    private BdJugador bd;
    private String id;
    private Uri ruta;
    private Bitmap imagen;
    private String imagen64;
    private Jugador jug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jugador);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null) {
            id = parametros.getString("Id");

        }

        etNDeportivoS = (EditText) findViewById(R.id.etNDeportivoS);
        ivFotoScoutingS = (ImageView) findViewById(R.id.ivFotoScoutingS);
        etNombreS = (EditText) findViewById(R.id.etNombreS);
        etApellidosS = (EditText) findViewById(R.id.etApellidosS);
        etEdadS = (EditText) findViewById(R.id.etEdadS);
        etAlturaS = (EditText) findViewById(R.id.etAlturaS);
        etPesoS = (EditText) findViewById(R.id.etPesoS);
        etPDominanteS = (EditText) findViewById(R.id.etPDominanteS);
        etDemarcacionS = (EditText) findViewById(R.id.etDemarcacionS);
        anadir=(FloatingActionButton) findViewById(R.id.btnAnadir);
        ivFotoScoutingS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        bd=new BdJugador(this,"BDEquipos",null,6);
        cargarDatos();
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador j = new Jugador(jug.getId(),etNombreS.getText().toString(),etApellidosS.getText().toString(),etNDeportivoS.getText().toString()
                        ,Integer.parseInt(etEdadS.getText().toString()),Double.parseDouble(etAlturaS.getText().toString()),Double.parseDouble(etPesoS.getText().toString()),etDemarcacionS.getText().toString(),
                        etDemarcacionS.getText().toString(),etPDominanteS.getText().toString(),0,"Motril","J",jug.getIdEquip());
                long num_reg=bd.editarJugador(j);
                Toast.makeText(getApplicationContext(),"Se han editado " + num_reg + " jugador/es",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cargarImagen()
    {
        Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK)
        {
            try {
                ruta = data.getData();

                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ruta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivFotoScoutingS.setImageBitmap(selectedImageBitmap);
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

    public void cargarDatos(){
        int ide=Integer.parseInt(id);
        jug=bd.listadoJugadorEd(ide);
        // ivFotoScoutingS.setImageBitmap(decodedByte);
            etNombreS.setText(jug.getNombre());
            etApellidosS.setText(jug.getApellidos());
            etNDeportivoS.setText(jug.getNombreDeport());
            etEdadS.setText(String.valueOf(jug.getEdad()));
            etAlturaS.setText(String.valueOf(jug.getAltura()));
            etPesoS.setText(String.valueOf(jug.getPeso()));
            etDemarcacionS.setText(jug.getDemarcSec());
            etPDominanteS.setText(jug.getPie());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
