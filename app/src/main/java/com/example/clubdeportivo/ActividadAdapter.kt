package com.example.clubdeportivo

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActividadAdapter(
    private val datos: MutableList<Pair<String,Int>>,
    private val dbHelper: DBHelper
): RecyclerView.Adapter<ActividadAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemActividad: TextView = view.findViewById(R.id.tvItemActividad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.`item_actividad`, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (nombre, cupo) = datos[position]
        holder.tvItemActividad.text = "$nombre - Cupos disponibles: $cupo"

        holder.itemView.setOnClickListener(){
            val actividad = nombre
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar actividad")
                .setMessage("¿Desea eliminar la actividad?")
                .setPositiveButton("Sí") {_,_ ->
                    dbHelper.eliminarActividad(nombre)
                    datos.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }

    override fun getItemCount() = datos.size
}