package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
    /*Para crear la pantalla, crearemos una clase que herede de SurfaceView, que es una clase que nos permitirá
    pintar elementos  en  su  interior,  como  si  se  tratase  de  un  lienzo.  Al  crear  esta  clase,
    podremos  crear  un  objeto  de  ella en  la  interfaz  gráfica.  Esta  clase  deberá  sobrescribir  el  método
    onDraw,  el  cual  se  encargará  de  dibujar  todos  los elementos en la pantalla, y al que le llegará por
    parámetro un objeto de la clase Canvas, que será nuestro “lienzo”.*/

    //The Canvas class holds the "draw" calls. To draw something, you need 4 basic components:
// A Bitmap to hold the pixels, a Canvas to host the draw calls (writing into the bitmap),
// a drawing primitive (e.g. Rect, Path, text, Bitmap),
// and a paint (to describe the colors and styles for the drawing).

    class GameView extends SurfaceView implements Runnable {

        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private volatile boolean playing;
        private Canvas canvas;
        private Bitmap bitmapRunningMan;
        private boolean isMoving;
        private float runSpeedPerSecond = 300;
        private float manXPos = 10, manYPos = 10;
        private int frameWidth = 500, frameHeight = 600;
        private int frameCount = 6;
        private int currentFrame = 0;
        private long fps;
        private long timeThisFrame;
        private long lastFrameChangeTime = 0;
        private int frameLengthInMillisecond = 105;
        //  To draw correctly the good frame for the running man, we need two Rectangle instances.
        //  One used to define the current frame in the sprite sheet and an other to define where to
        //  draw the current frame on the screen :
        private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);

        private RectF whereToDraw = new RectF(manXPos, manYPos, manXPos + frameWidth, frameHeight);

        public GameView(Context context) {
            super(context);
            ourHolder = getHolder();
            bitmapRunningMan = BitmapFactory.decodeResource(getResources(), R.drawable.move);
            bitmapRunningMan = Bitmap.createScaledBitmap(bitmapRunningMan, frameWidth * frameCount, frameHeight, false);
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();
                update();
                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;

                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        //The update method is just used here to move the man positions in X and Y.
// Note that when the man reach the end of the screen horizontally or
// vertically, we set its position to the left or top of the screen
        public void update() {
            if (isMoving) {
                manXPos = manXPos + runSpeedPerSecond / fps;

                if (manXPos > getWidth()) {
                    manYPos += (int) frameHeight;
                    manXPos = 10;
                }

                if (manYPos + frameHeight > getHeight()) {
                    manYPos = 10;
                }
            }
        }

        //we need to define a method to manage the current frame to display
// for the character. We change the current frame only when he
// have ended the frame duration
        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (isMoving) {
                if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                    lastFrameChangeTime = time;
                    currentFrame++;

                    if (currentFrame >= frameCount) {
                        currentFrame = 0;
                    }
                }
            }

            frameToDraw.left = currentFrame * frameWidth;
            frameToDraw.right = frameToDraw.left + frameWidth;
        }
//Funcionalidad de la pantalla de juego
//Bitmap. Los bitmap o mapa de bits son la estructura donde se almacenan los pixeles que conforman
//un gráfico, este esta represento como una rejilla rectangular de puntos de color denominada matriz.
        // Canvas. Los canvas son el lienzo digital donde se dibujan los diferentes elementos como ser
//primitivas geométricas (círculos, rectangulos), texto e imágenes. Cuando «dibujamos» en el canvas,
        //en realidad estamos modificando los pixeles del bitmap.
        //Paint. La clase paint nos permite indicar el estilo y formato de los elementos a dibujar,
        //es decir, tipo de pincel, color, grosor, estilo de linea, etc.

        //Para poder manejar el objeto Surface es necesario crear un objeto contenedor SurfaceHolder,
        // ya que no se puede manejar directamente. Para ello se utiliza el constructor de la clase getHolder()
        // e indicar que SurfaceHolder va a recibir llamadas de SurfaceHolder.callback.

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                whereToDraw.set((int) manXPos, (int) manYPos, (int) manXPos + frameWidth, (int) manYPos + frameHeight);
                manageCurrentFrame();
                canvas.drawBitmap(bitmapRunningMan, frameToDraw, whereToDraw, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("ERR", "Joining Thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        //To start the running man animation, we’re going to wait the user click
// on the surface view. So, we need to override the onTouchEvent method
// and wait for an ACTION_DOWN event.
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    isMoving = !isMoving;
                    break;
            }

            return true;
        }
    }
}