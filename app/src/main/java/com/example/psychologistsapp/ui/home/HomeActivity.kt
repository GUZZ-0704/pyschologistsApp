package com.example.psychologistsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologistsapp.R
import com.example.psychologistsapp.databinding.ActivityHomeBinding
import com.example.psychologistsapp.models.Category
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB
import com.example.psychologistsapp.ui.psychologist.PsychologistDetailActivity
import com.example.psychologistsapp.ui.adapters.CategoryAdapter
import com.example.psychologistsapp.ui.adapters.PsychologistAdapter
import com.example.psychologistsapp.ui.appointment.AppointmentActivity
import com.example.psychologistsapp.ui.map.MapActivity
import com.example.psychologistsapp.ui.user.ProfileActivity

class HomeActivity : AppCompatActivity(), CategoryAdapter.OnCategoryClickListener, PsychologistAdapter.OnPsychologistClickListener{
    private lateinit var binding: ActivityHomeBinding
    private val model: HomeViewModel by viewModels()
    private lateinit var user: User
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
        user = intent.getSerializableExtra("user") as User
        setUpRecyclerView()
        setUpViewModelObservers()
        setupCategoriesRecyclerView()
        setupEventListeners()
    }

    private fun setUpViewModelObservers() {
        model.categorySelected.observe(this) {
            val adapter = binding.lstPsychologists.adapter as PsychologistAdapter
            if (model.categorySelected.value?.isEmpty() == true){
                adapter.updatePsychologistList(UsersDB.getPyshologists())
                return@observe
            }else{
                adapter.updatePsychologistList(model.filterPsychologists())
            }
        }
    }

    private fun setUpRecyclerView() {
        val adapter = PsychologistAdapter(UsersDB.getPyshologists(),this)
        binding.lstPsychologists.layoutManager = LinearLayoutManager(this)
        binding.lstPsychologists.adapter = adapter

    }

    private fun setupCategoriesRecyclerView() {
        val adapter = CategoryAdapter(ArrayList(Category.entries), this)
        binding.layoutFilter.bringToFront()
        binding.lstCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.lstCategories.adapter = adapter
    }

    private fun setupEventListeners() {
        binding.imgFilter.setOnClickListener {
            if (binding.layoutFilter.visibility == View.VISIBLE) {
                binding.layoutFilter.visibility = View.GONE
            } else {
                binding.layoutFilter.visibility = View.VISIBLE
            }
        }
        binding.btnHome.isEnabled = false
        binding.btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
        binding.btnAppointment.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    override fun onCategoryClick(category: Category) {
        val adapter = binding.lstCategories.adapter as CategoryAdapter
        if (model.isCategorySelected(category)){
            model.removeCategory(category)
            adapter.selectedCategories.remove(category)
        }else{
            model.addCategory(category)
            adapter.selectedCategories.add(category)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onPsychologistClick(psychologist: User) {
        val intent = Intent(this, PsychologistDetailActivity::class.java)
        intent.putExtra("psychologist", psychologist)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}