package com.example.clubdeportivo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.Instant.now

class FacturaDigitalActividadActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_factura_digital_actividad)

        val actividad = intent.getStringExtra("actividad")
        val monto = intent.getStringExtra("monto")
        val metodo = intent.getStringExtra("metodo")

        val tvActividad = findViewById<TextView>(R.id.tvNombreActividad)
        tvActividad.text = actividad

        val tvMonto = findViewById<TextView>(R.id.tvMontoActividad)
        tvMonto.text = monto

        val tvMetodo = findViewById<TextView>(R.id.tvMedioActividad)
        tvMetodo.text = metodo

        val tvFecha = findViewById<TextView>(R.id.tvFechaActividad)
        tvFecha.text = now().toString()
    }
}