package com.example.psychologistsapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityMainBinding
import com.example.psychologistsapp.models.WelcomeMessage
import com.example.psychologistsapp.models.WelcomeMessagesDB
import com.example.psychologistsapp.ui.MainViewModel
import com.example.psychologistsapp.ui.login.LoginActivity
import com.example.psychologistsapp.ui.adapters.WelcomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewPager()
        setupOnClickListeners()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.icon = getDrawable(R.drawable.dot)
        }.attach()
    }

    private fun setupViewPager() {
        model.setupData()
        val adapter = WelcomePagerAdapter(this, WelcomeMessagesDB.welcomeMessages as List<WelcomeMessage>)
        binding.viewPager.adapter = adapter
    }

    private fun setupOnClickListeners() {
        binding.btnFormer.setOnClickListener {
            binding.viewPager.currentItem = (binding.viewPager.currentItem - 1).coerceAtLeast(0)
        }
        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < WelcomeMessagesDB.welcomeMessages.size - 1) {
                binding.viewPager.currentItem = (binding.viewPager.currentItem + 1).coerceAtMost(2)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}