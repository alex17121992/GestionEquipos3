package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Controladores.JugadoresAdapter;
import com.example.alex_.gestionequipos2.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PartidoTuEquipo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PartidoTuEquipo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartidoTuEquipo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    private OnFragmentInteractionListener mListener;
    private TableLayout tablaPartido;
    private TextView tvGolesPartido;
    private TextView tvMinutGolPartido;
    private TextView tvJugadorGolPartido;
    private EditText etGolPatido;
    private EditText etMinutoGolPartido;
    private Spinner spiJugadorGolPartido;

    private TextView tvCambiosCabecera;
    private TextView tvCambioPartido;
    private TextView tvMinutCambioPartido;
    private TextView tvJugadorCambioPartido;
    private EditText etCambiopartido;
    private EditText etMinutoCambioPartido;
    private Spinner  spiJugadorCambioPartido;

    private TextView tvTarjetasACabecera;
    private TextView tvTarjetasAPartido;
    private TextView tvMinutoTarjetaAPartido;
    private TextView tvJugadorTarjetaAPartido;
    private EditText etTarjetaAPartido;
    private EditText etMinutoTarjetaAPartido;
    private Spinner  spiJugadorTarjetaAPartido;

    private TextView tvTarjetasRCabecera;
    private TextView tvTarjetasRPartido;
    private TextView tvMinutoTarjetaRPartido;
    private TextView tvJugadorTarjetaRPartido;
    private EditText etTarjetaRPartido;
    private EditText etMinutoTarjetaRPartido;
    private Spinner  spiJugadorTarjetaRPartido;

    // Para la base de datos
    private BdJugador bd;
    private JugadoresAdapter jr;
    private List<String> jug;


    public PartidoTuEquipo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PartidoTuEquipo.
     */
    // TODO: Rename and change types and number of parameters
    public static PartidoTuEquipo newInstance(String param1, String param2) {
        PartidoTuEquipo fragment = new PartidoTuEquipo();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_partido_tu_equipo, container, false);


         // Referencias de todos los elementos dell fragment part

        tablaPartido = (TableLayout) vista.findViewById(R.id.tablaPartido) ;
        tvGolesPartido = (TextView) vista.findViewById(R.id.tvGolesPartido) ;
        tvMinutGolPartido = (TextView) vista.findViewById(R.id.tvMinutGolPartido) ;
        tvJugadorGolPartido = (TextView) vista.findViewById(R.id.tvJugadorGolPartido);
        etGolPatido = (EditText) vista.findViewById(R.id.etGolPatido);
        etMinutoGolPartido = (EditText) vista.findViewById(R.id.etMinutoGolPartido);
        spiJugadorGolPartido = (Spinner) vista.findViewById(R.id.spiJugadorGolPartido);

        tvCambiosCabecera = (TextView) vista.findViewById(R.id.tvCambiosCabecera);
        tvCambioPartido = (TextView) vista.findViewById(R.id.tvCambioPartido);
        tvMinutCambioPartido = (TextView) vista.findViewById(R.id.tvMinutCambioPartido);
        tvJugadorCambioPartido = (TextView) vista.findViewById(R.id.tvJugadorCambioPartido);
        etCambiopartido = (EditText) vista.findViewById(R.id.etCambiopartido);
        etMinutoCambioPartido = (EditText) vista.findViewById(R.id.etMinutoCambioPartido);
        spiJugadorCambioPartido = (Spinner) vista.findViewById(R.id.spiJugadorCambioPartido);

        tvTarjetasACabecera = (TextView) vista.findViewById(R.id.tvTarjetasACabecera);
        tvTarjetasAPartido = (TextView) vista.findViewById(R.id.tvTarjetasAPartido);
        tvMinutoTarjetaAPartido = (TextView) vista.findViewById(R.id.tvMinutoTarjetaAPartido);
        tvJugadorTarjetaAPartido = (TextView) vista.findViewById(R.id.tvJugadorTarjetaAPartido);
        etTarjetaAPartido = (EditText) vista.findViewById(R.id.etTarjetaAPartido);
        etMinutoTarjetaAPartido = (EditText) vista.findViewById(R.id.etMinutoTarjetaAPartido);
        spiJugadorTarjetaAPartido = (Spinner) vista.findViewById(R.id.spiJugadorTarjetaAPartido);

        tvTarjetasRCabecera = (TextView) vista.findViewById(R.id.tvTarjetasRCabecera);
        tvTarjetasRPartido = (TextView) vista.findViewById(R.id.tvTarjetasRPartido);
        tvMinutoTarjetaRPartido = (TextView) vista.findViewById(R.id.tvMinutoTarjetaRPartido);
        tvJugadorTarjetaRPartido = (TextView) vista.findViewById(R.id.tvJugadorTarjetaRPartido);
        etTarjetaRPartido = (EditText) vista.findViewById(R.id.etTarjetaRPartido);
        etMinutoTarjetaRPartido = (EditText) vista.findViewById(R.id.etMinutoTarjetaRPartido);
        spiJugadorTarjetaRPartido = (Spinner) vista.findViewById(R.id.spiJugadorTarjetaRPartido);



        /* Conexion con la base de datos existente y creamos un ArrayList<String>, al que pasamos el metodo
        obtenerNombrejugador() que está en BdJugador */
        bd=new BdJugador(getContext(),"BDEquipos",null,6);
        jug=bd.obtenerNombrejugador();

        /* creamos un ArrayAdapter pasándole simple_spinner_item como layout y el array antes creado*/
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (getContext(),android.R.layout.simple_spinner_item,jug);

        /* pasamos el adaptador a los diferentes spinners */
        spiJugadorGolPartido.setAdapter(adaptador);
        spiJugadorCambioPartido.setAdapter(adaptador);
        spiJugadorTarjetaAPartido.setAdapter(adaptador);
        spiJugadorTarjetaRPartido.setAdapter(adaptador);

        // funcion para recoger que item ha sido seleccionado del spinner, igualando el resultado en una variable.
        String gol = (String) spiJugadorGolPartido.getSelectedItem();

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
