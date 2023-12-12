package com.example.calculator.Evaluator

import kotlin.NumberFormatException

/*
This class uses the Shunting Yard Algorithm to evaluate the result
 */
class Evaluator: Evaluate {
    override fun getResult(input: String): Double {
       val postVal = shuntingYard(input)
        return 0.0
    }

    override fun shuntingYard(expression: String): String {
        val operatorStack = ArrayDeque<String>()
        val numberQueue = ArrayDeque<Int>()
        val expressionArray = expression.split(" ")
        for (value in expressionArray){
            try {
                // If the string is a number, that means
                val temp = Integer.parseInt(value)
                numberQueue.addFirst(temp)
            } catch (e: NumberFormatException) {

            }
        }
        return ""
    }
}