package com.example.calculator.Evaluator

import kotlin.NumberFormatException

/*
This class uses the Shunting Yard Algorithm to evaluate the result
 */
class Evaluator: Evaluate {
    override fun getResult(input: String): Double {
        val resultQueue = shuntingYard(input)
        val calculationStack = ArrayDeque<Double>()
        for (token in resultQueue) {
            val temp = token.toDoubleOrNull()
            if (temp != null){
              calculationStack.addFirst(temp)
            } else {
                val number1 = calculationStack.removeFirst()
                val number2 = calculationStack.removeFirst()
                var result = 0.0
                when (token) {
                    "+" -> result = number1 + number2
                    "-" -> result = number1 - number2
                    "*" -> result = number1 * number2
                    "/" -> result = number1 / number2
                }
                resultQueue.addFirst(result.toString())
            }
        }
        return resultQueue.first().toDouble()
    }

    override fun shuntingYard(expression: String): ArrayDeque<String> {
        val operatorMap = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2)
        val operatorStack = ArrayDeque<String>()
        val outputQueue = ArrayDeque<String>()
        val expressionArray = expression.split(" ")
        for (value in expressionArray){
            when (value) {
                in operatorMap -> {
                    if (operatorStack.isNotEmpty() && operatorMap[operatorStack.first()]!! >= operatorMap[value]!!){
                        outputQueue.addFirst(operatorStack.removeFirst())
                    }
                    operatorStack.addFirst(value)
                }
                "(" -> operatorStack.addFirst(value)
                ")" -> {
                    while(operatorStack.first() != "(") {
                        outputQueue.addFirst(operatorStack.removeFirst())
                    }
                    operatorStack.removeFirst() // removes the "("
                }
                else -> outputQueue.addFirst(value) //the case where value is a number
            }
        }
        while (operatorStack.isNotEmpty()) {
            outputQueue.addFirst(operatorStack.removeFirst())
        }
        return outputQueue
    }
}