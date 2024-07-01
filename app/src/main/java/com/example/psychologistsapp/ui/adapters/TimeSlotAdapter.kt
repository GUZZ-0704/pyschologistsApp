package com.example.psychologistsapp.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ItemTimeSlotBinding

class TimeSlotAdapter(private val timeSlots: ArrayList<String>) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {
    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_slot, parent, false)
            return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.bind(timeSlot)

        if (selectedPosition == position) {
            // Cambia el color de fondo del elemento seleccionado a gris claro
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        } else {
            // Restaura el color de fondo del elemento no seleccionado
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

        override fun getItemCount(): Int = timeSlots.size

        fun updateTimeSlots(timeSlots: List<String>) {
            this.timeSlots.clear()
            this.timeSlots.addAll(timeSlots)
            notifyDataSetChanged()
        }

    fun getSelectedTimeSlot(): String? {
        return if (selectedPosition != -1) timeSlots[selectedPosition] else null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(timeSlot: String) {
            val binding = ItemTimeSlotBinding.bind(itemView)
            binding.textView.text = timeSlot

            itemView.setOnClickListener {
                selectedPosition = layoutPosition
                notifyDataSetChanged()  // Notifica al adaptador que los datos han cambiado para que pueda actualizar la vista
            }
        }
    }
}