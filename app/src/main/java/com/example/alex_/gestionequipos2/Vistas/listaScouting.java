package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;
import com.example.alex_.gestionequipos2.Controladores.RecyclerViewOnItemClickListener;
import com.example.alex_.gestionequipos2.Controladores.ScoutingAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listaScouting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listaScouting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listaScouting extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvSc;
    private List<Jugador> listScout;
    private BdJugador bd;
    private Cursor c;

    public listaScouting() {
        listScout=new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listaScouting.
     */
    // TODO: Rename and change types and number of parameters
    public static listaScouting newInstance(String param1, String param2) {
        listaScouting fragment = new listaScouting();
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
        View v=inflater.inflate(R.layout.fragment_lista_scouting, container, false);
        rvSc=(RecyclerView)v.findViewById(R.id.rvScout);
        bd=new BdJugador(getContext(),"BDEquipos",null,6);
        listScout=bd.listadoJugadorS();
        ScoutingAdapter sa=new ScoutingAdapter(getContext(),listScout,new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getContext(),String.valueOf(listScout.get(position).getId()),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(),editarJugador.class);
                i.putExtra("Id",String.valueOf(listScout.get(position).getId()));
                startActivityForResult(i,0);
            }
        });
        rvSc.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSc.setAdapter(sa);
        return v;
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
