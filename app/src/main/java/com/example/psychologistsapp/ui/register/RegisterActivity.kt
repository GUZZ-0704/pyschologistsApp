package com.example.psychologistsapp.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val model: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupOnClickListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        model.goToLoginScreen.observe(this){
            if (it) {
                finish()
            }
        }
        model.errorMessages.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupOnClickListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                model.registerUser(
                    txtName.text.toString(),
                    txtLastName.text.toString(),
                    txtEmail.text.toString(),
                    txtPassword.text.toString(),
                    txtPhoneNumber.text.toString()
                )
            }
        }
    }
}