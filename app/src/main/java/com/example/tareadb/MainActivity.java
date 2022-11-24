package com.example.tareadb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText nombreEvento, fechaEvento, detalleEvento;
    Button btnInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EditText
        nombreEvento = findViewById(R.id.nombre);
        fechaEvento = findViewById(R.id.fecha);
        detalleEvento = findViewById(R.id.detalle);
        //Buttons
        btnInsert = findViewById(R.id.btnInsertar);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar("http://10.0.0.4/Android%20Studio%20DB/insert.php");
            }
        });
    }
    private void insertar(String url)
    {
        String nombre = nombreEvento.getText().toString().trim();
        String fecha = fechaEvento.getText().toString().trim();
        String detalle = detalleEvento.getText().toString().trim();
        if(nombre.isEmpty()){
            nombreEvento.setError("Complete el campo.");
        }else if(fecha.isEmpty()){
            fechaEvento.setError("Complete el campo.");
        }else if(detalle.isEmpty()){
            detalleEvento.setError("Complete el campo.");
        }else{
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("Evento Registrado")) {
                                Toast.makeText(MainActivity.this, "Datos Ingresados",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, response,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nombre", nombre); params.put("detalle", detalle);
                    params.put("fecha", fecha);
                    return params;
                }
            };
            RequestQueue requestQueue =
                    Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
    }
}