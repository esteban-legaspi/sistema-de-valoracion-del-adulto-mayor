package com.mycompany.mavenproject1;

import java.sql.Timestamp;

public class Valoracion {

    private int id;
    private int usuarioId;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private boolean completada;

    public Valoracion() {
    }

    public Valoracion(
            int id,
            int usuarioId,
            Timestamp fechaRegistro,
            Timestamp fechaActualizacion,
            boolean completada) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.completada = completada;
    }

    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
