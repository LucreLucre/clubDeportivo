package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroExitosoSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_exitoso_socio)
        val tvNumero = findViewById<TextView>(R.id.tvNumeroRegistro)
        val nro_socio = intent.getIntExtra("numero", 0)
        tvNumero.text = "Nº de socio: $nro_socio"

        val tvnombreApellido = findViewById<TextView>(R.id.tvNombreApellidoRegistro)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        tvnombreApellido.text = "$apellido, $nombre"

        val btnPagarCuotaSocio = findViewById<Button>(R.id.btnPagarCuotaSocio)
        btnPagarCuotaSocio.setOnClickListener {
            if(nro_socio != null){
                val intent = Intent(this, PagarCuotaActivity::class.java)
                intent.putExtra("numero", nro_socio)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellido", apellido)
                startActivity(intent)
            }else{
                AlertDialog.Builder(this)
                    .setTitle("El cliente no es socio")
                    .setMessage("El cliente no es socio")
                    .setNeutralButton("Aceptar", null)
                    .show()
            }

        }
    }
}