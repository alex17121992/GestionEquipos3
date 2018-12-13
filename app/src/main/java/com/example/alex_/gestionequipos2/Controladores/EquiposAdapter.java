package com.example.alex_.gestionequipos2.Controladores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.Modelos.Equipo;
import com.example.alex_.gestionequipos2.R;

import java.util.List;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.ViewHolder>{

    private  static Context mContext;
    private List<Equipo> equip;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public EquiposAdapter(Context mContext, List<Equipo> equip, RecyclerViewOnItemClickListener
            recyclerViewOnItemClickListener) {
        this.mContext = mContext;
        this.equip = equip;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }
    @NonNull
    @Override
    public EquiposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.itemequipos,parent,false);
        ViewHolder viewHolder= new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNombreEquip.setText(equip.get(i).getNombre());
        //viewHolder.imgFotoEquip.setImageBitmap(equip.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return equip.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNombreEquip;
        private ImageView imgFotoEquip;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombreEquip=(TextView)itemView.findViewById(R.id.txtNombreEquip);
            imgFotoEquip=(ImageView) itemView.findViewById(R.id.imgFotoEquip);

        }
        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    /**
     * Metodo para borrar un item del recycler view en la posicion,la conseguimos con getAdapterPosition y hacemos un nptify para refrescar el RV
     * @param position
     */
    public void removeItem(int position) {
        equip.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    /**
     * Metodo para restaurar un item en caso de no querer borrarlo,le pasamos el objeto que previamente hemos guargado y la posicion como en el metodo anterior
     * y notificamos al RV
     * @param item
     * @param position
     */
    public void restoreItem(Equipo item, int position) {
        equip.add(position, item);
        // notify item added by position

        notifyItemInserted(position);
    }
}
