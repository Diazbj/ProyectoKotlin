package com.example.ejemploexpo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ejemploexpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Variable para contar los intentos de ingreso de clave
    private var contadorIntentos = 0

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener referencias a las vistas de la actividad
        val Clave: EditText = findViewById(R.id.editTextClave)
        val boton: Button = findViewById(R.id.btnIngresar)
        val textoMensaje: TextView = findViewById(R.id.textAcceso)

        // Configurar el evento click del botón
        boton.setOnClickListener {
            // Obtener la clave ingresada por el usuario
            val claveIngresada = Clave.text.toString()

            // Verificar si la clave ingresada es correcta
            if (claveIngresada == "clave1234") {
                // Mostrar un diálogo indicando acceso permitido
                mostrarDialogo("Acceso permitido")
                // Reiniciar el contador de intentos
                contadorIntentos = 0

                // Mostrar la cantidad de números en la clave
                textoMensaje.text = "Su clave tiene " + contarNumeros(claveIngresada) + " números"

            } else {
                // Incrementar el contador de intentos
                contadorIntentos++
                // Verificar si el usuario ha excedido el límite de intentos
                if (contadorIntentos >= 3) {
                    // Mostrar un diálogo indicando que el usuario está bloqueado
                    mostrarDialogo("Usuario bloqueado")
                    // Reiniciar el contador de intentos
                    contadorIntentos = 0
                } else {
                    // Mostrar un diálogo indicando clave incorrecta y el número de intentos restantes
                    mostrarDialogo("Clave incorrecta. Intento $contadorIntentos de 3.")
                }
            }
        }
    }

    // Función para mostrar un diálogo con un mensaje dado
    private fun mostrarDialogo(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensaje")
        //dependiendo de la condicion muestra el mensaje
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

    // Función para contar la cantidad de números en una cadena
    fun contarNumeros(clave: String): Int {
        var contadorNumeros = 0

        // Recorrer cada caracter de la clave
        for (caracter in clave) {
            // Verificar si el caracter es un número
            if (caracter.isDigit()) {
                // Incrementar el contador de números
                contadorNumeros++
            }
        }

        // Devolver la cantidad de números encontrados en la clave
        return contadorNumeros
    }
}

