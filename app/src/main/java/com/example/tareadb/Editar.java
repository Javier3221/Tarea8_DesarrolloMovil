package com.example.tareadb;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.tareadb.R;

public class Editar extends AppCompatActivity {
    EditText nombreEvento, fechaEvento, detalleEvento;
    Button btnEditar;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        //EditText
        nombreEvento = findViewById(R.id.nombre);
        fechaEvento = findViewById(R.id.fecha);
        detalleEvento = findViewById(R.id.detalle);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        nombreEvento.setText(Home.recordatorios.get(position).getNombre());
        fechaEvento.setText(Home.recordatorios.get(position).getFecha());
        detalleEvento.setText(Home.recordatorios.get(position).getDetalle());
    }
    public void
    actualizar(){ } }
