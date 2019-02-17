package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Controladores.JugadoresAdapter;
import com.example.alex_.gestionequipos2.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link convocatoria.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link convocatoria#newInstance} factory method to
 * create an instance of this fragment.
 */
public class convocatoria extends Fragment implements menuPartido.OnFragmentInteractionListener{

    private OnFragmentInteractionListener mListener;

    private TableLayout tablaConvocatoria;
    private ImageView ivPorteroConvo;
    private ImageView ivLateralDereConvo;
    private ImageView ivCentralDereConvo;
    private ImageView ivCentralIzquiConvo;
    private ImageView ivLateralIzquiConvo;
    private ImageView ivInterDereConvo;
    private ImageView ivInterIzquiConvo;
    private ImageView ivMedioCentro;
    private ImageView ivDelanteroCentro;
    private ImageView ivDelanteroDereConvo;
    private ImageView ivDelanteroIzquiConvo;

    private Spinner spinPorteroConvo;
    private Spinner spinLateralDereConvo;
    private Spinner spinCentralDereConvo;
    private Spinner spinCentralIzquiConvo;
    private Spinner spinLateralIzquiConvo;
    private Spinner spinInterDereConvo;
    private Spinner spinInteIzquiConvo;
    private Spinner spinMedioCentroConvo;
    private Spinner spinDelanteroDereConvo;
    private Spinner spinDelanteroIzquiConvo;
    private Spinner spinDelanteroCentro;
    private FloatingActionButton floaBtGuardarConvo;
    private String portero;

    // Para la base de datos
    private BdJugador bd;
    private JugadoresAdapter jr;
    private List<String> jug;


    public convocatoria() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment convocatoria.
     */
    // TODO: Rename and change types and number of parameters
    public static convocatoria newInstance(String param1, String param2) {
        convocatoria fragment = new convocatoria();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_convocatoria, container, false);

        tablaConvocatoria = (TableLayout) vista.findViewById(R.id.tablaConvocatoria);
        ivLateralDereConvo = (ImageView) vista.findViewById(R.id.ivLateralDereConvo);
        ivLateralIzquiConvo =(ImageView) vista.findViewById(R.id.ivLateralIzquiConvo);
        ivCentralDereConvo =(ImageView) vista.findViewById(R.id.ivCentralDereConvo);
        ivCentralIzquiConvo =(ImageView) vista.findViewById(R.id.ivCentralIzquiConvo);

        ivInterDereConvo =(ImageView) vista.findViewById(R.id.ivInteriorDereConvo);
        ivMedioCentro =(ImageView) vista.findViewById(R.id.ivMedioCentroConvo);
        ivDelanteroCentro =(ImageView) vista.findViewById(R.id.ivDelanteroCentroConvo);
        ivInterIzquiConvo =(ImageView) vista.findViewById(R.id.ivInteriorIzquiConvo);
        ivDelanteroDereConvo =(ImageView) vista.findViewById(R.id.ivDelanteroDereconvo);
        ivDelanteroIzquiConvo =(ImageView) vista.findViewById(R.id.ivDelanteroIzquiConvo);

        spinPorteroConvo = (Spinner) vista.findViewById(R.id.spinPorteroConvo);
        spinLateralDereConvo =(Spinner) vista.findViewById(R.id.spinLateralDereConvo);
        spinCentralDereConvo =(Spinner) vista.findViewById(R.id.spinCentralDereConvo);
        spinCentralIzquiConvo =(Spinner) vista.findViewById(R.id.spinCentralIzquiConvo);
        spinLateralIzquiConvo =(Spinner) vista.findViewById(R.id.spinLateralIzquiConvo);
        spinInterDereConvo =(Spinner) vista.findViewById(R.id.spinInteriorDereConvo);
        spinDelanteroCentro =(Spinner) vista.findViewById(R.id.spinDelanteroCentroConvo);
        spinMedioCentroConvo =(Spinner) vista.findViewById(R.id.spinMedioCentroConvo);
        spinInteIzquiConvo =(Spinner) vista.findViewById(R.id.spinInteriorIzquiConvo);
        spinDelanteroDereConvo =(Spinner) vista.findViewById(R.id.spinDelanteroDereConvo);
        spinDelanteroIzquiConvo =(Spinner) vista.findViewById(R.id.spinDelanteroIzquiConvo);

        floaBtGuardarConvo =(FloatingActionButton) vista.findViewById(R.id.floaBtGuardarConvo);

        /* Conexion con la base de datos existente y creamos un ArrayList<String>, al que pasamos el metodo
        obtenerNombrejugador() que está en BdJugador */
        bd=new BdJugador(getContext(),"BDEquipos",null,6);
        jug=bd.obtenerNombrejugador();

        /* creamos un ArrayAdapter pasándole simple_spinner_item como layout y el array antes creado*/
       ArrayAdapter<String> adaptador=new ArrayAdapter
                (getContext(),R.layout.spinner_item_personalizado,jug);



        /* pasamos el adaptador a los diferentes spinners */
        spinPorteroConvo.setAdapter(adaptador);
        spinLateralDereConvo.setAdapter(adaptador);
        spinCentralDereConvo.setAdapter(adaptador);
        spinCentralIzquiConvo.setAdapter(adaptador);
        spinLateralIzquiConvo.setAdapter(adaptador);
        spinInterDereConvo.setAdapter(adaptador);
        spinDelanteroCentro.setAdapter(adaptador);
        spinMedioCentroConvo.setAdapter(adaptador);
        spinInteIzquiConvo.setAdapter(adaptador);
        spinDelanteroDereConvo.setAdapter(adaptador);
        spinDelanteroIzquiConvo.setAdapter(adaptador);

        /* funcion (a modo de ejemplo)get para recoger que item ha sido seleccion en el spiner,
         igualando el resultado en una variable
          */
                portero = (String) spinPorteroConvo.getSelectedItem();

        // al pulsar en el boton, vamos al fragment menuPartido, previamente creado.
        floaBtGuardarConvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPartido partido =  new menuPartido();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, partido).commit();
            }
        });



        return vista;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

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
