package com.example.alex_.gestionequipos2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alex_.gestionequipos2.Controladores.BdJugador;
import com.example.alex_.gestionequipos2.Controladores.EquiposAdapter;
import com.example.alex_.gestionequipos2.Modelos.Equipo;
import com.example.alex_.gestionequipos2.R;
import com.example.alex_.gestionequipos2.Controladores.RecyclerItemTouchHelper2;
import com.example.alex_.gestionequipos2.Controladores.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class ListaEquipos extends Fragment implements RecyclerItemTouchHelper2.RecyclerItemTouchHelperListener {

    private RecyclerView rv;
    private BdJugador bd;
    private Cursor c;
    private List<Equipo> equip;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EquiposAdapter sa;

    public ListaEquipos() {
        equip = new ArrayList<>();
    }

    public static ListaEquipos newInstance() {
        ListaEquipos fragment = new ListaEquipos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_equipos, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rvEquipos);
        bd = new BdJugador(getContext(), "BDEquipos", null, 6);
        equip = bd.listadoEquipo();
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout2);
        refrescarLista();
        swipeRefreshLayout.setRefreshing(false);
        sa = new EquiposAdapter(getContext(), equip, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i = new Intent(getContext(), JugadorInterfaz.class);
                int id = equip.get(position).getId();
                Log.d("Valor",String.valueOf(equip.get(position).getNumJug()));
                i.putExtra("Id", String.valueOf(id));
                startActivity(i);
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper2(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv);


        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(sa);
        return v;
    }

    /**
     * Metodo para refrescar el RecyclerView haciendo swipe vertical,implementarmos el listener onRefreschListener
     * y sobreescribimos el metodo onRefresh,recogemos el arraylist de equipos desde la BD,implementamos el listener del RecyclerView
     * y dentro creamos un intent para llamar a la interfaz del jugador para editarlo.Le pasamos un Bundle con la id del equipo y lanzamos el activity.
     * Si no hacemos click en un elemento del Recycler usa el metodo setRefresing para refrescar el Recycler y pasamos al adapdatador al Recycler.
     */
    private void refrescarLista() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                equip = bd.listadoEquipo();
                sa = new EquiposAdapter(getContext(), equip, new RecyclerViewOnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Intent i = new Intent(getContext(), JugadorInterfaz.class);
                        int id = equip.get(position).getId();
                        i.putExtra("Id", String.valueOf(id));
                        startActivity(i);
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv.setAdapter(sa);
            }
        });
    }

    /**
     * El metodo onSwiped permite eliminar un elemento del RecyclerView haciendo swipe hacia la izquierda.Guardamos una copia del equipo eliminado
     * guardamos una copia del index y comprobamos el campo numJug que contiene el numero de jugadores del equipo y siempre que sea cero podra ser eliminado.
     * Guardamos la id del equipo que es el numero en la lista y lo eliminamos del Recycler.Llamamos al metodo de borrar de la base de datos y ya quedaria
     * eliminado.
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        final Equipo deletedItem = equip.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();
        if (viewHolder instanceof EquiposAdapter.ViewHolder) {
            if(equip.get(viewHolder.getAdapterPosition()).getNumJug()==0) {
                // get the removed item name to display it in snack bar
                int id = equip.get(viewHolder.getAdapterPosition()).getId();

                // backup of removed item for undo purpose
                // remove the item from recycler view
                sa.removeItem(viewHolder.getAdapterPosition());
                long num_reg = bd.borrarEquipo(id);
            }else {
                Toast.makeText(getContext(),"Error,no se pueden eliminar equipos con jugadores",Toast.LENGTH_LONG).show();
                sa.removeItem(viewHolder.getAdapterPosition());
                sa.restoreItem(deletedItem,deletedIndex);
            }
        }
    }

}
