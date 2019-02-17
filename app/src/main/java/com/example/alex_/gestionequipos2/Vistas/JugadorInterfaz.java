package com.example.alex_.gestionequipos2.Vistas;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex_.gestionequipos2.BuildConfig;
import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

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
    private Uri ruta; //Ruta de la imagen desde la galeria o camara
    private Bitmap imagen; //Imagen en formato Bitmap que hemos decodificado de la Uri
    private String imagen64; //Imagen en base64 que previamente teniamos en el Bitmap,para introducirla en la base de datos
    private int idE;
    private TextView tvTipoJugador;
    private Spinner SpinnerlistaJugador;
    private String rutaArchivo;
    private Jugador j;
    private final int COD_FOTO = 20; //Constante que se usa en el activityforresult para saber si se lanza la camara
    private final int COD_GALERIA = 10; //Constante que se usa en el activityforresult para saber si se lanza la galeria
    private Uri contentUri;
    private String rutaImagen;

    /**
     * Constructor para inizializar todos los componentes de la vista
     */
    public void initComponent() {
        etNDeportivoS = (EditText) findViewById(R.id.etNDeportivoS);
        ivFotoScoutingS = (ImageView) findViewById(R.id.ivFotoScoutingS);
        etNombreS = (EditText) findViewById(R.id.etNombreS);
        etApellidosS = (EditText) findViewById(R.id.etApellidosS);
        etEdadS = (EditText) findViewById(R.id.etEdadS);
        etAlturaS = (EditText) findViewById(R.id.etAlturaS);
        etPesoS = (EditText) findViewById(R.id.etPesoS);
        etPDominanteS = (EditText) findViewById(R.id.etPDominanteS);
        etDemarcacionS = (EditText) findViewById(R.id.etDemarcacionS);
        etDemarcacionHs = findViewById(R.id.etDemarcacionHS);
        etLesionesS = findViewById(R.id.etLesionesS);
        etEquipoPro = findViewById(R.id.etLesionesS);
        anadir = (FloatingActionButton) findViewById(R.id.btnAnadir);
        tvTipoJugador = (TextView) findViewById(R.id.tvListaSpinner);
        SpinnerlistaJugador = (Spinner) findViewById(R.id.spinnerS);
        rutaImagen = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        initComponent();

        ivFotoScoutingS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        bd = new BdJugador(getApplicationContext(), "BDEquipos", null, 6);
        String idEquip = getIntent().getExtras().getString("Id");
        idE = Integer.parseInt(idEquip);

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
                anadirJugador(v);
            }
        });


    }

    /**
     * Metodo para añadir un equipo,recoge el tipo del jugador desde un spinner y la foto desde un imageview.La imagen la convierte en base64
     * y crea un objeto jugador con todos los campos.Por ultimo recoge las filas insertasdas y muestra un mensaje por pantalla
     *
     * @param v es un objeto boton que se recoge desde el onClick
     */
    private void anadirJugador(View v) {
        tipo = SpinnerlistaJugador.getSelectedItem().toString();
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ramos_50px);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
        String imagen = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        validarDatos();
        long num_reg = bd.insertarJugador(j);
        Snackbar.make(v, "Jugador Añadido", Snackbar.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Se ha agregado " + num_reg + " jugador/es", Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para validar los datos de los TextField comprobando y poniendo valores por defecto a los que reciben un casting a numero
     * para evitar errores de conversion,despues creamos el juegador que introduciremos en la BD
     */
    private void validarDatos() {
        tipo = SpinnerlistaJugador.getSelectedItem().toString();
        if (tipo.equals("")) {
            tipo = "J";
        }
        String nombre = etNombreS.getText().toString();
        String apellidos = etApellidosS.getText().toString();
        String nombreDeport = etNDeportivoS.getText().toString();
        int edad = 18;
        double peso = 0.0;
        double altura = 0.0;
        int lesiones = 0;
        if (!etEdadS.getText().toString().equals("")) {
            edad = Integer.parseInt(etEdadS.getText().toString());
        }
        if (!etAlturaS.getText().toString().equals("")) {
            altura = Double.parseDouble(etAlturaS.getText().toString());
        }
        if (!etPesoS.getText().toString().equals("")) {
            peso = Double.parseDouble(etPesoS.getText().toString());
        }
        String demarcPri = etDemarcacionHs.getText().toString();
        String demarcSec = etDemarcacionS.getText().toString();
        String pie = etPDominanteS.getText().toString();
        if (!etLesionesS.getText().toString().equals("")) {
            lesiones = Integer.parseInt(etLesionesS.getText().toString());
        }

        String equipoPro = etEquipoPro.getText().toString();

        if (contentUri != null && !contentUri.getPath().equals("")) {
            rutaImagen = contentUri.getPath();
        }

        j = new Jugador(nombre, apellidos, nombreDeport,
                edad, altura,
                peso, demarcPri,
                demarcSec, pie, lesiones, equipoPro, tipo, idE, rutaImagen);
    }

    // métodos para cargar las imagenes desde la galeria
    public void cargarImagen() {
        final CharSequence[] opciones = {"Tomar foto", "Cargar Imagen de la Galeria", "Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        try {
                            tomarFoto();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 1: {
                        Intent i = new Intent(Intent.ACTION_PICK);
                        i.setType("image/");
                        startActivityForResult(i, COD_GALERIA);
                        break;
                    }
                    case 2: {
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();
    }

    /**
     * Metodo que crea una imagen con un nombre aleatorio con el formato de la hora del sistema,despues copiamos la ruta de el archivo en una variable
     * global para su posterior recuperacion(rutaArchivo).Lanzamos el intent para abrir la camara y con el file provider conseguimos la uri del archivo.
     * Añadimos los permisos para leer o escribir uris y lanzamos el startActivityForResult la
     *
     * @throws IOException
     */
    private void tomarFoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );


        rutaArchivo = image.getAbsolutePath();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri rutaUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", image);
        i.putExtra(MediaStore.EXTRA_OUTPUT, rutaUri);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(i, COD_FOTO);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case COD_FOTO: {
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(rutaArchivo);
                contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
                Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());
                ivFotoScoutingS.setImageBitmap(bitmap);
                break;
            }
            case COD_GALERIA: {
                try {
                    Uri rutaG = data.getData();
                    Bitmap selectedImageBitmap = null;
                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), rutaG);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ivFotoScoutingS.setImageBitmap(selectedImageBitmap);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
                    rutaImagen = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

}

