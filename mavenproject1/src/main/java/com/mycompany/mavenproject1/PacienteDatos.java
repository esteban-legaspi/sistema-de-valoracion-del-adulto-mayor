/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
 
public class PacienteDatos {
 
    private int    id;
    private int    valoracionId;
    private String nombre;
    private Integer edad;
    private String genero;
    private String lugarNacimiento;
    private String domicilio;
    private String fechaIngreso;      // String "YYYY-MM-DD" viene del form
    private String religion;
    private String escolaridad;
    private String estadoCivil;
    private String ocupacion;
    private String dependenciaInstitucion;
    private String serviciosSalud;    // JSON array como String
    private String cuandoAcudeMedico;
    private Boolean capazDecisiones;
    private String responsable;
    private Boolean llevaTratamiento;
 
    public PacienteDatos() {}
 
    // ── Getters y setters ──────────────────────────────
    public int getId()                        { return id; }
    public void setId(int id)                 { this.id = id; }
 
    public int getValoracionId()              { return valoracionId; }
    public void setValoracionId(int v)        { this.valoracionId = v; }
 
    public String getNombre()                 { return nombre; }
    public void setNombre(String n)           { this.nombre = n; }
 
    public Integer getEdad()                  { return edad; }
    public void setEdad(Integer e)            { this.edad = e; }
 
    public String getGenero()                 { return genero; }
    public void setGenero(String g)           { this.genero = g; }
 
    public String getLugarNacimiento()        { return lugarNacimiento; }
    public void setLugarNacimiento(String l)  { this.lugarNacimiento = l; }
 
    public String getDomicilio()              { return domicilio; }
    public void setDomicilio(String d)        { this.domicilio = d; }
 
    public String getFechaIngreso()           { return fechaIngreso; }
    public void setFechaIngreso(String f)     { this.fechaIngreso = f; }
 
    public String getReligion()               { return religion; }
    public void setReligion(String r)         { this.religion = r; }
 
    public String getEscolaridad()            { return escolaridad; }
    public void setEscolaridad(String e)      { this.escolaridad = e; }
 
    public String getEstadoCivil()            { return estadoCivil; }
    public void setEstadoCivil(String e)      { this.estadoCivil = e; }
 
    public String getOcupacion()              { return ocupacion; }
    public void setOcupacion(String o)        { this.ocupacion = o; }
 
    public String getDependenciaInstitucion() { return dependenciaInstitucion; }
    public void setDependenciaInstitucion(String d) { this.dependenciaInstitucion = d; }
 
    public String getServiciosSalud()         { return serviciosSalud; }
    public void setServiciosSalud(String s)   { this.serviciosSalud = s; }
 
    public String getCuandoAcudeMedico()      { return cuandoAcudeMedico; }
    public void setCuandoAcudeMedico(String c){ this.cuandoAcudeMedico = c; }
 
    public Boolean getCapazDecisiones()       { return capazDecisiones; }
    public void setCapazDecisiones(Boolean c) { this.capazDecisiones = c; }
 
    public String getResponsable()            { return responsable; }
    public void setResponsable(String r)      { this.responsable = r; }
 
    public Boolean getLlevaTratamiento()      { return llevaTratamiento; }
    public void setLlevaTratamiento(Boolean l){ this.llevaTratamiento = l; }
}
