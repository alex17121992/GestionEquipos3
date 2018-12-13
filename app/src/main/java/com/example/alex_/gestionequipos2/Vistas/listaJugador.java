package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Controladores.JugadoresAdapter;
import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;
import com.example.alex_.gestionequipos2.Controladores.RecyclerItemTouchHelper;
import com.example.alex_.gestionequipos2.Controladores.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listaJugador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listaJugador extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView rv;
    private List<Jugador> jug;
    private BdJugador bd;
    private JugadoresAdapter jr;
    private View v;
    private SwipeRefreshLayout swipeRefreshLayout;
    public listaJugador() {
        jug=new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listaJugador.
     */
    // TODO: Rename and change types and number of parameters
    public static listaJugador newInstance(String param1, String param2) {
        listaJugador fragment = new listaJugador();
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
        v=inflater.inflate(R.layout.fragment_lista_jugador, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rvJugador);
        bd=new BdJugador(getContext(),"BDEquipos",null,6);
        jug=bd.listadoJugador();
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jug=bd.listadoJugador();
                jr=new JugadoresAdapter(getContext(),jug,new RecyclerViewOnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        //Toast.makeText(v.getContext(),String.valueOf(jug.get(position).getId()),Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getContext(),editarJugador.class);
                        i.putExtra("Id",String.valueOf(jug.get(position).getId()));
                        startActivityForResult(i,0);
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv.setAdapter(jr);
            }
        });

        jr=new JugadoresAdapter(getContext(),jug,new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(v.getContext(),String.valueOf(jug.get(position).getId()),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(),editarJugador.class);
                i.putExtra("Id",String.valueOf(jug.get(position).getId()));
                startActivityForResult(i,0);

            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv);


        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(jr);
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof JugadoresAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
            int id = jug.get(viewHolder.getAdapterPosition()).getId();

            // backup of removed item for undo purpose
            final Jugador deletedItem = jug.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            // remove the item from recycler view
            jr.removeItem(viewHolder.getAdapterPosition());
            long num_reg=bd.borrarJugador(id);
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
