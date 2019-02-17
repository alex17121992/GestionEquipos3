package com.example.alex_.gestionequipos2.Controladores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex_.gestionequipos2.Modelos.Jugador;
import com.example.alex_.gestionequipos2.R;

import java.util.List;

public class JugadoresAdapter extends RecyclerView.Adapter<JugadoresAdapter.ViewHolder> {

    private static Context mContext;
    private List<Jugador> jug;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public JugadoresAdapter(Context mContext, List<Jugador> jug, RecyclerViewOnItemClickListener
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        viewHolder.tvNomD.setText(jug.get(i).getNombreDeport());
        viewHolder.tvNomJ.setText(jug.get(i).getNombre());
        viewHolder.tvDemar.setText(jug.get(i).getDemarcPri());
        if(jug.get(i).getF()==null){
            viewHolder.imgFoto.setImageResource(R.drawable.ramos_50px);
        }else {
            viewHolder.imgFoto.setImageBitmap(jug.get(i).getF());
        }
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
        private RelativeLayout content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvNomD=(TextView)itemView.findViewById(R.id.tvNomD);
            tvNomJ=(TextView)itemView.findViewById(R.id.tvNomJ);
            tvDemar=(TextView)itemView.findViewById(R.id.tvDemar);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            content=itemView.findViewById(R.id.content_item);

        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public void removeItem(int position) {
        jug.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Jugador item, int position) {
        jug.add(position, item);
        // notify item added by position

        notifyItemInserted(position);
    }
}
