package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegistrarSocioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_socio)

        val dbHelper = DBHelper(this)

        val btnVolver = findViewById<TextView>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }

        val etNombre   = findViewById<EditText>(R.id.etNombreSocio)
        val etApellido = findViewById<EditText>(R.id.etApellidoSocio)
        val etDni      = findViewById<EditText>(R.id.etDNISocio)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoSocio)
        val cbSocio    = findViewById<CheckBox>(R.id.cbSocio)
        val btnRegistrarSocio = findViewById<Button>(R.id.btnRegistrarSocio)

        btnRegistrarSocio.setOnClickListener {

            val nombre   = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val dniTexto = etDni.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val socio    = cbSocio.isChecked

            // ---------- VALIDACIONES ----------
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Complete el campo Nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (apellido.isEmpty()) {
                Toast.makeText(this, "Complete el campo Apellido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dniTexto.isEmpty()) {
                Toast.makeText(this, "Complete el campo DNI", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dni = dniTexto.toIntOrNull()
            if (dni == null) {
                Toast.makeText(this, "El DNI debe ser numérico", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (telefono.isEmpty()) {
                Toast.makeText(this, "Complete el campo Teléfono", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // ---------- FIN VALIDACIONES ----------

            // Registramos
            val nroSocio = dbHelper.insertarCliente(nombre, apellido, dni, telefono, socio)

            // ---------- MENSAJE DE ÉXITO ----------
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // ---------- NAVEGACIÓN SEGÚN CHECKBOX ----------
            if (socio) {
                // Es socio -> voy a pantalla de registro exitoso
                val intent = Intent(this, RegistroExitosoSocioActivity::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellido", apellido)
                intent.putExtra("numero", nroSocio)
                startActivity(intent)
            } else {
                // NO es socio -> vuelvo al menú principal
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}