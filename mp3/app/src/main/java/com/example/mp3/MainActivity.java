package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // creamos un objeto de MediaPlayer llamado music incorporada en el paquete android
    MediaPlayer music;
    private boolean play;
    ImageView imagv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagv = (ImageView) findViewById(R.id.fondo);
        imagv.setBackground(getDrawable(R.drawable.mp4));
        // Agregamos nuestro archivo mp3 de música al objeto music usando la función de create
        try{
            music = MediaPlayer.create(this, R.raw.music2);
        }catch (Exception e){
            System.out.println(e.getCause());
        }


    }

    // Playing the music
    public void musicplay(View v)
    {
        if (!play){
            music.start();
            play= true;
            imagv.setBackground(getDrawable(R.drawable.ariculareschulos));
        }
    }


    // Pausing the music
    public void musicpause(View v)
    {
        if(play){
            music.pause();
            play = false;
            imagv.setBackground(getDrawable(R.drawable.pause));
        }

    }

    // Stoping the music
    public void musicstop(View v)
    {
        play =false;
        music.stop();
        music= MediaPlayer.create(this, R.raw.music2);
        imagv.setBackground(getDrawable(R.drawable.mp4));
    }


}