package com.example.alex_.gestionequipos2.Controladores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;

import java.util.List;

public class ScoutingAdapter extends RecyclerView.Adapter<ScoutingAdapter.ViewHolder> {

    private static Context mContext;
    private List<Jugador> jug;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public ScoutingAdapter(Context mContext, List<Jugador> jug, RecyclerViewOnItemClickListener
            recyclerViewOnItemClickListener) {
        this.mContext = mContext;
        this.jug = jug;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.itemjugador,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoutingAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvNomD.setText(jug.get(i).getNombreDeport());
        viewHolder.tvNomJ.setText(jug.get(i).getNombre());
        viewHolder.tvDemar.setText(jug.get(i).getDemarcPri());
        viewHolder.imgFoto.setImageBitmap(jug.get(i).getF());
    }

    @Override
    public int getItemCount() {
        return jug.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvId;
        private TextView tvNomD;
        private TextView tvNomJ;
        private TextView tvDemar;
        private ImageButton btnEditar;
        private ImageButton btnBorrar;
        private ImageView imgFoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvNomD=(TextView)itemView.findViewById(R.id.tvNomD);
            tvNomJ=(TextView)itemView.findViewById(R.id.tvNomJ);
            tvDemar=(TextView)itemView.findViewById(R.id.tvDemar);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);

        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
