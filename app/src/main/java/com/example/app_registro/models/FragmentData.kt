package com.example.app_registro.models

/**
 * Interface para Fragments a implementar
 */
interface FragmentData {
    fun returnData(): String
    fun checkData(): Boolean
    fun cleanData()
}