package com.example.psychologistsapp.ui.appointment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityMakeAnAppointmentBinding
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.ui.adapters.TimeSlotAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MakeAnAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeAnAppointmentBinding
    var pyschologist : User? = null
    var user : User? = null
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
        pyschologist = intent.getSerializableExtra("psychologist") as User?
        user = intent.getSerializableExtra("user") as User?
        setuprecyclerView()
        setupEventListeners()
    }

    private fun setuprecyclerView() {
        //val timeSlots = generateTimeSlots(pyschologist?.schedule!!.first, pyschologist!!.schedule.second)
        val timeSlots = generateTimeSlots("9:00", "18:00")
        val adapter = TimeSlotAdapter(arrayListOf())
        adapter.updateTimeSlots(timeSlots)
        binding.lstSchedulePsychologists.adapter = adapter

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
    }

    fun generateTimeSlots(start: String, end: String): List<String> {
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

        return timeSlots
    }

}