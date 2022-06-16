package com.example.app_registro.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSqliteOpenHelper (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version){

    /**
     * Función sobreescrita para ejecutar comandos de creación de tablas
     */
    override fun onCreate(db: SQLiteDatabase?) {
        //db!!.execSQL("create table articulos (codigo int primary key, descripcion text, precio real)")
        db!!.execSQL("create table asignatura (clave int primary key, nombre text, dia_clase text, hora_entrada text, hora_salida text)")
        db!!.execSQL("create table bitacora (fecha_registro text, numero_control text, clave_materia text)")
        db!!.execSQL("create table estudiante (numero_control text primary key, nombre text, carrera text, semestre text, grupo text)")
        db!!.execSQL("create table sala_computo (clave text primary key, nombre text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}