package com.melendez.paulo.laboratoriocalificado03 // Asegúrate de que el paquete sea correcto


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melendez.paulo.laboratoriocalificado03.databinding.ItemTeacherBinding

class ProfesorAdapter(
    private val teachers: List<ProfesorResponse>,
    private val context: Context
) : RecyclerView.Adapter<ProfesorAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTeacherBinding.bind(view)

        fun bind(teacher: ProfesorResponse) {
            binding.tvName.text = "${teacher.name} ${teacher.last_name}"
            binding.tvEmail.text = teacher.email

            Glide.with(context)
                .load(teacher.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.ivTeacher)

            itemView.setOnClickListener {
                if (teacher.phone.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${teacher.phone}")
                    }
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "No se puede realizar la llamada", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            itemView.setOnLongClickListener {
                if (teacher.email.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${teacher.email}")
                    }
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "No se puede enviar el correo", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int = teachers.size
}