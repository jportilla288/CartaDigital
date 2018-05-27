package com.carta.dx.cartadigital;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class SQLiteOpenHelper {
    public SQLiteOpenHelper(Context context, String databaseName, Object o, int databaseVersion) {
    }


    public abstract void onCreate(SQLiteDatabase sqLiteDatabase);

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
