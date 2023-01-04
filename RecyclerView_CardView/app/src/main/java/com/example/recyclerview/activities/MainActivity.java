package com.example.recyclerview.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerview.R;
import com.example.recyclerview.adaptador.myadapter;
import com.example.recyclerview.modelos.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Movie> peliculas;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private int contador =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peliculas = this.getallmovies();/*lo intaciamos*/
        recyclerView = findViewById(R.id.recycler);
        layoutManager =  new LinearLayoutManager(this);

        adaptador = new myadapter(peliculas, R.layout.recyclerview_item, new myadapter.OnItemClickListener() {
            @Override
            public void onItemclick(Movie pelicula, int posicion) {
                DeleteMovie(posicion);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true); /*optimiza el tamaño de nuestro recycler para ahorrar memoria*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());/* le pasamos una animacion hay mas pero este es por defecto*/
    }

    /*OPCIONES DE BOTON*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                this.AddMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private List<Movie>getallmovies(){
        return new ArrayList<Movie>(){{
           add(new Movie("American Psycho",R.drawable.american));
           add(new Movie("American Assasin",R.drawable.assasin));
           add(new Movie("Blade Runner",R.drawable.blade));
           add(new Movie("Fight Club",R.drawable.fight));
        }};
    }

    private void AddMovie(int posicion) {
        peliculas.add(posicion,new Movie("NEW MOVIE"+(++contador),R.drawable.peli)); //añade nuevos valores
        adaptador.notifyItemInserted(posicion); //notifica y actualiza donde se ha añadido el valor :
        layoutManager.scrollToPosition(posicion); /*escrolea solo a donde se va añadiendo en la lista*/
    }

     private void DeleteMovie(int posicion){
        peliculas.remove(posicion);
        adaptador.notifyItemRemoved(posicion); //notifica y actualiza que se elimina el valor item
        layoutManager.scrollToPosition(posicion);/*escrolea solo a donde se va eliminando valor  en la lista*/
    }

}