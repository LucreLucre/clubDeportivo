package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrarSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_socio)


        val dbHelper = DBHelper(this)
        val etNombre = findViewById<EditText>(R.id.etNombreSocio)
        val etApellido = findViewById<EditText>(R.id.etApellidoSocio)
        val etDni = findViewById<EditText>(R.id.etDNISocio)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoSocio)
        val cbSocio = findViewById<CheckBox>(R.id.cbSocio)

        val btnRegistrarSocio = findViewById<Button>(R.id.btnRegistrarSocio)
        btnRegistrarSocio.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val dni = etDni.text.toString().toIntOrNull() ?: 0
            val telefono = etTelefono.text.toString()
            val socio = cbSocio.isChecked

            dbHelper.insertarCliente(nombre, apellido, dni, telefono, socio)

            val intent = Intent(this, RegistroExitosoSocioActivity::class.java)
            startActivity(intent)
        }
    }
}