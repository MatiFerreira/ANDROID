package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> names;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private int contador =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = this.getallname();/*lo intaciamos*/
        recyclerView = findViewById(R.id.recycler);
        layoutManager =  new LinearLayoutManager(this);

        adaptador = new myadapter(names, R.layout.recyclerview_item, new myadapter.OnItemClickListener() {
            @Override
            public void onItemclick(String nombre, int posicion) {
                deleteName(posicion);
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
                this.addname(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private List<String>getallname(){
        return new ArrayList<String>(){{
           add("Marco");
           add("Pedor");
           add("JUAN");
           add("NACHO");
           add("Luis");
        }};
    }

    private void addname(int posicion) {
        names.add(posicion,"NEW NAME "+((contador++))); //añade nuevos valores
        adaptador.notifyItemInserted(posicion); //notifica y actualiza donde se ha añadido el valor :
        layoutManager.scrollToPosition(posicion); /*escrolea solo a donde se va añadiendo en la lista*/
    }

    private void deleteName(int posicion){
        names.remove(posicion);
        adaptador.notifyItemRemoved(posicion); //notifica y actualiza que se elimina el valor item
        layoutManager.scrollToPosition(posicion);/*escrolea solo a donde se va eliminando valor  en la lista*/
    }

}