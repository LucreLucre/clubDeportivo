package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate.now


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
    fun obtenerClientesVencidos(): List<Triple<Int, String, String>>{
        val db = readableDatabase
        // Busqué cómo listar 3 elementos seguidos
        val lista = mutableListOf<Triple<Int, String, String>>()
        val cursor = db.rawQuery("SELECT nro_socio, nombre, apellido FROM clientes WHERE date(ultimo_pago) <= date('now', '-1 month')", null)
        if(cursor.moveToFirst()){
            do{
                val nro_socio = (cursor.getInt(0))
                val nombre = (cursor.getString(1))
                val apellido = (cursor.getString(2))
                lista.add(Triple(nro_socio, nombre, apellido))
            } while(cursor.moveToNext())
        }
        return lista
    }

    fun eliminarCliente(nombre: String){
        val db = writableDatabase
        db.delete("clientes", "nombre = ?", arrayOf(nombre))
    }

    // PAGOS
    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarUltimoPago(nroSocio: Int) {
        val db = writableDatabase
        val valores = ContentValues()
        valores.put("ultimo_pago", now().toString())
        db.update(
            "clientes",
            valores,
            "nro_socio = ?",
            arrayOf(nroSocio.toString())
        )

        db.close()
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

    fun actualizarCupo(idActividad: Int) {
        val db = writableDatabase

        val cursor = db.rawQuery("SELECT cupo FROM actividades WHERE id = ?", arrayOf(idActividad.toString()))
        if (cursor.moveToFirst()) {
            val cupoActual = cursor.getInt(0)
            cursor.close()

            if (cupoActual > 0) {
                val nuevoCupo = cupoActual - 1
                val valores = ContentValues()
                valores.put("cupo", nuevoCupo)

                db.update(
                    "actividades",
                    valores,
                    "id = ?",
                    arrayOf(idActividad.toString())
                )
            }
        } else {
            cursor.close()
        }

        db.close()
    }


}