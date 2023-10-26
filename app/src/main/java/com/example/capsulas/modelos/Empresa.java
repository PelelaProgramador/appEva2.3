package com.example.capsulas.modelos;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private int imagenId;
    private List<producto> productos;

    public Empresa(String nombre, int imagenId) {
        this.nombre = nombre;
        this.imagenId = imagenId;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precio, int imagenProductoId) {
        producto producto = new producto(nombre, precio, imagenProductoId);
        productos.add(producto);
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenId() {
        return imagenId;
    }

    public List<producto> getProductos() {
        return productos;
    }
}

