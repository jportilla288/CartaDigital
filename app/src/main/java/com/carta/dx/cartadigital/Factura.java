package com.carta.dx.cartadigital;

/**
 * Created by jennyandrea on 3/11/2016.
 */

public class Factura {
    private int idFactura;
    private int numero;
    private int idEmpleado;
    private int idPed;

    public Factura() {
    }

    public Factura(int idFactura, int numero, int idEmpleado, int idPed) {
        this.idFactura = idFactura;
        this.numero = numero;
        this.idEmpleado = idEmpleado;
        this.idPed = idPed;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }
}
