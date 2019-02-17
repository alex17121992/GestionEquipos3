package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PartidoRival.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PartidoRival#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartidoRival extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    private OnFragmentInteractionListener mListener;
    private TableLayout tablaPartidoRival;
    private TextView tvGolesRivPartido;
    private TextView tvMinutGolRivPartido;
    private EditText etGolRivPatido;
    private EditText etMinutoGolRivPartido;

    private TextView tvCambiosRivCabecera;
    private TextView tvCambioRivPartido;
    private TextView tvMinutCambioRivPartido;
    private EditText etCambioRivpartido;
    private EditText etMinutoCambioRivPartido;

    private TextView tvTarjetasARivCabecera;
    private TextView tvTarjetasARivPartido;
    private TextView tvMinutoTarjetaARivPartido;
    private EditText etTarjetaARivPartido;
    private EditText etMinutoTarjetaARivPartido;

    private TextView tvTarjetasRRivCabecera;
    private TextView tvTarjetasRRivPartido;
    private TextView tvMinutoTarjetaRRivPartido;
    private EditText etTarjetaRRivPartido;
    private EditText etMinutoTarjetaRRivPartido;


    public PartidoRival() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PartidoTuEquipo.
     */
    // TODO: Rename and change types and number of parameters
    public static PartidoRival newInstance(String param1, String param2) {
        PartidoRival fragment = new PartidoRival();
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
        View vista = inflater.inflate(R.layout.fragment_partido_rival, container, false);


         // Referencias de todos los elementos del fragment partidoTuEquipo

        tablaPartidoRival = (TableLayout) vista.findViewById(R.id.tablaPartido) ;

        tvGolesRivPartido = (TextView) vista.findViewById(R.id.tvGolesPartido) ;
        tvMinutGolRivPartido = (TextView) vista.findViewById(R.id.tvMinutGolPartido) ;
        etGolRivPatido = (EditText) vista.findViewById(R.id.etGolPatido);
        etMinutoGolRivPartido = (EditText) vista.findViewById(R.id.etMinutoGolPartido);

        tvCambiosRivCabecera = (TextView) vista.findViewById(R.id.tvCambiosCabecera);
        tvCambioRivPartido = (TextView) vista.findViewById(R.id.tvCambioPartido);
        tvMinutCambioRivPartido = (TextView) vista.findViewById(R.id.tvMinutCambioPartido);
        etCambioRivpartido = (EditText) vista.findViewById(R.id.etCambiopartido);
        etMinutoCambioRivPartido = (EditText) vista.findViewById(R.id.etMinutoCambioPartido);

        tvTarjetasARivCabecera = (TextView) vista.findViewById(R.id.tvTarjetasACabecera);
        tvTarjetasARivPartido = (TextView) vista.findViewById(R.id.tvTarjetasAPartido);
        tvMinutoTarjetaARivPartido = (TextView) vista.findViewById(R.id.tvMinutoTarjetaAPartido);
        etTarjetaARivPartido = (EditText) vista.findViewById(R.id.etTarjetaAPartido);
        etMinutoTarjetaARivPartido = (EditText) vista.findViewById(R.id.etMinutoTarjetaAPartido);

        tvTarjetasRRivCabecera = (TextView) vista.findViewById(R.id.tvTarjetasRCabecera);
        tvTarjetasRRivPartido = (TextView) vista.findViewById(R.id.tvTarjetasRPartido);
        tvMinutoTarjetaRRivPartido = (TextView) vista.findViewById(R.id.tvMinutoTarjetaRPartido);
        etTarjetaRRivPartido = (EditText) vista.findViewById(R.id.etTarjetaRPartido);
        etMinutoTarjetaRRivPartido = (EditText) vista.findViewById(R.id.etMinutoTarjetaRPartido);


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
