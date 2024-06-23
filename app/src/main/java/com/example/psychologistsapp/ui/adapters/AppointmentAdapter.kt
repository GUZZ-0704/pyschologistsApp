package com.example.psychologistsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.psychologistsapp.databinding.AppointmentListItemBinding
import com.example.psychologistsapp.models.Appointment

class AppointmentAdapter (
    private var appointmentList: ArrayList<Appointment>,
    private val listener: OnAppointmentClickListener

): RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    fun updateAppointmentList(newList: ArrayList<Appointment>) {
        appointmentList.clear()
        appointmentList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = AppointmentListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppointmentViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return appointmentList?.size?: 0
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList?.get(position)
        if (appointment != null) {
            holder.bind(appointment, listener)
        }
    }

    class AppointmentViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        fun bind(appointment: Appointment, listener: OnAppointmentClickListener) {
            val binding = AppointmentListItemBinding.bind(itemView)
            binding.lblNamePsycologist.text = appointment.psychologist.name
            binding.lblAppointmentDate.text = appointment.date
            binding.lblAppointmentTime.text = appointment.time
            binding.root.setOnClickListener {
                listener.onAppointmentClick(appointment)
            }
        }
    }

    interface OnAppointmentClickListener {
        fun onAppointmentClick(appointment: Appointment)
    }



}


