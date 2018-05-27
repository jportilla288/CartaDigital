package com.carta.dx.cartadigital;

import android.provider.BaseColumns;

public class HacerPedido {


    public static abstract class NuevoPedido implements BaseColumns {
        public static final String TABLE_NAME ="pedido";

        public static final String ID = "idPed";//pendiente
        public static final String  PRECIO ="precioTotal" ;
        public static final String DURACION = "duracion";
        public static final String MESA = "numMesa";
        public static final String CLIENTE = "idCliente";
        public static final String LOGIN = "idLogin";
    }
}
