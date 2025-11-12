package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AsignarActividadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asignar_actividad)


            val btnasig = findViewById<Button>(R.id.btnasig)
            btnasig.setOnClickListener {
                val intent = Intent(this, PagarActividadActivity::class.java)
                startActivity(intent)
        }
    }
}