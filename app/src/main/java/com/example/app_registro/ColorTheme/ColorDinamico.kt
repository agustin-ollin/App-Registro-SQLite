package com.example.app_registro.ColorTheme

import android.app.Application
import com.google.android.material.color.DynamicColors

/**
 * Clase para asignar color Dinámico a la aplicación
 */
class ColorDinamico: Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}