package com.webdynamos.fincas.dto;

import com.webdynamos.fincas.enums.ROLE;

public class ArrendadorConPasswordDTO {
    private Long id_arrendador;
    private int calificacion;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String password;
    private ROLE role;
    public ArrendadorConPasswordDTO() {
    }
    public ArrendadorConPasswordDTO(Long id_arrendador, int calificacion, String nombre, String apellido, String username,
            String telefono, String correo, String password) {
        this.id_arrendador = id_arrendador;
        this.calificacion = calificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
    }
    public Long getId_arrendador() {
        return id_arrendador;
    }
    public void setId_arrendador(Long id_arrendador) {
        this.id_arrendador = id_arrendador;
    }
    public int getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ROLE getRole(){
        return this.role;
    }
    public void setRole(ROLE role){
        this.role = role;
    }
}
