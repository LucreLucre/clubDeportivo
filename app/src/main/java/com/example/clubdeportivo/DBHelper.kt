package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context, "Club.db", null, 1){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE clientes("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "nombre TEXT NOT NULL, " +
                    "apellido TEXT NOT NULL, " +
                    "dni INTEGER NOT NULL, "+
                    "telefono TEXT NOT NULL, " +
                    "socio BOOLEAN, " +
                    "nro_socio INTEGER, " +
                    "apto_fisico BOOLEAN, " +
                    "ultimo_pago TEXT)"
        )

        db.execSQL(
            "CREATE TABLE actividades(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "actividad TEXT NOT NULL, " +
                    "cupo INTEGER)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS clientes")
        db.execSQL("DROP TABLE IF EXISTS actividades")
        onCreate(db)
    }
/*
    fun insertarProducto(nombre:String, fechaVencimiento: String){
        val db =  writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("fecha_vencimiento", fechaVencimiento)
        db.insert("productos", null, values)
    }
*/

    // CLIENTES
    fun obtenerClientesVencidos(): List<Pair<Int, String>>{
        val db = readableDatabase
        val lista = mutableListOf<Pair<Int, String>>()
        val cursor = db.rawQuery("SELECT nro_socio, ultimo_pago FROM clientes WHERE date(ultimo_pago) <= date('now', '-1 month')", null)
        if(cursor.moveToFirst()){
            do{
                val nro_socio = (cursor.getInt(0))
                val ultimo_pago = (cursor.getString(1) ?: "")
                lista.add(nro_socio to ultimo_pago)
            } while(cursor.moveToNext())
        }
        return lista
    }

    fun eliminarCliente(nombre: String){
        val db = writableDatabase
        db.delete("clientes", "nombre = ?", arrayOf(nombre))
    }


    // ACTIVIDADES
    fun obtenerActividades(): List<Pair<String, Int>>{
        val db = readableDatabase
        val lista = mutableListOf<Pair<String, Int>>()
        val cursor = db.rawQuery("SELECT nombre, cupo FROM actividades", null)
        if(cursor.moveToFirst()){
            do{
                val nombre = (cursor.getString(0))
                val cupo = (cursor.getInt(1) ?: 0)
                lista.add(nombre to cupo)
            } while(cursor.moveToNext())
        }
        return lista
    }

    fun eliminarActividad(nombre: String){
        val db = writableDatabase
        db.delete("actividades", "nombre = ?", arrayOf(nombre))
    }

}