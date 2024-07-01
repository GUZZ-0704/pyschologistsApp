package com.example.psychologistsapp.ui.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityPaymentMethodBinding
import com.example.psychologistsapp.models.Appointment
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.appointment.AppointmentActivity
import com.example.psychologistsapp.ui.home.HomeActivity

class PaymentMethodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding
    private lateinit var user: User
    private lateinit var appointment: Appointment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        user = intent.getSerializableExtra("user") as User
        appointment = intent.getSerializableExtra("appointment") as Appointment

        Glide.with(this)
            .load(R.drawable.qr)
            .into(binding.imgQr)
        Glide.with(this)
            .load(R.drawable.tarjeta)
            .into(binding.imgTarjeta)
        binding.imgTarjeta.visibility = View.GONE
        binding.chkEfectivo.visibility = View.GONE
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.apply {
            btnQr.setOnClickListener {
                imgTarjeta.visibility = View.GONE
                chkEfectivo.visibility = View.GONE
                imgQr.visibility = View.VISIBLE
            }
            btnTarjeta.setOnClickListener {
                imgQr.visibility = View.GONE
                chkEfectivo.visibility = View.GONE
                imgTarjeta.visibility = View.VISIBLE
            }
            btnEfectivo.setOnClickListener {
                imgQr.visibility = View.GONE
                imgTarjeta.visibility = View.GONE
                chkEfectivo.visibility = View.VISIBLE
                chkEfectivo.isChecked = true
            }
            btnPagar.setOnClickListener {
                Toast.makeText(this@PaymentMethodActivity, "Pago realizado con éxito", Toast.LENGTH_SHORT).show()
                appointment!!.isPaid = true
                val intent = Intent(this@PaymentMethodActivity, HomeActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
    }
}