package com.example.psychologistsapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityHomeBinding
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.adapters.PsychologistAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val model: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        model.loadPsychologists()
        setUpRecyclerView()
        setUpViewModelObservers()
    }

    private fun setUpViewModelObservers() {
        model.psychologistList.observe(this) {
            val adapter = binding.lstPsychologists.adapter as PsychologistAdapter
            adapter.updatePsychologistList(it)
        }
    }

    private fun setUpRecyclerView() {
        val adapter = PsychologistAdapter(ArrayList(), object : PsychologistAdapter.OnPsychologistClickListener {
            override fun onPsychologistClick(user: User) {
                val intent = Intent(this@HomeActivity, PsychologistDetailActivity::class.java)
                intent.putExtra("psychologist", user)
                startActivity(intent)
            }
        })
        binding.lstPsychologists.layoutManager = LinearLayoutManager(this)
        binding.lstPsychologists.adapter = adapter

    }
}