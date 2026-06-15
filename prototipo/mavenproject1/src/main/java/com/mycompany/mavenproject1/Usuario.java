/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Admin
 */
public class Usuario {
 
    private int    id;
    private String nombre;
    private String correo;
    private String contrasenaHash;
    private String rol;   // "estudiante" | "maestro" | "admin"
    private boolean activo;
 
    public Usuario() {}
 
    public Usuario(int id, String nombre, String correo, String rol, boolean activo) {
        this.id      = id;
        this.nombre  = nombre;
        this.correo  = correo;
        this.rol     = rol;
        this.activo  = activo;
    }
 
    // Getters y setters
    public int     getId()             { return id; }
    public void    setId(int id)       { this.id = id; }
 
    public String  getNombre()                  { return nombre; }
    public void    setNombre(String nombre)     { this.nombre = nombre; }
 
    public String  getCorreo()                  { return correo; }
    public void    setCorreo(String correo)     { this.correo = correo; }
 
    public String  getContrasenaHash()                       { return contrasenaHash; }
    public void    setContrasenaHash(String contrasenaHash)  { this.contrasenaHash = contrasenaHash; }
 
    public String  getRol()                { return rol; }
    public void    setRol(String rol)      { this.rol = rol; }
 
    public boolean isActivo()              { return activo; }
    public void    setActivo(boolean a)    { this.activo = a; }
}
