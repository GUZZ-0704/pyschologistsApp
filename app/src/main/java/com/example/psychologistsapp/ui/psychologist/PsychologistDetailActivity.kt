package com.example.psychologistsapp.ui.psychologist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityPsychologistDetailBinding
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.appointment.MakeAnAppointmentActivity

class PsychologistDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPsychologistDetailBinding
    private lateinit var psychologist: User
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPsychologistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        psychologist = intent.getSerializableExtra("psychologist") as User
        user = intent.getSerializableExtra("user") as User
        displayPsychologistDetails(psychologist)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnMakeAnAppointment.setOnClickListener {
            val intent = Intent(this, MakeAnAppointmentActivity::class.java)
            intent.putExtra("psychologist", psychologist)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun displayPsychologistDetails(psychologist: User) {
        binding.lblNamePsychologistDetailActivity.text = "${psychologist.name} ${psychologist.lastName}"
        binding.lblQualificationPsychologistDetailActivity.text = psychologist.qualification
        binding.lblDescriptionPsychologistDetailActivity.text = psychologist.description
        Glide.with(this)
            .load(psychologist.profilePicture)
            .into(binding.imgProfilePsychologistDetailActivity)
    }
}