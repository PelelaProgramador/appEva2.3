package com.example.capsulas.modelos;

public class usuario {
    private String _id;  // Este campo corresponderá al ID del documento en Firestore
    private String nombre;
    private String correo;
    private String genero;
    private String ciudad;
    private String contrasena;

    public usuario(String nombre, String correo, String genero, String ciudad, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.genero = genero;
        this.ciudad = ciudad;
        this.contrasena = contrasena;
        this._id = _id;


    }

    public usuario() {

    }

    public String get_Id() {
        return _id;
    }

    public void set_Id(String id) {
        this._id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
