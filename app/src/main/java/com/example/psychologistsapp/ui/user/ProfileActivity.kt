package com.example.psychologistsapp.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityProfileBinding
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.appointment.AppointmentActivity
import com.example.psychologistsapp.ui.home.HomeActivity
import com.example.psychologistsapp.ui.login.LoginActivity
import com.example.psychologistsapp.ui.map.MapActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        user = intent.getSerializableExtra("user") as User
        displayUserDetails(user)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.lblFullInformationUser.setOnClickListener {
            val intent = Intent(this, FullInformationActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
        binding.btnProfileProfileUserActivity.isEnabled = false
        binding.btnHomeProfileUserActivity.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        binding.btnMapProfileUserActivity.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        binding.btnAppointmentProfileUserActivity.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayUserDetails(user: User) {
        binding.lblFullnameProfile.text = "${user.name} ${user.lastName}"
        binding.imgProfileUser.setImageResource(user.profilePicture)
    }
}