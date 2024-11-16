package com.melendez.paulo.laboratoriocalificado03


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melendez.paulo.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProfesorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(ProfesorViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTeachers()
    }

    private fun setupRecyclerView() {
        // Configurar RecyclerView
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        // Observar cambios en los datos del ViewModel
        viewModel.teacherList.observe(this) { teachers ->
            val adapter = ProfesorAdapter(teachers, this)
            binding.rvTeachers.adapter = adapter
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }
}