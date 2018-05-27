package com.carta.dx.cartadigital;

import java.io.Serializable;

/**
 * Created by jennyandrea on 26/09/2016.
 */

public class Plato implements Serializable {

    int idPlato;
    String nombre;
    int cantidad;
    int precio;






    public Plato(int idPlato, String nombre, int cantidad, int precio, String producto) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;

    }

    public Plato() {
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
