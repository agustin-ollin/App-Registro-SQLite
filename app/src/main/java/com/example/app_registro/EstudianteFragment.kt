package com.example.app_registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.app_registro.models.Estudiante
import com.example.app_registro.models.FragmentData
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment para el registro de estudiantes
 */
class EstudianteFragment : Fragment(), FragmentData {
    // Inicialización de variables
    lateinit var textInputLayout: TextInputLayout
    lateinit var autoCompleteTextViewCarrera: AutoCompleteTextView
    lateinit var textInputLayoutSemestre: TextInputLayout
    lateinit var autoCompleteTextViewSemestre: AutoCompleteTextView

    lateinit var nc: EditText
    lateinit var nombre: EditText
    lateinit var grupoA: RadioButton
    lateinit var grupoB: RadioButton

    var semestre: String = ""
    var carrera: String = ""

    /**
     * Función onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_estudiante, container, false)

        inicializarComponente(root)
        // Inflate the layout for this fragment
        return root
    }

    /**
     * Función para inicializar los elementos de las listas a implementar en la interfaz
     */
    fun inicializarComponente(view: View) {
        // Dropdown_Carreras
        textInputLayout = view.findViewById(R.id.text_input_layout)
        autoCompleteTextViewCarrera = view.findViewById(R.id.autoCompleteTextViewCarrera)

        val carreras = listOf(
            "Ing. Informática",
            "Ing. en TIC",
            "Lic. en Biología",
            "Ing. en Agronomía",
            "Ing. Forestal"
        )

        val adapter = ArrayAdapter(view.context, R.layout.dropdown_item, carreras)
        autoCompleteTextViewCarrera.setAdapter(adapter)

        // Dropdown_Semestres
        textInputLayoutSemestre = view.findViewById(R.id.text_input_layout_semestre)
        autoCompleteTextViewSemestre = view.findViewById(R.id.autoCompleteTextViewSemestre)

        val semestres = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val adapterSemestres = ArrayAdapter(view.context, R.layout.dropdown_item, semestres)
        autoCompleteTextViewSemestre.setAdapter(adapterSemestres)

        eventListDropDown()

        nc = view.findViewById(R.id.editNumControl)
        nombre = view.findViewById(R.id.editNombre)
        grupoA = view.findViewById(R.id.radioA)
        grupoB = view.findViewById(R.id.radioB)
    }

    /**
     * Función para obtener los valoes de los ListDropDown
     */
    fun eventListDropDown() {
        autoCompleteTextViewSemestre.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                semestre = adapterView.getItemAtPosition(i).toString()
            }

        autoCompleteTextViewCarrera.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                carrera = adapterView.getItemAtPosition(i).toString()
            }
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    override fun returnData(): String {
        val estudiante: Estudiante = Estudiante(
            nc.text.toString(),
            nombre.text.toString(),
            carrera,
            semestre,
            (if (grupoA.isChecked) "A" else "B")
        )

        return "Num. Control: ${estudiante.numControl} \nNombre: ${estudiante.nombre} \nCarrera: $carrera \nSemestre: $semestre \nGrupo: ${estudiante.grupo} \n"
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    fun returnDato(): Estudiante  = Estudiante(
            nc.text.toString(),
            nombre.text.toString(),
            carrera,
            semestre,
            (if (grupoA.isChecked) "A" else "B")
        )

    /**
     * Función sobreescrita para comprobar si hay campos vacíos
     */
    override fun checkData(): Boolean = nc.text.toString().isEmpty() || nombre.text.toString()
        .isEmpty() || carrera.isEmpty() || semestre.isEmpty() || (!grupoA.isChecked && !grupoB.isChecked)

    /**
     * Función para limpiar los EditText
     */
    override fun cleanData() {
        nc.setText("")
        nombre.setText("")
        autoCompleteTextViewCarrera.setText("")
        autoCompleteTextViewSemestre.setText("")
        grupoA.isChecked = false
        grupoB.isChecked = false
    }

}