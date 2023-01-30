package com.example.httpsrequestjson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.httpsrequestjson.modelo.Alumno;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Alumno> alumnolist;
    private RequestQueue queue;
    private EditText nombre, apellido, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.textviewnombre);
        apellido = findViewById(R.id.textoapellido);
        id = findViewById(R.id.textodni);
    }

    private void obtenerDatos(ArrayList<Alumno> alumnos) {
        //inicializamos la cola con la biblioteca Volley
        queue = Volley.newRequestQueue(this);
        String url = "http://10.200.240.115/universidad/OBTENER_ALUMNOS.php";

        //metodos de peticion http request busqueda en google metodos http
        //le pasamos la respuesta que te obliga a implementar el metodo onresponse
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        // Procesamos el JSONArray
                        try {
                            JSONArray datosalumnos = response.getJSONObject(0).getJSONArray("mensaje");
                            //JSONArray datospersonas = response.getJSONArray(0);

                            for (int i = 0; i < datosalumnos.length(); i++) {
                                JSONObject per = datosalumnos.getJSONObject(i);
                                String id = per.getString("id_alumnos");
                                String nombre = per.getString("NOMBRE");
                                String apellidos = per.getString("APELLIDO");
                                Alumno persona = new Alumno(nombre, apellidos, id);
                                alumnos.add(persona);

                            }
                            nombre.setText(alumnos.get(0).getNOMBRE());
                            apellido.setText(alumnos.get(0).getAPELLIDO());
                            id.setText(alumnos.get(0).getId_alumnos());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //añadir a la cola la url y la peticion que vamos a mandar
        queue.add(jsonObjectRequest);
    }
}