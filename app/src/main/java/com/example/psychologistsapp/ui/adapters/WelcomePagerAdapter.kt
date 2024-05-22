package com.example.psychologistsapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psychologistsapp.databinding.WelcomePagerItemBinding
import com.example.psychologistsapp.models.WelcomeMessage

class WelcomePagerAdapter(private val context: Context, private val welcomeMessages : List<WelcomeMessage>): RecyclerView.Adapter<WelcomePagerAdapter.WelcomeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WelcomePagerAdapter.WelcomeViewHolder {
        val binding = WelcomePagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WelcomeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WelcomePagerAdapter.WelcomeViewHolder, position: Int) {
        val welcomeMessage = welcomeMessages?.get(position)
        if (welcomeMessage != null) {
            Log.d("WelcomePagerAdapter", "welcomeMessage: $welcomeMessage")
            holder.bind(welcomeMessage)
        }
    }

    override fun getItemCount(): Int {
        return welcomeMessages?.size?: 0
    }

    inner class WelcomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(welcomeMessage: WelcomeMessage) {
            val binding = WelcomePagerItemBinding.bind(itemView)
            if (welcomeMessage.messageImage != null) {
                Log.d("WelcomeViewHolder", "messageImage: ${welcomeMessage.messageImage}")
                Glide.with(context)
                    .load(welcomeMessage.messageImage)
                    .into(binding.imgWelcomeMessage)
            }
        }
    }
}