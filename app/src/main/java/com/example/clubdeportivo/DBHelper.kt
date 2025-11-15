package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate.now


class DBHelper(context: Context) : SQLiteOpenHelper(context, "Club.db", null, 6){

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

        db.execSQL(
            "CREATE TABLE empleados(" +
                    "e_legajo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "e_nombre TEXT NOT NULL, " +
                    "e_apellido TEXT NOT NULL, " +
                    "e_usuario TEXT NOT NULL, " +
                    "e_contraseña TEXT NOT NULL, " +
                    "e_rol TEXT NOT NULL)"
        )

        db.execSQL(
            "INSERT INTO empleados (e_nombre, e_apellido, e_usuario, e_contraseña, e_rol) VALUES " +
                    "('Camila', 'Cuns', 'ccuns', 'cuns1234', 'empleado'), " +
                    "('Lucas', 'Blaci', 'lblaci', 'blac1234', 'empleado'), " +
                    "('Lucrecia', 'Vigo', 'lvigo', 'vigo1234', 'empleado'), " +
                    "('Yahir Iván', 'Perez Tolchinsky', 'yperez', 'pere1234', 'empleado'), " +
                    "('Kevin Axel', 'Del Bello', 'kdelbello', 'delb1234', 'gerente')" +
                    ";"
        )

        db.execSQL(
            "INSERT INTO actividades (actividad, cupo) VALUES" +
                    "('pilates',6), " +
                    "('aqua gym', 5)," +
                    "('funcional', 6)" +
                    ";"
        )

        // 👇 SOCIO DE PRUEBA CON CUOTA VENCIDA
        db.execSQL(
            "INSERT INTO clientes (nombre, apellido, dni, telefono, socio, nro_socio, apto_fisico, ultimo_pago) VALUES " +
                    "('Prueba', 'Vencido', 11223344, '1122334455', 1, 1, 1, date('now','-40 days'));"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS clientes")
        db.execSQL("DROP TABLE IF EXISTS actividades")
        db.execSQL("DROP TABLE IF EXISTS empleados")
        onCreate(db)
    }

    // CLIENTES

    fun insertarCliente(nombre: String, apellido: String, dni: Int, telefono: String, socio: Boolean): Int {
        val db = writableDatabase
        val values = ContentValues()

        // Si es socio, asigno un número nuevo. Si no, nro_socio = 0
        val nroSocio = if (socio) getNroSocio() else 0

        values.put("nombre", nombre)
        values.put("apellido", apellido)
        values.put("dni", dni)
        values.put("telefono", telefono)
        values.put("socio", socio)
        values.put("nro_socio", nroSocio)   // SIEMPRE guardo algo: >0 o 0

        db.insert("clientes", null, values)
        db.close()

        return nroSocio
    }

    private fun getNroSocio(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT MAX(nro_socio) FROM clientes", null)

        var nuevoNumero = 1  // Si es el 1er socio

        if (cursor.moveToFirst()) {
            val ultimo = cursor.getInt(0)
            if (!cursor.isNull(0)) {
                nuevoNumero = ultimo + 1
            }
        }

        cursor.close()
        return nuevoNumero
    }


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
    fun obtenerActividades(): List<Triple<Int, String, Int>>{
        val db = readableDatabase
        val lista = mutableListOf<Triple<Int, String, Int>>()
        val cursor = db.rawQuery("SELECT id, actividad, cupo FROM actividades", null)
        if(cursor.moveToFirst()){
            do{
                val id = (cursor.getInt(0))
                val nombre = (cursor.getString(1))
                val cupo = (cursor.getInt(2) ?: 0)
                lista.add(Triple(id, nombre, cupo))
            } while(cursor.moveToNext())
        }
        return lista
    }

    fun eliminarActividad(nombre: String){
        val db = writableDatabase
        db.delete("actividades", "actividad = ?", arrayOf(nombre))
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

    // EMPLEADOS
    fun validarEmpleado(usuario: String, contraseña: String): Boolean {
        val db = readableDatabase
        var esValido = false

        val cursor = db.rawQuery(
            "SELECT e_legajo FROM empleados WHERE e_usuario = ? AND e_contraseña = ?",
            arrayOf(usuario, contraseña)
        )

        if (cursor.moveToFirst()) {
            esValido = true
        }

        cursor.close()
        db.close()
        return esValido
    }

}