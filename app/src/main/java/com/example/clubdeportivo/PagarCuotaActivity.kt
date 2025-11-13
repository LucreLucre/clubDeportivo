package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagarCuotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pagar_cuota)
        val tvNroSocio = findViewById<TextView>(R.id.tvNroSocio)
        val nro_socio = intent.getStringExtra("numero")
        tvNroSocio.text = "Nº de socio: $nro_socio"

        val tvnombreApellido = findViewById<TextView>(R.id.tvNombreApellido)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvnombreApellido.text = "$apellido, $nombre"

        val btnPagar = findViewById<Button>(R.id.btnPagarCuota)
        btnPagar.setOnClickListener {
            val intent = Intent(this, PagoCuotaRegistradoActivity::class.java)
            startActivity(intent)
        }
    }
}