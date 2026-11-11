package com.atom.examen

data class Task(
    val id: Int,
    val nombre: String,
    val categoria: String,        // "Escuela", "Trabajo" o "Personal"
    val esPrioridadAlta: Boolean, // Controlado por el Switch
    var isTerminada: Boolean      // Controlado por el Checkbox
)