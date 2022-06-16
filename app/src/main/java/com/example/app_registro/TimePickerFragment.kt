package com.example.app_registro

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * Clase para generar el cuadro de hora
 */
class TimePickerFragment(val listener: (String) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val hora = calendar.get(Calendar.HOUR_OF_DAY)
        val minutos = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, this, hora, minutos, true)
        return dialog
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val hora: String = if(p1 < 10) "0$p1" else "$p1"
        val minutos: String = if(p2 < 10) "0$p2" else "$p2"

        listener("$hora:$minutos")
    }
}