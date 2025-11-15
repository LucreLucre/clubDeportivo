package com.example.clubdeportivo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate.now

class PagarActividadActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pagar_actividad)

        val actividad = intent.getStringExtra("actividad")
        val tvActividad = findViewById<TextView>(R.id.tvPagarActividad)
        tvActividad.text = "Actividad: $actividad"

        val rgMetodoPago = findViewById<RadioGroup>(R.id.rgMetodoPago)
        val metodo = rgMetodoPago.checkedRadioButtonId

        val etMonto = findViewById<EditText>(R.id.etMonto)
        val monto = etMonto.text

        val btnPagar = findViewById<Button>(R.id.btnPagarActividad)

        btnPagar.setOnClickListener {
            val intent = Intent(this, PagoActividadRegistradoActivity::class.java)
            intent.putExtra("actividad", actividad)
            intent.putExtra("monto", monto)
            intent.putExtra("metodo", metodo)
            startActivity(intent)
        }
    }
}