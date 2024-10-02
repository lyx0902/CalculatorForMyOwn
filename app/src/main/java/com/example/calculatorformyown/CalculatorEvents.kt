package com.example.calculatorformyown

/**
 * 2024年10月2日15:57:05
 * 事件接口
 * 事件会导致viewmodel的变化
 * 使用密封类来实现
 * 归纳事件为四类:清除事件、删除事件、小数点事件、执行计算事件
 */
sealed class CalculatorEvents {
    data class Number(val number: Int) : CalculatorEvents()
    object Clear : CalculatorEvents()
    object Delete : CalculatorEvents()
    object Decimal : CalculatorEvents()
    object Calculator : CalculatorEvents()
    //符号数据类
    data class Operation(val operation: CalculatorOperation):CalculatorEvents()
}