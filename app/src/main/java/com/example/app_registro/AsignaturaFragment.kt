package com.example.app_registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.app_registro.models.Asignatura
import com.example.app_registro.models.FragmentData
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment para el registro de Asignaturas
 */
class AsignaturaFragment : Fragment(), FragmentData {
    // Inicialización de variables
    lateinit var textInputLayout: TextInputLayout
    lateinit var autoCompleteTextViewDia: AutoCompleteTextView
    lateinit var horaEntrada: EditText
    lateinit var horaSalida: EditText
    lateinit var clave: EditText
    lateinit var nombre: EditText

    lateinit var root: View

    /**
     * Función onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_asignatura, container, false)
        inicializarComponente(root)
        timersFunt()
        // Inflate the layout for this fragment
        return root
    }

    /**
     * Función para inicializar los elementos de las listas a implementar en la interfaz
     */
    fun inicializarComponente(view: View) {
        // Dropdown_Carreras
        textInputLayout = view.findViewById(R.id.text_input_layout_diaclases)
        autoCompleteTextViewDia = view.findViewById(R.id.autoCompleteTextViewDiaClase)

        val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

        val adapter = ArrayAdapter(view.context, R.layout.dropdown_item, dias)
        autoCompleteTextViewDia.setAdapter(adapter)

        horaEntrada = view.findViewById(R.id.editHoraInicio)
        horaSalida = view.findViewById(R.id.editHoraFinal)
        clave = view.findViewById(R.id.editClaveMateria)
        nombre = view.findViewById(R.id.editNombreMateria)
    }

    /**
     * Función para aplicar evento al dar click en los TextView de horaEntrada y horaSalida
     */
    fun timersFunt() {
        // Evento para hora de entrada
        horaEntrada.setOnClickListener {
            showTimePickerDialog(horaEntrada)
        }

        // Evento para hora de salida
        horaSalida.setOnClickListener {
            showTimePickerDialog(horaSalida)
        }
    }

    /**
     * Función para mostrar cuadro de selección de hora
     */
    private fun showTimePickerDialog(vi: EditText) {
        val timePicker = TimePickerFragment { onTimeSelected(it, vi) }

        timePicker.show(MainActivity.ssupportFragmentManager, "time")
    }

    /**
     * Función para asignar texto a EditText
     */
    private fun onTimeSelected(it: String, vi: EditText) {
        vi.setText("$it")
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    override fun returnData(): String {
        val asignatura: Asignatura = Asignatura(
            clave.text.toString(),
            nombre.text.toString(),
            autoCompleteTextViewDia.text.toString(),
            horaEntrada.text.toString(),
            horaSalida.text.toString()
        )
        return "Clave: ${asignatura.claveAsignatura} \nNombre: ${asignatura.nombreMateria} \nDía de clase: ${asignatura.diaClase} \nHora Entrada: ${asignatura.horaEntrada} \nHora Salida: ${asignatura.horaSalida} \n"
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    fun returnDato(): Asignatura = Asignatura(
            clave.text.toString(),
            nombre.text.toString(),
            autoCompleteTextViewDia.text.toString(),
            horaEntrada.text.toString(),
            horaSalida.text.toString()
    )

    /**
     * Función sobreescrita para comprobar si hay campos vacíos
     */
    override fun checkData(): Boolean = clave.text.toString().isEmpty() || nombre.text.toString()
        .isEmpty() || autoCompleteTextViewDia.text.toString()
        .isEmpty() || horaEntrada.text.toString().isEmpty() || horaSalida.text.toString().isEmpty()

    /**
     * Función para limpiar los EditText
     */
    override fun cleanData() {
        clave.setText("")
        nombre.setText("")
        autoCompleteTextViewDia.setText("")
        horaEntrada.setText("")
        horaSalida.setText("")
    }

}