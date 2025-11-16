package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AsignarActividadActivity : AppCompatActivity() {
    var actividadSeleccionada: Triple<Int, String, Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asignar_actividad)

        val rvListaActividades = findViewById<RecyclerView>(R.id.rvListaActividades)
        rvListaActividades.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val dbHelper = DBHelper(this)
        val datos = dbHelper.obtenerActividades().toMutableList()

        val adapter = ActividadAdapter(datos, dbHelper){ id, actividad, cupo ->
            actividadSeleccionada = Triple(id, actividad, cupo)
        }
        rvListaActividades.adapter = adapter

        val etDni = findViewById<EditText>(R.id.etDNIActividad)
        val dni = etDni.text

        val btnVolver = findViewById<TextView>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnAsignar = findViewById<Button>(R.id.btnAsignarActividad)
        btnAsignar.setOnClickListener{
            actividadSeleccionada?.let { (id, actividad, cupo) ->
                val intent = Intent(this, PagarActividadActivity::class.java)
                intent.putExtra("actividad", actividad)
                intent.putExtra("cupo", cupo)
                intent.putExtra("dni", dni)
                intent.putExtra("id", id)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Seleccione una actividad primero", Toast.LENGTH_SHORT).show()
            }
        }

    }
}