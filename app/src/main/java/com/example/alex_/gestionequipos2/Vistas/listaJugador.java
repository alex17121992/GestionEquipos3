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



public class listaJugador extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView rv;
    private List<Jugador> jug;
    private BdJugador bd;
    private JugadoresAdapter jr;
    private View v;
    private SwipeRefreshLayout swipeRefreshLayout;

    public listaJugador() {
        jug=new ArrayList<>();
    }

    // TODO: Rename and change types and number of parameters
    public static listaJugador newInstance() {
        listaJugador fragment = new listaJugador();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}
