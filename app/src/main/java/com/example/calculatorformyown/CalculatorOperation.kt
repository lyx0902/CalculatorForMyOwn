package com.example.calculatorformyown

import android.icu.text.SymbolTable

sealed class CalculatorOperation(val symbol:String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("*")
    object Divide : CalculatorOperation("/")
}
