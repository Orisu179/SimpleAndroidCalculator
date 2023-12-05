package com.example.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
//    var result = mutableStateOf(0.0)
//        private set
    private val _result = MutableLiveData(0.0)
    val result: LiveData<Double>
        get() = _result

    fun updateResult() {

    }

    fun returnResult(): String {
        if (result.value == 0.0)
            return ""
        return result.value.toString()
    }
}

//This is what is saved in the local database
data class Calculations(val input: String, val result: Double)