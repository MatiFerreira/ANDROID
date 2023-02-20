package com.example.idsfv10.USERLOGION;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idsfv10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registeractivity extends AppCompatActivity {
    EditText newnombre, contrasenia1, contrasenia2;
    Button botonRegistro;
    boolean iscomprobacion = true;
    FirebaseAuth firebaseauthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        firebaseauthor = FirebaseAuth.getInstance();
        newnombre = findViewById(R.id.NEWUSUARIO);
        contrasenia1 = findViewById(R.id.newcontrasenia);
        contrasenia2 = findViewById(R.id.newcontraseniaconfirmacion);
        botonRegistro = findViewById(R.id.bottonconfirmacionregistro);

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = newnombre.getText().toString();
                String contra1 = contrasenia1.getText().toString();
                String contra2 = contrasenia2.getText().toString();
                if (!contra1.equals(contra2) || contra1.isEmpty()) {
                    mensajeerrorContrasenia();
                    iscomprobacion = true;
                } else {
                    iscomprobacion = false;
                }
                if (nombre.isEmpty()) {
                    mensajeerrorUsuario();
                    iscomprobacion = true;
                } else {
                    iscomprobacion = false;
                }
                if (!iscomprobacion) {
                    firebaseauthor.createUserWithEmailAndPassword(nombre, contra1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //para comprobar si a salido bien
                            if (task.isSuccessful()) {
                                MensajeExito();
                            } else {
                                MensajeErrorRegistro();
                            }
                        }
                    });
                }
                System.out.println(contra1 + contra2 + nombre);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.atras, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuatras:
                Intent intent = new Intent(registeractivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void mensajeerrorContrasenia() {
        Toast.makeText(this, "CONTRASEÑA VACIA O NO COINCIDEN", Toast.LENGTH_LONG).show();
    }

    private void mensajeerrorUsuario() {
        Toast.makeText(this, "ERROR DE AL PONER SU CORREO ELECTRONICO", Toast.LENGTH_LONG).show();
    }

    private void MensajeExito() {
        Toast.makeText(this, "Registrado Correctamente", Toast.LENGTH_LONG).show();
        finish();
        Intent lyoutLogin = new Intent(registeractivity.this, LoginActivity.class); //Creamos el intent que va abrir
        registeractivity.this.startActivity(lyoutLogin); // le decimos que empieze esta actividad
    }

    private void MensajeErrorRegistro() {
        Toast.makeText(this, "Registrado Incorrectamente Ejecutado", Toast.LENGTH_LONG).show();
    }
}