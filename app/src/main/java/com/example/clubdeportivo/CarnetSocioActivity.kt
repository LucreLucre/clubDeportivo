package com.example.clubdeportivo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarnetSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carnet_socio)

        val tvNumeroCarnet = findViewById<TextView>(R.id.tvNumeroCarnet)
        val nro_socio = intent.getIntExtra("numero", 0)
        tvNumeroCarnet.text = "Nº de socio: $nro_socio"

        val tvSocioCarnet = findViewById<TextView>(R.id.tvSocioCarnet)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvSocioCarnet.text = "$apellido, $nombre"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}