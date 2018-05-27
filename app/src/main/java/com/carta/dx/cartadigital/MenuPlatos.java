package com.carta.dx.cartadigital;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dx on 21/09/2015.
 */
public class MenuPlatos implements Serializable {

  private ArrayList<Plato> Entradas;
    private ArrayList<Plato> PlatoFuerte;
   private ArrayList<Plato> bebidas;
    private ArrayList<Plato> postres;

    private int valor;

    public ArrayList<Plato> getEntradas() {
        return Entradas;
    }

    public void setEntradas(ArrayList<Plato> entradas) {
        Entradas = entradas;
    }

    public ArrayList<Plato> getPlatoFuerte() {
        return PlatoFuerte;
    }

    public void setPlatoFuerte(ArrayList<Plato> platoFuerte) {
        PlatoFuerte = platoFuerte;
    }

    public ArrayList<Plato> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Plato> bebidas) {
        this.bebidas = bebidas;
    }

    public ArrayList<Plato> getPostres() {
        return postres;
    }

    public void setPostres(ArrayList<Plato> postres) {
        this.postres = postres;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public MenuPlatos() {
    }

    public MenuPlatos(ArrayList<Plato> entradas, ArrayList<Plato> platoFuerte, ArrayList<Plato> bebidas, ArrayList<Plato> postres, int valor) {
        Entradas = entradas;
        PlatoFuerte = platoFuerte;
        this.bebidas = bebidas;
        this.postres = postres;
        this.valor = valor;
    }


}


