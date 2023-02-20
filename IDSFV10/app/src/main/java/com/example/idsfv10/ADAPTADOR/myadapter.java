package com.example.idsfv10.ADAPTADOR;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idsfv10.DATAGYM.ListExercise;
import com.example.idsfv10.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Mathias Ferreira
 **/
public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    private List<ListExercise> ListaEjercicios;
    private int layout;
    private OnItemClickListener listener;

    public myadapter(List<ListExercise> ListaEjercicios, int layout, OnItemClickListener listener) {
        this.layout = layout;
        this.listener = listener;
        this.ListaEjercicios = ListaEjercicios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false); /*INFLAMOS LA VISTA*/
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(ListaEjercicios.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return ListaEjercicios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView NombreEjercicio;
        public ImageView ImagenEjercicio;
        public TextView DescripionEjercicio;

        public ViewHolder(View view) {
            super(view);
            NombreEjercicio = view.findViewById(R.id.TituloCardview);
            ImagenEjercicio = view.findViewById(R.id.ImageCardView);
            DescripionEjercicio = view.findViewById(R.id.textodescripcionCardView);
        }

        public void bind(final ListExercise ListaEjercicio, final OnItemClickListener listener) {
            //Procesamos los valores
            NombreEjercicio.setText(ListaEjercicio.getNameExercise());
            DescripionEjercicio.setText(ListaEjercicio.getDataExercise());
            Picasso.get().load(ListaEjercicio.getImageExersice()).fit().noFade().into(ImagenEjercicio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemclick(ListaEjercicio, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemclick(ListExercise Listaejercicio, int posicionLista);
    }

}
