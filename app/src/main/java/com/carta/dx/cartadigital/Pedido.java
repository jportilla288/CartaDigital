package com.carta.dx.cartadigital;

import android.content.ContentValues;

/**
 * Created by Dx on 02/04/2016.
 */
public class Pedido {
    private int idPed;
    private  int precioTotal;
    private int duracion;
    private int numMesa;
    private int idCliente;
    private int idLogin;

    public Pedido(int idPed, int precioTotal, int duracion, int numMesa, int idCliente, int idLogin) {
        this.idPed = idPed;
        this.precioTotal = precioTotal;
        this.duracion = duracion;
        this.numMesa = numMesa;
        this.idCliente = idCliente;
        this.idLogin = idLogin;
    }

    public Pedido(){

    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    @Override
    public String toString() {
        return "NuevoPedido{" +
                "idPed=" + idPed +
                ", precioTotal=" + precioTotal +
                ", duracion=" + duracion +
                ", numMesa=" + numMesa +
                ", idCliente=" + idCliente +
                ", idLogin=" + idLogin +
                '}';
    }



}


