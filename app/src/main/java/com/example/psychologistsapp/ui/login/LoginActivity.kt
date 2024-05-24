package com.example.psychologistsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityLoginBinding
import com.example.psychologistsapp.ui.home.HomeActivity
import com.example.psychologistsapp.ui.register.RegisterActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val model: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
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
        model.goToHomeScreen.observe(this) {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("user", model.getUser())
                startActivity(intent)
                finish()
            }
        }
        model.errorMessages.observe(this){
            if (it.isEmpty()){
                return@observe
            }
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupOnClickListeners() {
        binding.btnLogin.setOnClickListener {
            model.login(binding.txtEmailLogin.text.toString(), binding.txtPasswordLogin.text.toString())
        }
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}