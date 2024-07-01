package com.example.psychologistsapp.ui.appointment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityMakeAnAppointmentBinding
import com.example.psychologistsapp.models.Appointment
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.adapters.TimeSlotAdapter
import com.example.psychologistsapp.ui.payment.PaymentMethodActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MakeAnAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeAnAppointmentBinding
    private lateinit var pyschologist : User
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMakeAnAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pyschologist = intent.getSerializableExtra("psychologist") as User
        user = intent.getSerializableExtra("user") as User
        setuprecyclerView()
        setupEventListeners()
    }

    private fun setuprecyclerView() {
        //val timeSlots = generateTimeSlots(pyschologist?.schedule!!.first, pyschologist!!.schedule.second)
        val timeSlots = generateTimeSlots("9:00", "18:00")
        val adapter = TimeSlotAdapter(arrayListOf())
        binding.lstSchedulePsychologists.layoutManager = LinearLayoutManager(this)
        Log.i("MakeAnAppointmentActivity", "TimeSlots: $timeSlots")
        binding.lstSchedulePsychologists.adapter = adapter
        adapter.updateTimeSlots(timeSlots)
    }

    private fun setupEventListeners() {
        binding.btnShowCalendar.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                binding.lblShowDate.text = selectedDate
            }, year, month, day)
            datePickerDialog.show()
        }
        binding.btnMakeAnAppointmentMakeAnAppointmentActivity.setOnClickListener {
            saveAppointment()
            val intent = Intent(this, PaymentMethodActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("appointment", user?.appointments?.last())
            startActivity(intent)
            finish()
        }
    }

    private fun saveAppointment() {
        val date = binding.lblShowDate.text.toString()
        val adapter = binding.lstSchedulePsychologists.adapter as? TimeSlotAdapter
        val time = adapter?.getSelectedTimeSlot()?.toString()
        val opcion = binding.rbtnOpciones.checkedRadioButtonId

        if (time == null) {
            Toast.makeText(this, "Seleccione una hora", Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(this, "Hora seleccionada: $time", Toast.LENGTH_SHORT).show()
        }

        if (opcion == -1) {
            Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show()
            return
        }

        if (pyschologist == null || user == null) {
            Toast.makeText(this, "Psychologist or user is null", Toast.LENGTH_SHORT).show()
            return
        }

        val appointment = Appointment(
            date = date,
            time = time,
            psychologist = pyschologist!!,
            patient = user!!,
            isAtHome = opcion == R.id.rbtnOpcionHome
        )

        pyschologist!!.addAppointment(appointment)
        user!!.addAppointment(appointment)
    }

    fun generateTimeSlots(start: String, end: String): ArrayList<String> {
        val timeSlots = mutableListOf<String>()
        val format = SimpleDateFormat("H:mm", Locale.getDefault())
        val startDate = format.parse(start)
        val endDate = format.parse(end)
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        while (calendar.time.before(endDate) || calendar.time == endDate) {
            timeSlots.add(format.format(calendar.time))
            calendar.add(Calendar.HOUR, 1)
        }

        return timeSlots as ArrayList<String>
    }

}