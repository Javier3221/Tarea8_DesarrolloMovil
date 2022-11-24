package com.example.tareadb;

public class Recordatorios {
    String id, nombre, detalle, fecha;
    public Recordatorios(){
    }
    public Recordatorios(String id, String nombre, String detalle, String fecha)
    {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.fecha = fecha;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } public String
    getDetalle() { return
            detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    } public String
    getFecha() { return
            fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
