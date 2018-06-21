package com.example.nacho.loginrecordar;

public class Tarea {
    private String tarea;
    private String descripcion;

    public Tarea(String tarea, String descripcion) {
        this.tarea = tarea;
        this.descripcion = descripcion;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return tarea + ": " + descripcion;
    }
}
