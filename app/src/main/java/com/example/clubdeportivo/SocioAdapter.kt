package com.example.clubdeportivo

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DatoAdapter(
    private val datos: MutableList<Triple<Int,String,String>>,
    private val dbHelper: DBHelper,
    // para pasarlo en el intent
    private val onItemClick: (Int, String, String) -> Unit
): RecyclerView.Adapter<DatoAdapter.ViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItemSocio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_socio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (nro_socio,nombre, apellido) = datos[position]
        holder.tvItem.text = "$nro_socio: $apellido, $nombre"

        holder.itemView.isSelected = position == selectedPosition
        holder.itemView.setBackgroundColor(
            if (position == selectedPosition) 0xFFE0E0E0.toInt() else 0xFFFFFFFF.toInt()
        )

        holder.itemView.setOnClickListener{
            onItemClick(nro_socio,nombre, apellido)
        }

    }

    override fun getItemCount() = datos.size
}