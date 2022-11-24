package com.example.tareadb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class Adaptador extends ArrayAdapter<Recordatorios> {
    Context context;
    List<Recordatorios> arrayListRecordatorios;
    public Adaptador(@NonNull Context context, List<Recordatorios>
            arrayListRecordatorios) {
        super(context, R.layout.my_list_item, arrayListRecordatorios);
        this.context = context;
        this.arrayListRecordatorios = arrayListRecordatorios;
    }
    @NonNull
    @Override
    public View gettView(int position, @Nullable View convertView, @NonNull
            ViewGroup parent){
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, null,
                        true);
        TextView nombre = view.findViewById(R.id.nombreEvento);
        TextView fecha = view.findViewById(R.id.fechaEvento);
        TextView detalles = view.findViewById(R.id.detallesEvento);
        nombre.setText(arrayListRecordatorios.get(position).getId());
        fecha.setText(arrayListRecordatorios.get(position).getFecha());
        detalles.setText(arrayListRecordatorios.get(position).getDetalle());
        return view;
    }
}
