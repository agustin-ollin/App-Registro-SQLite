package com.example.app_registro.models

/**
 * DataClass para serialización de registro de Estudiantes
 */
data class Estudiante(
    val numControl: String,
    val nombre: String,
    val carrera: String,
    val semestre: String,
    val grupo: String
)
