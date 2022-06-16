package com.example.app_registro

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.app_registro.database.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.jar.Manifest

/**
 * MainActivity
 */
class MainActivity : AppCompatActivity() {
    // Declaración de variables y componentes
    var indice: Int = 0
    val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

    lateinit var bottomNavigation: BottomNavigationView
    lateinit var bottomAgregar: FloatingActionButton
    lateinit var bottomConsultar: FloatingActionButton

    val homeFragment: HomeFragment = HomeFragment()
    val bitacoraFragment: BitacoraFragment = BitacoraFragment()
    val estudianteFragment: EstudianteFragment = EstudianteFragment()
    val asignaturaFragment: AsignaturaFragment = AsignaturaFragment()
    val salaComputoFragment: SalaComputoFragment = SalaComputoFragment()

    val REQUEST_CODE: Int = 200

    /**
     * Función onCreate
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verificarPermisos()

        ssupportFragmentManager = supportFragmentManager

        inicializarComponents()
        bottomNavigation = findViewById(R.id.bottom_navigation)
        lanzarFragments()
        eventsFloatingBottom()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun verificarPermisos() {
        val permisoEscribir: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permisoLeer: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(permisoEscribir == PackageManager.PERMISSION_GRANTED && permisoLeer == PackageManager.PERMISSION_GRANTED){
            println("Permisos de lectura y escritura concedidos")
        } else {
            requestPermissions(listOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray(), REQUEST_CODE)
        }
    }

    /**
     * Función para instanciar los componentes de la interfaz
     */
    fun inicializarComponents() {
        bottomAgregar = findViewById(R.id.buttonAgregar)
        bottomConsultar = findViewById(R.id.buttonConsultar)
    }

    /**
     * Función para asignar eventos a los botones flotantes
     */
    fun eventsFloatingBottom() {
        // Evento para consultar datos
        bottomConsultar.setOnClickListener {
            selectFileRead()
        }

        // Evento para agregar registro
        bottomAgregar.setOnClickListener {
            selectFileWrite()
        }
    }

    /**
     * Función para elegir y asignar el archivo en el que se escribirán los datos
     */
    fun selectFileWrite() {
        when (indice) {
            2 -> {
                if (bitacoraFragment.checkData()) {
                    sendToastError()
                } else {
                    //escribirArchivo("bitacora", bitacoraFragment.returnData())
                    setBitacoraDB(bitacoraFragment.returnDato(), this)
                    bitacoraFragment.cleanData()
                }
            }
            3 -> {
                if (estudianteFragment.checkData()) {
                    sendToastError()
                } else {
                    //escribirArchivo("estudiante", estudianteFragment.returnData())
                    setEstudianteDB(estudianteFragment.returnDato(), this)
                    estudianteFragment.cleanData()
                }
            }
            4 -> {
                if (asignaturaFragment.checkData()) {
                    sendToastError()
                } else {
                    //escribirArchivo("asignatura", asignaturaFragment.returnData())
                    setAsignaturaDB(asignaturaFragment.returnDato(), this)
                    asignaturaFragment.cleanData()
                }
            }
            5 -> {
                if (salaComputoFragment.checkData()) {
                    sendToastError()
                } else {
                    //escribirArchivo("sala_computo", salaComputoFragment.returnData())
                    setSalaComputoDB(salaComputoFragment.returnDato(), this)
                    salaComputoFragment.cleanData()
                }
            }
            else -> {
                println("No se registró en la base de datos")
            }
        }
    }

    /**
     * Función para mostrar mensaje de error
     */
    fun sendToastError() {
        Toast.makeText(this, "Ingresa todos los datos", Toast.LENGTH_SHORT).show()
    }

    /**
     * Función para seleccionar la tabla de la que se obtendrán los registros para mostrar en pantalla
     */
    fun selectFileRead() {
        var mns: String = ""
        when (indice) {
            2 -> {
                mns = getBitacoraDB(this)
                lanzarConsulta(mns, "Registro de Bitácora")
            }
            3 -> {
                mns = getEstudianteDB(this)
                lanzarConsulta(mns, "Registro de Estudiantes")
            }
            4 -> {
                mns = getAsignaturaDB(this)
                lanzarConsulta(mns, "Registro de Asignaturas")
            }
            5 -> {
                mns = getSalaComputoDB(this)
                lanzarConsulta(mns, "Registro de la Sala de Cómputo")
            }
            else -> {
                println("No se escribió en archivos")
            }
        }
    }

    /**
     * Función para leer un archivo txt y retornar contenido en un String
     */
    fun leerArchivo(nombre: String): String {
        var contenido: String = ""
        val pathName: String = ruta + "/" + nombre
        val archivo = File(pathName)

        if (!archivo.exists()) {
            contenido = "No se han realizado Registros!"
        } else {
            contenido = archivo.readText()
        }

        return contenido
    }

    /**
     * Función para mostrar datos de archivos en un AlertDialog
     */
    fun lanzarConsulta(mensaje: String, titulo: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(titulo)
            .setMessage(mensaje).setNegativeButton("Cerrar") { dialog, which ->
                // Respond to negative button press
            }.show()
    }

    /**
     * Función para escribir una cadena de texto en un archivo
     */
    fun escribirArchivo(nombre: String, contenido: String) {
        val nombreArchivo = "$nombre.txt"
        val archivo = File(ruta + "/" + nombreArchivo)

        if (!archivo.exists()) {
            archivo.createNewFile()
            println(archivo.path)
        }

        val fileWrite: FileWriter = FileWriter(archivo, true)

        val escribir: BufferedWriter = BufferedWriter(fileWrite)
        escribir.write("------------------------------------------------\n$contenido")

        escribir.close()
        fileWrite.close()
        Toast.makeText(this, "Se insertaron datos correctamente", Toast.LENGTH_SHORT).show()
    }

    /**
     * Función para ejecutar Fragments
     */
    fun lanzarFragments(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()
        ocultarBotones()
        bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.inicio -> {
                    indice = 1
                    supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment)
                        .commit()
                    ocultarBotones()
                    return@OnItemSelectedListener true
                }
                R.id.bitacora -> {
                    indice = 2
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, bitacoraFragment).commit()
                    mostrarBotones()
                    return@OnItemSelectedListener true
                }
                R.id.estudiante -> {
                    indice = 3
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, estudianteFragment).commit()
                    mostrarBotones()
                    return@OnItemSelectedListener true
                }
                R.id.asignatura -> {
                    indice = 4
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, asignaturaFragment).commit()
                    mostrarBotones()
                    return@OnItemSelectedListener true
                }
                R.id.sala_computo -> {
                    indice = 5
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, salaComputoFragment).commit()
                    mostrarBotones()
                    return@OnItemSelectedListener true
                }
                else -> {
                    return@OnItemSelectedListener false
                }
            }
        })
        return true
    }

    /**
     * Función para ocultar botones de agregar y consultar
     */
    fun ocultarBotones() {
        bottomAgregar.isVisible = false
        bottomConsultar.isVisible = false
    }

    /**
     * Función para mostrar botones de agregar y consultar
     */
    fun mostrarBotones() {
        bottomAgregar.isVisible = true
        bottomConsultar.isVisible = true
    }

    /**
     * Companion objects a utilizar en diferentes clases
     */
    companion object {
        lateinit var ssupportFragmentManager: FragmentManager
    }
}