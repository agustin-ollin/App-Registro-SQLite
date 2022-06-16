package com.example.app_registro.database

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.example.app_registro.models.Asignatura
import com.example.app_registro.models.Bitacora
import com.example.app_registro.models.Estudiante
import com.example.app_registro.models.SalaComputo

/**
 * Función para insertar datos en la tabla Asignatura
 */
fun setAsignaturaDB(asignatura: Asignatura, context: Context){
    val admin = AdminSqliteOpenHelper(context,"asignatura", null, 1)
    val bd = admin.writableDatabase
    val registro = ContentValues()
    registro.put("clave", asignatura.claveAsignatura)
    registro.put("nombre", asignatura.nombreMateria)
    registro.put("dia_clase", asignatura.diaClase)
    registro.put("hora_entrada", asignatura.horaEntrada)
    registro.put("hora_salida", asignatura.horaSalida)

    bd.insert("asignatura", null, registro)
    bd.close()

    lanzToast("asignatura", context)
}

/**
 * Función para insertar datos en la tabla Bitácora
 */
fun setBitacoraDB(bitacora: Bitacora, context: Context){
    val admin = AdminSqliteOpenHelper(context,"bitacora", null, 1)
    val bd = admin.writableDatabase
    val registro = ContentValues()
    registro.put("fecha_registro", bitacora.fechaRegistro)
    registro.put("numero_control", bitacora.claveMateria)
    registro.put("clave_materia", bitacora.numControl)

    bd.insert("bitacora", null, registro)
    bd.close()

    lanzToast("bitacora", context)
}

/**
 * Función para insertar datos en la tabla Estudiante
 */
fun setEstudianteDB(estudiante: Estudiante, context: Context){
    val admin = AdminSqliteOpenHelper(context,"estudiante", null, 1)
    val bd = admin.writableDatabase
    val registro = ContentValues()
    registro.put("numero_control", estudiante.numControl)
    registro.put("nombre", estudiante.nombre)
    registro.put("carrera", estudiante.carrera)
    registro.put("semestre", estudiante.semestre)
    registro.put("grupo", estudiante.grupo)

    bd.insert("estudiante", null, registro)
    bd.close()

    lanzToast("estudiante", context)
}

/**
 * Función para insertar datos en la tabla SalaComputo
 */
fun setSalaComputoDB(salaComputo: SalaComputo, context: Context){
    val admin = AdminSqliteOpenHelper(context,"sala_computo", null, 1)
    val bd = admin.writableDatabase
    val registro = ContentValues()
    registro.put("clave", salaComputo.clave)
    registro.put("nombre", salaComputo.nombreSala)

    bd.insert("sala_computo", null, registro)
    bd.close()

    lanzToast("sala_computo", context)
}

/**
 * Función para lanzar mensaje después de realizar un registro
 */
fun lanzToast(n: String, context: Context){
    Toast.makeText(context, "Se cargaron los datos en la tabla: $n", Toast.LENGTH_SHORT).show()
}