package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ListarSociosCuotaVencidaActivity : AppCompatActivity() {
    var socioSeleccionado: Triple<Int, String, String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar_socios_cuota_vencida)

        val rvVencimientos = findViewById<RecyclerView>(R.id.rvListaVencimientos)
        rvVencimientos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val dbHelper = DBHelper(this)
        val datos = dbHelper.obtenerClientesVencidos().toMutableList()

        val adapter = DatoAdapter(datos, dbHelper) { nro_socio, nombre, apellido ->
            socioSeleccionado = Triple(nro_socio, nombre, apellido)
        }

        rvVencimientos.adapter = adapter

        val btnVolver = findViewById<TextView>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnPagarCuota = findViewById<Button>(R.id.btnPagarCuota)
        btnPagarCuota.setOnClickListener {
            socioSeleccionado?.let { (nro_socio, nombre, apellido) ->
                val intent = Intent(this, PagarCuotaActivity::class.java)
                intent.putExtra("numero", nro_socio)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellido", apellido)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Seleccione un socio primero", Toast.LENGTH_SHORT).show()
            }
        }
    }
}