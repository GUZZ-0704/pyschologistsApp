package com.example.psychologistsapp.ui.map

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityAppointmentBinding
import com.example.psychologistsapp.databinding.ActivityMapBinding
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.appointment.AppointmentActivity
import com.example.psychologistsapp.ui.appointment.AppointmentViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityMapBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.mapView2.onCreate(savedInstanceState)
        binding.mapView2.getMapAsync(this)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnHome.setOnClickListener {
            finish()
        }
        binding.btnAppointment.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }

        binding.btnMap.isEnabled = false
        /*binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }*/

    }

    override fun onMapReady(p0: GoogleMap) {

    }

    override fun onResume() {
        super.onResume()
        binding.mapView2.onResume()
    }

    override fun onPause() {
        binding.mapView2.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.mapView2.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView2.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView2.onSaveInstanceState(outState)
    }
}