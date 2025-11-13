package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AsignarActividadActivity : AppCompatActivity() {
    var actividadSeleccionada: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asignar_actividad)


        val rvListaActividades = findViewById<RecyclerView>(R.id.rvListaActividades)
        rvListaActividades.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val dbHelper = DBHelper(this)
        val datos = dbHelper.obtenerActividades().toMutableList()


        val adapter = ActividadAdapter(datos, dbHelper)
        rvListaActividades.adapter = adapter


        val btnAsignar = findViewById<Button>(R.id.btnAsignarActividad)
        btnAsignar.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Asignar Actividad")
                .setPositiveButton("Asignar") {_,_ ->

                    val intent = Intent(this, PagarActividadActivity::class.java)
                    intent.putExtra("actividad", nombre)
                    startActivity(intent)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

    }
}