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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Equipos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Equipos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Equipos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Equipo e;
    private BdJugador bd;
    private TextInputEditText nom;
    private Uri ruta;
    private ImageView img;
    private String imagen64;
    private OnFragmentInteractionListener mListener;

    public Equipos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Equipos.
     */
    // TODO: Rename and change types and number of parameters
    public static Equipos newInstance(String param1, String param2) {
        Equipos fragment = new Equipos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
