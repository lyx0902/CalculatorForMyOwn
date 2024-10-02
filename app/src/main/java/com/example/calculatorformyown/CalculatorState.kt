package com.example.calculatorformyown

data class CalculatorState(
    val leftNumber:String ="",//左边的数字
    val rightNumber:String ="",//右边的数字
    val operation:CalculatorOperation? = null,//操作符
    )