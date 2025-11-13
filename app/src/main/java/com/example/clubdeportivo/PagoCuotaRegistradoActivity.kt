package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagoCuotaRegistradoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago_cuota_registrado)
        val tvNumero = findViewById<TextView>(R.id.tvNumero)
        val nro_socio = intent.getIntExtra("numero", 0)
        tvNumero.text = "Nº de socio: $nro_socio"

        val tvSocio = findViewById<TextView>(R.id.tvSocio)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvSocio.text = "$apellido, $nombre"


        val btnMostrarFactura= findViewById<Button>(R.id.btnMostrarFactura)
        btnMostrarFactura.setOnClickListener {
            val intent = Intent(this, FacturaDigitalCuotaSocioActivity::class.java)
            intent.putExtra("numero", nro_socio)
            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            startActivity(intent)
        }
    }
}