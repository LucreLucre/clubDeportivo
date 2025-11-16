package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val usuario = intent.getStringExtra("usuario")
        tvBienvenida.text = "Bienvenido, $usuario"

        val btnRegistrarSocio = findViewById<Button>(R.id.btnRegistrarSocio)
        btnRegistrarSocio.setOnClickListener{
            val intent = Intent(this, RegistrarSocioActivity::class.java)
            startActivity(intent)
        }

        val btnAsignarActividad = findViewById<Button>(R.id.btnAsignarActividad)
        btnAsignarActividad.setOnClickListener {
            val intent = Intent(this, AsignarActividadActivity::class.java)
            startActivity(intent)
        }

        val btnListarSociosCuotaVencida = findViewById<Button>(R.id.btnListarSociosCuotaVencida)
        btnListarSociosCuotaVencida.setOnClickListener {
            val intent = Intent(this, ListarSociosCuotaVencidaActivity::class.java)
            startActivity(intent)
        }

        val btnSalir = findViewById<TextView>(R.id.tvSalir)

        btnSalir.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Desea cerrar la sesión?")
                .setPositiveButton("Sí") { _, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)   // limpia actividades previas
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)    // inicia Login como nueva raíz
                    startActivity(intent)
                    finish()   // cierra la Activity actual
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}