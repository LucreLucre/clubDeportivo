package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FacturaDigitalActividadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_factura_digital_actividad)
        val btnVerCarnet = findViewById<Button>(R.id.btnVerCarnet)
        btnVerCarnet.setOnClickListener {
            val intent = Intent(this, CarnetSocioActivity::class.java)
            startActivity(intent)
        }
    }
}