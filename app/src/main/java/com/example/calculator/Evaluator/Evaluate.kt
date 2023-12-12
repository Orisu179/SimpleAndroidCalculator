package com.example.calculator.Evaluator

interface Evaluate {
   fun getResult(input: String) : Double
   fun shuntingYard(expression: String): ArrayDeque<String>
}