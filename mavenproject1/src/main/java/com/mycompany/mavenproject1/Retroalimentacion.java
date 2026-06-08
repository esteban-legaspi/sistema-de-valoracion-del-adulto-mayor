package com.mycompany.mavenproject1;

import java.sql.Timestamp;

public class Retroalimentacion {

    private int       id;
    private int       valoracionId;
    private int       maestroId;
    private String    comentario;
    private Timestamp fecha;
    private boolean   leida;

    public Retroalimentacion() {}

    public int       getId()                      { return id; }
    public void      setId(int id)                { this.id = id; }

    public int       getValoracionId()            { return valoracionId; }
    public void      setValoracionId(int v)       { this.valoracionId = v; }

    public int       getMaestroId()               { return maestroId; }
    public void      setMaestroId(int m)          { this.maestroId = m; }

    public String    getComentario()              { return comentario; }
    public void      setComentario(String c)      { this.comentario = c; }

    public Timestamp getFecha()                   { return fecha; }
    public void      setFecha(Timestamp f)        { this.fecha = f; }

    public boolean   isLeida()                    { return leida; }
    public void      setLeida(boolean l)          { this.leida = l; }
}