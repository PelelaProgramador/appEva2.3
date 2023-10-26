package com.example.capsulas.modelos;

public class producto {
    private String nombre;
    private double precio;
    private int imagenProductoId;

    public producto(String nombre, double precio, int imagenProductoId) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenProductoId = imagenProductoId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getImagenProductoId() {
        return imagenProductoId;
    }
}

