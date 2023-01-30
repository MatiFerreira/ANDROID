package com.example.realm.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.realm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardActivity extends AppCompatActivity {
    private FloatingActionButton botonflotante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        botonflotante = findViewById(R.id.fabaddboard);

    }
}