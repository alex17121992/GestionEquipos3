package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex_.gestionequipos2.Controladores.ViewPageAdapterDos;
import com.example.alex_.gestionequipos2.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link menuPartido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link menuPartido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menuPartido extends Fragment implements
        PartidoTuEquipo.OnFragmentInteractionListener,PartidoRival.OnFragmentInteractionListener {

    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPa;
    private Boolean BotonOpen = false;
    private FloatingActionMenu floaBtMenu;
    private FloatingActionButton floaBtGuardarReqist, floaBtTermiPartido;
    private OnFragmentInteractionListener mListener;

    public menuPartido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ListaJugadores.
     */
    // TODO: Rename and change types and number of parameters
    public static menuPartido newInstance(String param1, String param2) {
        menuPartido fragment = new menuPartido();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.fragment_menu_partido, container, false);

        viewPa=(ViewPager)vista.findViewById(R.id.contenedor);


        floaBtMenu =(FloatingActionMenu) vista.findViewById(R.id.floaBtMenu);
        floaBtGuardarReqist =(FloatingActionButton) vista.findViewById(R.id.floaBtGuardarReqist);
        floaBtTermiPartido =(FloatingActionButton) vista.findViewById(R.id.floaBtTermiPartido);


        // metodos OnClickListener de los botones flotantes
        floaBtMenu.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      });

        floaBtGuardarReqist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        floaBtTermiPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // creamos un viewpager
        ViewPageAdapterDos viewPager = new ViewPageAdapterDos(getFragmentManager());

        // creamos los fragtment que queremos mostrar
        PartidoTuEquipo partidoEquipo = new PartidoTuEquipo();
        PartidoRival partidoR = new PartidoRival();

        viewPager.anadirFragment(partidoEquipo,"Tu Equipo");
        viewPager.anadirFragment(partidoR,"Rival");

        viewPa.setAdapter(viewPager);
        tabs = vista.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPa);



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
