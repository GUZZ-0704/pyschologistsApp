package com.example.psychologistsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ItemTimeSlotBinding

class TimeSlotAdapter(private val timeSlots: ArrayList<String>) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_slot, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(timeSlots[position])
        }

        override fun getItemCount(): Int = timeSlots.size

        fun updateTimeSlots(timeSlots: List<String>) {
            this.timeSlots.clear()
            this.timeSlots.addAll(timeSlots)
            notifyDataSetChanged()
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = ItemTimeSlotBinding.bind(itemView)
            fun bind(timeSlot: String) {
                binding.textView.text = timeSlot
            }
        }
}