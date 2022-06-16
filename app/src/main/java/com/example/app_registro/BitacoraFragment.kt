package com.example.app_registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.app_registro.DateFormBit.DatePickerFragment
import com.example.app_registro.models.Bitacora
import com.example.app_registro.models.FragmentData

/**
 * Fragment para el registro de la Bitácora
 */
class BitacoraFragment : Fragment(), FragmentData {
    // Inicialización de variables
    lateinit var fecha: EditText
    lateinit var numControl: EditText
    lateinit var claveMateria: EditText

    /**
     * Función onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_bitacora, container, false)
        launchDate(root)
        // Inflate the layout for this fragment
        return root
    }

    /**
     * Función para instanciar componentes
     */
    fun launchDate(view: View) {
        fecha = view.findViewById(R.id.editFechaRegistro)
        numControl = view.findViewById(R.id.editNumControlBitacora)
        claveMateria = view.findViewById(R.id.editClaveMateriaBitacora)

        fecha.setOnClickListener {
            showDatePickerDialog()
        }
    }

    /**
     * Función para mostrar cuadro de fecha
     */
    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment({ day, month, year -> onDateSelected(day, month, year) })
        datePicker.show(MainActivity.ssupportFragmentManager, "datePicker")
    }

    /**
     * Función para asignar la fecha al EditText
     */
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val dia: String = if (day < 10) "0$day" else "$day"
        val mes: String = if (month < 10) "0$month" else "$month"

        fecha.setText("$dia/$mes/$year")
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    override fun returnData(): String {
        val bitacora: Bitacora = Bitacora(
            fecha.text.toString(),
            numControl.text.toString(),
            claveMateria.text.toString()
        )

        return "Fecha de Registro: ${bitacora.fechaRegistro} \nNum. Control: ${bitacora.numControl} \nClave de la Materia: ${bitacora.claveMateria} \n"
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    fun returnDato(): Bitacora = Bitacora(
            fecha.text.toString(),
            numControl.text.toString(),
            claveMateria.text.toString()
        )

    /**
     * Función sobreescrita para comprobar si hay campos vacíos
     */
    override fun checkData(): Boolean =
        fecha.text.toString().isEmpty() || numControl.text.toString()
            .isEmpty() || claveMateria.text.toString().isEmpty()

    /**
     * Función para limpiar los EditText
     */
    override fun cleanData() {
        fecha.setText("")
        numControl.setText("")
        claveMateria.setText("")
    }
}