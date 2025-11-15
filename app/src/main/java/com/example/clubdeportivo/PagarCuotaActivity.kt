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

class PagarCuotaActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pagar_cuota)
        val tvNroSocio = findViewById<TextView>(R.id.tvNroSocio)
        val nro_socio = intent.getIntExtra("numero", 0)
        tvNroSocio.text = "Nº de socio: $nro_socio"

        val tvnombreApellido = findViewById<TextView>(R.id.tvNombreApellido)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvnombreApellido.text = "$apellido, $nombre"

        val btnPagar = findViewById<Button>(R.id.btnPagarCuota)
        btnPagar.setOnClickListener {
            if (nro_socio != null) {
                val dbHelper = DBHelper(this)
                dbHelper.actualizarUltimoPago(nro_socio)
            }
            val intent = Intent(this, PagoCuotaRegistradoActivity::class.java)
            intent.putExtra("numero", nro_socio)
            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            startActivity(intent)
        }
    }
}