package com.melendez.paulo.laboratoriocalificado03
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melendez.paulo.laboratoriocalificado03.databinding.ActivityEjercicio01Binding

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var viewModel: ProfesorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ProfesorViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTeachers()
    }

    private fun setupRecyclerView() {
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        viewModel.teacherList.observe(this) { teachers ->
            binding.rvTeachers.adapter = ProfesorAdapter(teachers, this)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }
    }
}