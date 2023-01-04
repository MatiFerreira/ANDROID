package com.example.recyclerview.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.modelos.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Mathias Ferreira
 * **/
public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    private List<Movie>pelicula;
    private int layout;
    private OnItemClickListener listener;

    public myadapter(List<Movie>pelicula,int layout,OnItemClickListener listener){
         this.layout = layout;
         this.listener = listener;
         this.pelicula = pelicula;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false); /*INFLAMOS LA VISTA*/
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pelicula.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return pelicula.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView textViewname;
        public ImageView portada;
        public  ViewHolder(View view){
            super(view);
            textViewname = view.findViewById(R.id.textviewtitle);
            portada = view.findViewById(R.id.imagenposter);
        }

        public void bind(final Movie pelicula,final OnItemClickListener listener){
            //Procesamos los valores
            textViewname.setText(pelicula.getName());
            Picasso.get().load(pelicula.getImagen()).fit().into(portada);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemclick(pelicula,getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemclick(Movie pelicula,int posicion);
    }

}
