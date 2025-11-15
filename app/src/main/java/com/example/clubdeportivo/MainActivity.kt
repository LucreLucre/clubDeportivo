package com.example.clubdeportivo

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Instanciamos el helper de la BD
        dbHelper = DBHelper(this)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val pass = etPassword.text.toString()

            if(usuario.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val esValido = dbHelper.validarEmpleado(usuario, pass)

                if (esValido) {
                    val intent = Intent(this, MenuPrincipalActivity::class.java)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Usuario o contraseña incorrectas",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}