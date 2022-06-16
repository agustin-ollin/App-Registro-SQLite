package com.example.app_registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.app_registro.models.FragmentData
import com.example.app_registro.models.SalaComputo

/**
 * Fragment para el registro de las salas de cómputo
 */
class SalaComputoFragment : Fragment(), FragmentData {
    // Declaración de variables y componentes
    lateinit var clave: EditText
    lateinit var nomSala: EditText

    /**
     * Función onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_sala_computo, container, false)
        inicializarComponentes(root)
        // Inflate the layout for this fragment
        return root
    }

    /**
     * Función para instanciar componentes
     */
    fun inicializarComponentes(view: View) {
        clave = view.findViewById(R.id.editClaveSalaComputo)
        nomSala = view.findViewById(R.id.editNombreSala)
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    override fun returnData(): String {
        val salaC: SalaComputo = SalaComputo(clave.text.toString(), nomSala.text.toString())

        return "Clave: ${salaC.clave} \nNombre de la Sala: ${salaC.nombreSala} \n"
    }

    /**
     * Función sobreescrita para retornar los valores de la DataClass para este Fragment
     */
    fun returnDato(): SalaComputo = SalaComputo(clave.text.toString(), nomSala.text.toString())

    /**
     * Función sobreescrita para comprobar si hay campos vacíos
     */
    override fun checkData(): Boolean =
        clave.text.toString().isEmpty() || nomSala.text.toString().isEmpty()

    /**
     * Función para limpiar los EditText
     */
    override fun cleanData() {
        clave.setText("")
        nomSala.setText("")
    }

}