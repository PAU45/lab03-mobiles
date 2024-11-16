package com.melendez.paulo.laboratoriocalificado03

data class Profesor(
    val name: String,
    val last_name: String,  // Cambiado de lastName a last_name para coincidir con el JSON
    val phone: String,
    val email: String,
    val imageUrl: String  // Cambiado de photo a imageUrl
)