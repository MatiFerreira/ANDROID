package com.example.idsfv10.DATAGYM;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idsfv10.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class aniadirejercicios extends AppCompatActivity {
    String rutaImagen;
    ListaEjericios newejercicios;
    String Titulo;
    String Descripcion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadirejercicios2);
        Button botonaceptar;
        Button AbrirGaleria;
        EditText titulo;
        EditText Descripcion;
        botonaceptar = findViewById(R.id.confirmarnuevoejercicio);
        AbrirGaleria = findViewById(R.id.galeriafotos);
        titulo = findViewById(R.id.texttitulo);
        Descripcion = findViewById(R.id.decripciontexto);
        Titulo = titulo.getText().toString();
        Descripcion1 = Descripcion.getText().toString();
        if (Titulo.isEmpty() || Descripcion1.isEmpty()) {
            Toast.makeText(this, "No puede estar ninguno de los dos vacios", Toast.LENGTH_SHORT).show();
        }
        AbrirGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    abrirCamara();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    private File crearImagen() throws IOException {

        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;

    }

    private void abrirCamara() throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File imagenArchivo = null;

        try {
            imagenArchivo = crearImagen();

        } catch (IOException ex) {
            Log.e("Error", ex.toString());
        }


        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(this, "com.example.idsfv10.DATAGYM.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
        }

        startActivityForResult(intent, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
        }
    }

}