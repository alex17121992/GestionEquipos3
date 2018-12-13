package com.example.alex_.gestionequipos2.Vistas;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JugadorInterfaz extends AppCompatActivity {

    private EditText etNDeportivoS;
    private ImageView ivFotoScoutingS;
    private EditText etNombreS;
    private EditText etApellidosS;
    private EditText etEdadS;
    private EditText etAlturaS;
    private EditText etPesoS;
    private EditText etPDominanteS;
    private EditText etDemarcacionS;
    private EditText etDemarcacionHs;
    private EditText etEquipoPro;
    private EditText etLesionesS;
    private String tipo;
    private FloatingActionButton anadir;
    private BdJugador bd;
    private Uri ruta;
    private Bitmap imagen;
    private String imagen64;
    private int idE;
    private TextView tvTipoJugador;
    private Spinner SpinnerlistaJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        etNDeportivoS = (EditText) findViewById(R.id.etNDeportivoS);
        ivFotoScoutingS = (ImageView) findViewById(R.id.ivFotoScoutingS);
        etNombreS = (EditText) findViewById(R.id.etNombreS);
        etApellidosS = (EditText) findViewById(R.id.etApellidosS);
        etEdadS = (EditText) findViewById(R.id.etEdadS);
        etAlturaS = (EditText) findViewById(R.id.etAlturaS);
        etPesoS = (EditText) findViewById(R.id.etPesoS);
        etPDominanteS = (EditText) findViewById(R.id.etPDominanteS);
        etDemarcacionS = (EditText) findViewById(R.id.etDemarcacionS);
        etDemarcacionHs=findViewById(R.id.etDemarcacionHS);
        etLesionesS=findViewById(R.id.etLesionesS);
        etEquipoPro=findViewById(R.id.etLesionesS);
        anadir = (FloatingActionButton) findViewById(R.id.btnAnadir);
        ivFotoScoutingS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        bd = new BdJugador(getApplicationContext(), "BDEquipos", null, 6);
        String idEquip = getIntent().getExtras().getString("Id");
        idE = Integer.parseInt(idEquip);
        tvTipoJugador = (TextView) findViewById(R.id.tvListaSpinner);
        SpinnerlistaJugador = (Spinner) findViewById(R.id.spinnerS);

        // SPINNER:
        // Creamos un ArrayAdapter pasándole como parámetro el xml creado con la lista desplegable
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listaTipojugador, android.R.layout.simple_spinner_item);

        // le pasamos al spinner el adapter antes creado
        SpinnerlistaJugador.setAdapter(adapter);

        // FLOATINGACTIONBUTTON:
        // animacion del floatingActionButton
        final FloatingActionButton añadir = (FloatingActionButton) findViewById(R.id.btnAnadir);
        añadir.setScaleX(0);
        añadir.setScaleY(0);


        // comprobamos la version de android para que no falle la app.
        // si es correcta, creamos un interpolador, para luego añadirlo al boton
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            añadir.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(1000)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {


                        }
                    });
        }


        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo=SpinnerlistaJugador.getSelectedItem().toString();
                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.ramos_50px);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                String imagen = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                Jugador j = new Jugador(etNombreS.getText().toString(), etApellidosS.getText().toString(), etNDeportivoS.getText().toString(),
                        Integer.parseInt(etEdadS.getText().toString()), Double.parseDouble(etAlturaS.getText().toString()),
                        Double.parseDouble(etPesoS.getText().toString()), etDemarcacionHs.getText().toString(),
                        etDemarcacionS.getText().toString(), etPDominanteS.getText().toString(),Integer.parseInt(etLesionesS.getText().toString()),etEquipoPro.getText().toString(),tipo, idE,imagen);
                long num_reg = bd.insertarJugador(j);
                Snackbar.make(v, "Jugador Añadido", Snackbar.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Se ha agregado " + num_reg + " jugador/es", Toast.LENGTH_SHORT).show();
            }
        });


    }


    // métodos para cargar las imagenes desde la galeria
    public void cargarImagen() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i, 10);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

