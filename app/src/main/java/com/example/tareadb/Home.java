package com.example.tareadb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ListView
            listView; Adaptador
            adaptador;
    public static ArrayList<Recordatorios> recordatorios = new ArrayList<>();
    String url ="http://10.0.0.4/Android%20Studio%20DB/listar.php";
    Recordatorios eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listView);
        adaptador = new Adaptador(this, recordatorios);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(view.getContext());
                CharSequence[] dialogoItem = {"Editar Evento", "Eliminar Evento"};
                builder.setTitle(recordatorios.get(position).getNombre());
                builder.setItems(dialogoItem, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){ case 0:
                                    startActivity(new
                                            Intent(getApplicationContext(), Editar.class)
                                            .putExtra("position", position));
                                    break; case 1:
                                    break;
                                }
                            }
                        });
                builder.create().show();
            }
        });
        mostrarDatos();
    }
    public void mostrarDatos(){
        StringRequest request = new StringRequest(Request.Method.POST, url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        recordatorios.clear(); try{

                            JSONArray jsonArray = new JSONArray(response);
                            if(!response.isEmpty()){
                                for(int i= 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String nombre = object.getString("nombre");
                                    String detalle = object.getString("detalle");
                                    String fecha = object.getString("fecha");
                                    eventos = new Recordatorios(id, nombre, detalle,
                                            fecha);
                                    recordatorios.add(eventos);
                                    adaptador.notifyDataSetChanged();
                                }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void btnAgg(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
