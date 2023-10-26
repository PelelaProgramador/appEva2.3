package com.example.capsulas.modelos;

import java.util.Date;

public class pedido {
    private String id; // Un identificador único para el pedido
    private String nombreUsuario;
    private int numeroPedidos;
    private Date fechaPedido; // Si deseas rastrear la fecha del pedido, de lo contrario, puedes omitir esto

    public pedido() {
        // Constructor vacío requerido para Firebase
    }

    public pedido(String nombreUsuario, int numeroPedidos) {
        this.nombreUsuario = nombreUsuario;
        this.numeroPedidos = numeroPedidos;
        this.fechaPedido = new Date(); // Fecha actual
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getNumeroPedidos() {
        return numeroPedidos;
    }

    public void setNumeroPedidos(int numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
