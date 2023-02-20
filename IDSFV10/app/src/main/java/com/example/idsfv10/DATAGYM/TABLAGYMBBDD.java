package com.example.idsfv10.DATAGYM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.idsfv10.ADAPTADOR.myadapter;
import com.example.idsfv10.USERLOGION.LoginActivity;
import com.example.idsfv10.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TABLAGYMBBDD extends AppCompatActivity {
    ListaEjericios arraysejercicios = new ListaEjericios();
    private RecyclerView recyclerView;
    private List<ListExercise> ListaEjercicios;
    private RecyclerView.Adapter AdaptadorRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseAuth firebaseauthor;

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablagymbbdd);
        firebaseauthor = FirebaseAuth.getInstance();
        ListaEjercicios = arraysejercicios.getallExercise();
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);

        AdaptadorRecyclerView = new myadapter(ListaEjercicios, R.layout.tablagym, new myadapter.OnItemClickListener() {
            @Override
            public void onItemclick(ListExercise Listaejercicio, int posicionLista) {

            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(AdaptadorRecyclerView);
        recyclerView.setHasFixedSize(true); /*optimiza el tamaño de nuestro recycler para ahorrar memoria*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());/* le pasamos una animacion hay mas pero este es por defecto*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                Toast.makeText(this, "CERRANDO SESION", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                System.out.println("ACABA DE ENTRAR EN LOGOUT");


                break;
            case R.id.rutina:
                Intent intent1 = new Intent(this, aniadirejercicios.class);
                Toast.makeText(this, "INICIANDO", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void signOut() {
        // Firebase sign out
        firebaseauthor.signOut();
    }

}