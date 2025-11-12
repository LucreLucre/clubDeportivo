package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagoActividadRegistradoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago_actividad_registrado)
        val btnMostrarFactura = findViewById<Button>(R.id.btnMostrarFactura)
        btnMostrarFactura.setOnClickListener {
            val intent = Intent(this, FacturaDigitalActividadActivity::class.java)
            startActivity(intent)
        }
    }
}