package com.example.alex_.gestionequipos2.Vistas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.R;



import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnadirPartido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnadirPartido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnadirPartido extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match


    private ImageView imagenPartido;
    private Button btFechaPartido;
    private TextView tvFechaPartido;
    private TextView tvDiaDePartido;
    private TextView etEquipoRival;
    private TextView etTuEquipo;
    private FloatingActionButton floaBtAnadirPartido;


    private OnFragmentInteractionListener mListener;

    public AnadirPartido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AnadirPartido.
     */
    // TODO: Rename and change types and number of parameters
    public static AnadirPartido newInstance(String param1, String param2) {
        AnadirPartido fragment = new AnadirPartido();
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

        View vista = inflater.inflate(R.layout.fragment_anadir_partido, container, false);


        imagenPartido = (ImageView) vista.findViewById(R.id.imagenPartido);
        btFechaPartido = (Button) vista.findViewById(R.id.btFechaPartido);
        tvFechaPartido = (TextView) vista.findViewById(R.id.tvFechaPartido);
        tvDiaDePartido = (TextView) vista.findViewById(R.id.tvDiaDePartido);
        etEquipoRival = (TextView) vista.findViewById(R.id.etEquipoRival);
        etEquipoRival = (TextView) vista.findViewById(R.id.etTuEquipo);


        btFechaPartido.setOnClickListener(this);
        floaBtAnadirPartido = (FloatingActionButton) vista.findViewById(R.id.floaBtAnadirPartido);

        floaBtAnadirPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convocatoria convocatoria = new convocatoria();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,convocatoria).commit();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == btFechaPartido){
            // Instanciamos a Calendar
            final Calendar calendario = Calendar.getInstance();
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
            int ano = calendario.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvFechaPartido.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
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