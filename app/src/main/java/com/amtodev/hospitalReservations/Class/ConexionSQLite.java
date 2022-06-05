package com.amtodev.hospitalReservations.Class;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ConexionSQLite extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "agilesReservas";

    final String TABLE_ESPECIALIDAD = "CREATE TABLE especialidades(" +
            "especialidad_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
            "especialidad_nombre TEXT)";

    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(TABLE_ESPECIALIDAD);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS especialidades");
        onCreate(db);
    }

    public Cursor getShowDataSpecialty(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT especialidad_id, especialidad_nombre FROM especialidades"+ " ORDER BY especialidades.especialidad_nombre ASC ", null);
    }

}