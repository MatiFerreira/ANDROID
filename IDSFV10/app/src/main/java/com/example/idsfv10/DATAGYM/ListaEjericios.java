package com.example.idsfv10.DATAGYM;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.idsfv10.R;

import java.util.ArrayList;
import java.util.List;

public class ListaEjericios {

    public ListaEjericios() {
    }


    protected List<ListExercise> getallExercise() {
        return new ArrayList<ListExercise>() {{
            add(new ListExercise("Press Militar", R.drawable.press, "Te sientas en una banca con el respaldo a 90º una vez coges una postura recta coges la mancuernas a la altura del hombre y procedes a subir desde el hombro hasta por encima de la cabeza bloqueando" +
                    "los codos podemos hacerlo en unas series de 3x12 con el kilo que mas se ajuste a tu limite."));
            add(new ListExercise("PRESS BANCA", R.drawable.pressbanca, "La ejecucion de este ejercicio es muy sencillo simplemente nos colocamos tumbado el la banca y procedemos a hacer una rotacion scapular con los brazon abietos y agarrando la barra de forma amplia" +
                    "simplemente procedemos a levantar y bajar la barra lentamente de esta forma una 10 veces."));
            add(new ListExercise("Bulgaras", R.drawable.bulgara, "Uno de los mejores ejercicio de piernas consiste en una baca plana apoyamos una pierna y la otra sobre el suelo con una amplitud de 90º" +
                    "bajamos y con las mancuenrnas en las manos sin mover, hacemos como si nos fueramos a sentar hacemos este movimiento en unas series 3x15 con el peso que mas deseemos."));
            add(new ListExercise("Sentadilla con Barra", R.drawable.sentadilla, "Ejercicio basico consiste basicamente coger una barra de 20kg y ponerlo sobre nuestra espalada el movimiento de bajada debe ser" +
                    "como si no sentasemos en una silla columna a 90º y bajamos lo mas bajo posible serie de 3x10."));
            add(new ListExercise("Jalon al pecho", R.drawable.jalonpecho, "En las maquinas de espaldas cogemos una barra de agarre cerrado y desde arriba jalamos o pulleamos la barra hasta nuestro pecho" +
                    "este ejercicio se debe ejecutar con la espalda un poco inclinado pero sin forzar las veces de repeticiones que recomiendo son 3x12 estrictos."));
            add(new ListExercise("Peso muerto convencional", R.drawable.pesomuerto, "Peso muerto con barra olimpica simplemente la barra desde el suelo nos ponemos las piernas a la altura del hombre las puntas mirando" +
                    "recto y como si nos fueramos a sentar en una silla bajamos desde esa postura levantamos la barra hacia arriba sin curvar la columna 3x12."));
            add(new ListExercise("PEC DECK", R.drawable.deck, "Apertura pec deck uno de los mejores ejercios para pecho en la maquina como se puede observar en la imagen simplemente debemos" +
                    "colocar o apoya nuestro antebrazo sobre el soporte y de forma lineal cerramos o hacemos presion sobre la maquina hasta que se toquen posteriormente dejamos de ejercer fuerza lentamente si soltarlo de golpe" +
                    "recomendacion de una serie de 5x10 "));
        }};
    }

    /*protected void AniadirExtra(String Titulo, int imagen, String Descripcion) {
        new ArrayList<ListExercise>() {{
            add(new ListExercise(Titulo, imagen, Descripcion));
        }};
    }*/
}
