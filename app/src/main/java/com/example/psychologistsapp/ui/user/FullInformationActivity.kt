package com.example.psychologistsapp.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityFullInformationBinding
import com.example.psychologistsapp.models.User

class FullInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullInformationBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFullInformationBinding.inflate(layoutInflater)
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
        binding.btnEditInformation.setOnClickListener {
            binding.txtNameUser.isEnabled = true
            binding.txtLastNameUser.isEnabled = true
            binding.txtUserName.isEnabled = true
            binding.txtPasswordUser.isEnabled = true
            binding.txtPhoneNumberUser.isEnabled = true
        }

        binding.btnSaveInformation.setOnClickListener {
            user.name = binding.txtNameUser.text.toString()
            user.lastName = binding.txtLastNameUser.text.toString()
            user.userName = binding.txtUserName.text.toString()
            user.password = binding.txtPasswordUser.text.toString()
            user.phoneNumber = binding.txtPhoneNumberUser.text.toString()
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }


    private fun displayUserDetails(user: User) {
        binding.txtNameUser.setText(user.name)
        binding.txtNameUser.isEnabled = false
        binding.txtLastNameUser.setText(user.lastName)
        binding.txtLastNameUser.isEnabled = false
        binding.txtUserName.setText(user.userName)
        binding.txtUserName.isEnabled = false
        binding.txtPasswordUser.setText(user.password)
        binding.txtPasswordUser.isEnabled = false
        binding.txtPhoneNumberUser.setText(user.phoneNumber)
        binding.txtPhoneNumberUser.isEnabled = false
    }
}