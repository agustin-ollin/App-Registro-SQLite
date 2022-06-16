package com.example.app_registro.database

import android.content.Context
import android.widget.Toast

/**
 * Función para obtener los valores de la tabla Asignatura
 */
fun getAsignaturaDB(context: Context): String {
    var contenido: String = ""

    val admin = AdminSqliteOpenHelper(context, "asignatura", null, 1)
    val bd = admin.writableDatabase
    val fila =
        bd.rawQuery("select clave,nombre,dia_clase,hora_entrada,hora_salida from asignatura", null)
    while (fila.moveToNext()) {
        contenido += "Clave: ${fila.getString(0)} \nNombre: ${fila.getString(1)} \nDía de clase: ${
            fila.getString(
                2
            )
        } \nHora Entrada: ${fila.getString(3)} \nHora Salida: ${fila.getString(4)} \n\n"
    }

    bd.close()

    return contenido
}

/**
 * Función para obtener los valores de la tabla Bitacora
 */
fun getBitacoraDB(context: Context): String {
    var contenido: String = ""

    val admin = AdminSqliteOpenHelper(context, "bitacora", null, 1)
    val bd = admin.writableDatabase
    val fila = bd.rawQuery("select fecha_registro,numero_control,clave_materia from bitacora", null)
    while (fila.moveToNext()) {
        contenido += "Fecha de Registro: ${fila.getString(0)} \nNum. Control: ${fila.getString(1)} \nClave de la Materia: ${
            fila.getString(
                2
            )
        } \n\n"
    }

    bd.close()

    return contenido
}

/**
 * Función para obtener los valores de la tabla Estudiante
 */
fun getEstudianteDB(context: Context): String {
    var contenido: String = ""

    val admin = AdminSqliteOpenHelper(context, "estudiante", null, 1)
    val bd = admin.writableDatabase
    val fila =
        bd.rawQuery("select numero_control,nombre,carrera,semestre,grupo from estudiante", null)
    while (fila.moveToNext()) {
        contenido += "Num. Control: ${fila.getString(0)} \nNombre: ${fila.getString(1)} \nCarrera: ${
            fila.getString(
                2
            )
        } \nSemestre: ${fila.getString(3)} \nGrupo: ${fila.getString(4)} \n\n"
    }

    bd.close()

    return contenido
}

/**
 * Función para obtener los valores de la tabla SalaComputo
 */
fun getSalaComputoDB(context: Context): String {
    var contenido: String = ""

    val admin = AdminSqliteOpenHelper(context, "sala_computo", null, 1)
    val bd = admin.writableDatabase
    val fila = bd.rawQuery("select clave,nombre from sala_computo", null)
    while (fila.moveToNext()) {
        contenido += "Clave: ${fila.getString(0)} \nNombre de la Sala: ${fila.getString(1)} \n\n"
    }

    bd.close()

    return contenido
}