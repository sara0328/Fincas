package com.webdynamos.fincas.dto;

public class ArrendatarioConPasswordDTO {
    private Long id_arrendatario;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String password;
    public ArrendatarioConPasswordDTO() {
    }
    public ArrendatarioConPasswordDTO(Long id_arrendatario, String username, String nombre, String apellido,
            String telefono, String correo, String password) {
        this.id_arrendatario = id_arrendatario;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
    }
    public Long getId_arrendatario() {
        return id_arrendatario;
    }
    public void setId_arrendatario(Long id_arrendatario) {
        this.id_arrendatario = id_arrendatario;
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
    
}
