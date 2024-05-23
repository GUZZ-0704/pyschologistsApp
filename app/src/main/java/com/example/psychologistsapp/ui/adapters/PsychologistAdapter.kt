package com.example.psychologistsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psychologistsapp.databinding.PshicologistListItemBinding
import com.example.psychologistsapp.models.User

class PsychologistAdapter (
    private var psychologistList: ArrayList<User>,
    private val listener: OnPsychologistClickListener

): RecyclerView.Adapter<PsychologistAdapter.PsychologistViewHolder>() {
    fun updatePsychologistList(newList: List<User>) {
        psychologistList.clear()
        psychologistList.addAll(newList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PsychologistViewHolder {
        val binding = PshicologistListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PsychologistViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return psychologistList.size
    }

    override fun onBindViewHolder(holder: PsychologistViewHolder, position: Int) {
        val psychologist = psychologistList[position]
        holder.bind(psychologist, listener)
    }

    class PsychologistViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, listener: OnPsychologistClickListener) {
            val binding = PshicologistListItemBinding.bind(itemView)
            binding.lblFullNamePsychologist.text = "${user.name} ${user.lastName}"
            binding.lblQualificationPsychologist.text = user.qualification
            Glide.with(itemView.context)
                .load(user.profileImage)
                .into(binding.imgPsychologist)
            binding.root.setOnClickListener {
                listener.onPsychologistClick(user)
            }

        }
    }

    interface OnPsychologistClickListener {
        fun onPsychologistClick(user: User)
    }
}