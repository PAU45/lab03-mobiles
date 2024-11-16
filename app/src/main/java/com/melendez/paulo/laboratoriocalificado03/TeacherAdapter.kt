package com.melendez.paulo.laboratoriocalificado03  // Asegúrate de que el paquete sea correcto

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.melendez.paulo.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.melendez.paulo.laboratoriocalificado03.Teacher;

class TeacherAdapter(
    private val context: Context,
    private val teachers: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            binding.tvFirstName.text = teacher.name
            binding.tvLastName.text = teacher.lastName
            Picasso.get().load(teacher.photoUrl).into(binding.ivPhoto)

            binding.root.setOnClickListener {
                // Click simple: llamar al número del profesor
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${teacher.phoneNumber}"))
                context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                // Click largo: enviar un correo electrónico
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${teacher.email}"))
                context.startActivity(intent)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int = teachers.size
}
