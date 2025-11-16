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

class PagoActividadRegistradoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago_actividad_registrado)

        val actividad = intent.getStringExtra("actividad")
        val tvActividad = findViewById<TextView>(R.id.tvPagoActividad)
        tvActividad.text = "Actividad: $actividad"

        val tvFecha = findViewById<TextView>(R.id.tvFechaActividad)
        tvFecha.text =  now().toString()

        val monto = intent.getStringExtra("monto")
        val tvImporte = findViewById<TextView>(R.id.tvImporte)
        tvImporte.text = monto

        val metodo = intent.getStringExtra("metodo")

        val btnVolver = findViewById<TextView>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnMostrarFactura = findViewById<Button>(R.id.btnMostrarFactura)
        btnMostrarFactura.setOnClickListener {
            val intent = Intent(this, FacturaDigitalActividadActivity::class.java)
            intent.putExtra("actividad", actividad)
            intent.putExtra("monto", monto)
            intent.putExtra("metodo", metodo)
            startActivity(intent)
        }
    }
}