package com.example.psychologistsapp.ui.appointment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityAppointmentBinding
import com.example.psychologistsapp.models.Appointment
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.adapters.AppointmentAdapter

class AppointmentActivity : AppCompatActivity(), AppointmentAdapter.OnAppointmentClickListener{
    lateinit var binding: ActivityAppointmentBinding
    private lateinit var user: User
    private val model: AppointmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        user = intent.getSerializableExtra("user") as User
        model.getUser(user)
        model.getAppointments()
        setupRecyclerView()
        setupViewModelObservers()
        setupEventListeners()
    }

    private fun setupViewModelObservers() {
        model.appointmentList.observe(this) { appointments ->
            val adapter = binding.lstAppointments.adapter as AppointmentAdapter
            adapter.updateAppointmentList(appointments)
        }
    }

    private fun setupEventListeners() {
        binding.btnHomeHomeActivity.setOnClickListener {
            finish()
        }
        /*binding.btnMapAppointmentActivity.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnProfileAppointmentActivity.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }*/


    }

    private fun setupRecyclerView() {
        val adapter = AppointmentAdapter(arrayListOf(), this)
        binding.lstAppointments.layoutManager = LinearLayoutManager(this)
        binding.lstAppointments.adapter = adapter
    }

    override fun onAppointmentClick(appointment: Appointment) {
        TODO("Not yet implemented")
    }
}