package com.vg.proyecto.model;

public class AlumnoDTO {

    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String qrCode;
    private String nombreCompletoPadre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompletoPadre() {
        return nombreCompletoPadre;
    }

    public void setNombreCompletoPadre(String nombreCompletoPadre) {
        this.nombreCompletoPadre = nombreCompletoPadre;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
