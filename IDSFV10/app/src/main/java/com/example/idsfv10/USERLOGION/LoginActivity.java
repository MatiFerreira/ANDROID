package com.example.idsfv10.USERLOGION;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idsfv10.DATAGYM.TABLAGYMBBDD;
import com.example.idsfv10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseauthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button ButtonRegister = findViewById(R.id.botonregistro);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbartitulo);
        firebaseauthor = FirebaseAuth.getInstance();
        Button botonlogin = findViewById(R.id.botonlogin);
        EditText mail = findViewById(R.id.camponombre);
        EditText pasword = findViewById(R.id.campocontrasena);
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lyoutRegister = new Intent(LoginActivity.this, registeractivity.class); //Creamos el intent que va abrir
                LoginActivity.this.startActivity(lyoutRegister); // le decimos que empieze esta actividad
                finish(); // cierro el la pestaña de login
            }
        });
        botonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailtext = mail.getText().toString();
                String paswrdtext = pasword.getText().toString();
                if (mailtext.isEmpty() || paswrdtext.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "UNO O VARIOS DE LOS CAMPOS ESTAN VACIO", Toast.LENGTH_SHORT).show();
                } else {
                    signIn(mailtext, paswrdtext);
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseauthor.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        firebaseauthor.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "INICIANDO SESION", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseauthor.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(LoginActivity.this, TABLAGYMBBDD.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            updateUI(null);
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Usuario no existente o error  de parametro pasado.", Toast.LENGTH_SHORT).show();
                    }
                });
        // [END sign_in_with_email]
    }

    private void updateUI(FirebaseUser user) {

    }

    private void reload() {
    }
}