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
import java.time.LocalDate.now

class FacturaDigitalCuotaSocioActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_factura_digital_cuota_socio)
        val tvNumeroFactura = findViewById<TextView>(R.id.tvNumeroFactura)
        val nro_socio = intent.getIntExtra("numero", 0)
        tvNumeroFactura.text = "Nº de socio: $nro_socio"

        val tvSocioFactura = findViewById<TextView>(R.id.tvSocioFactura)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvSocioFactura.text = "$apellido, $nombre"

        val tvFecha = findViewById<TextView>(R.id.tvFechaFactura)
        tvFecha.text = now().toString()


        val btnVerCarnet = findViewById<Button>(R.id.btnVerCarnet)
        btnVerCarnet.setOnClickListener {
            val intent = Intent(this, CarnetSocioActivity::class.java)
            intent.putExtra("numero", nro_socio)
            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            startActivity(intent)
        }
    }
}