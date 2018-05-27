package com.carta.dx.cartadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.security.auth.login.LoginException;

public class PedidoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pedido.db";


    public PedidoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }
    //Este método es llamado automáticamente cuando creamos una instancia de la clase SQLiteOpenHelper. En su interior establecemos la creación de las tablas y registros.
    //Recibe como parámetro una referencia de la clase SQLiteDataBase, la cual actua como manejadora de la base de datos.


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table...
        db.execSQL("CREATE TABLE " + HacerPedido.NuevoPedido.TABLE_NAME + " ("
                + HacerPedido.NuevoPedido.ID + " INT PRIMARY KEY AUTOINCREMENT,"
                + HacerPedido.NuevoPedido.PRECIO + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.DURACION + " TEXT NOT NULL,"
                + HacerPedido.NuevoPedido.MESA + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.CLIENTE + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.LOGIN + " INT,"
                + "UNIQUE (" + HacerPedido.NuevoPedido.ID + "))");

        // Contenedor de valores
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put(HacerPedido.NuevoPedido.ID, "1");
        values.put(HacerPedido.NuevoPedido.PRECIO, "50000");
        values.put(HacerPedido.NuevoPedido.DURACION, "20");
        values.put(HacerPedido.NuevoPedido.MESA, "4");
        values.put(HacerPedido.NuevoPedido.CLIENTE, "63598712");
        values.put(HacerPedido.NuevoPedido.LOGIN, "jcarlos20");

        // Insertar...

        db.insert(HacerPedido.NuevoPedido.TABLE_NAME, null, values);

    }

    public void InsertarPedido(Pedido pedido) {
        SQLiteDatabase sqLiteDatabase =  getWritableDatabase();

        sqLiteDatabase.insert(
                HacerPedido.NuevoPedido.TABLE_NAME,
                null,
                toContentValues(pedido));

        sqLiteDatabase.execSQL("CREATE TABLE " + HacerPedido.NuevoPedido.TABLE_NAME + " ("
                + HacerPedido.NuevoPedido.ID + " INT PRIMARY KEY AUTOINCREMENT,"
                + HacerPedido.NuevoPedido.PRECIO + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.DURACION + " TEXT NOT NULL,"
                + HacerPedido.NuevoPedido.MESA + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.CLIENTE + " INT NOT NULL,"
                + HacerPedido.NuevoPedido.LOGIN + " INT,"
                + "UNIQUE (" + HacerPedido.NuevoPedido.ID + "))");

    }

    public ContentValues toContentValues(Pedido p) {
        ContentValues values = new ContentValues();

        values.put(HacerPedido.NuevoPedido.ID, p.getIdCliente());
        /*values.put(HacerPedido.NuevoPedido.PRECIO, precio);
        values.put(HacerPedido.NuevoPedido.DURACION , duracion);
        values.put(HacerPedido.NuevoPedido.MESA, numMesa);
        values.put(HacerPedido.NuevoPedido.CLIENTE, idCliente);
        values.put(HacerPedido.NuevoPedido.LOGIN, login);*/
        return values;
    }


    //Este es ejecutado si se identificó que el usuario tiene una versión antigua de la base de datos.
    //En su interior establecerás instrucciones para modificar el esquema de la base de datos, como por ejemplo eliminar todo el esquema y recrearlo, agregar una nueva tabla, añadir una nueva columna, etc.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public void InsertarPedido() {
    }
}
