package com.example.calculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.Evaluator.Evaluator

class CalculatorViewModel: ViewModel() {
    private val _result = MutableLiveData(0.0)
    private val _input = mutableStateOf("")
    private val evaluate = Evaluator()
    val result: LiveData<Double>
        get() = _result

    val input: MutableState<String>
        get() = _input


    fun updateResult() {
        _result.value = evaluate.getResult(_input.value)
    }

    //used for number buttons
    fun concatNumber(number: String) {
        _input.value = input.value + number
    }

    //used for operation buttons
    fun concatOperation(operator: String) {
        _input.value = input.value + " " + operator + " "
    }

    fun clearInput() {
        _input.value = ""
    }

    fun deleteSingleInput() {
        _input.value = input.value.dropLast(1)
    }

    fun returnResult(): String {
        if (result.value == 0.0)
            return ""
        return result.value.toString()
    }
}
