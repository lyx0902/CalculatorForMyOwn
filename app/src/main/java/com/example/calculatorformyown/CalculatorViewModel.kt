package com.example.calculatorformyown

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())//观察状态
    companion object{//伴生对象
        private const val MAX_NUM_LENGTH = 8
    }
    fun onEvents(events:CalculatorEvents){
        when(events){
            is CalculatorEvents.Number -> enterNumber(events.number)
            is CalculatorEvents.Operation -> enterOperation(events.operation)
            is CalculatorEvents.Delete -> performDeletion()
            is CalculatorEvents.Decimal ->enterDecimal()
            is CalculatorEvents.Calculator -> performCalculation()
            is CalculatorEvents.Clear -> state=CalculatorState()//清除
        }
    }
    private fun performCalculation(){
        val number1=state.leftNumber.toDoubleOrNull()
        val number2=state.rightNumber.toDoubleOrNull()
        if(number1!=null&&number2!=null){
            val result = when(state.operation){
                CalculatorOperation.Add ->"${number1+number2}"
                CalculatorOperation.Subtract ->"${number1-number2}"
                CalculatorOperation.Multiply ->"${number1*number2}"
                CalculatorOperation.Divide ->"${number1/number2}"
                null ->return
            }
            //去除小数点后面的0
            //droplastwhile从后往前删除，直到不满足条件
            val result2="$result".dropLastWhile { it=='0' }.dropLastWhile { it=='.' }
            state=state.copy(
                leftNumber = result.take(15),//截取15位
                rightNumber = "",
                operation = null
            )
        }
    }
    private fun enterDecimal(){
        //如果操作符为空，那么就是左边的数字
        if(state.operation == null
            && !state.leftNumber.contains(".")
            && state.leftNumber.isNotEmpty()){
            state=state.copy(leftNumber = "${state.leftNumber}.")
            return
        }
        if(!state.rightNumber.contains(".")
            && state.rightNumber.isNotEmpty()
            ){
            state=state.copy(rightNumber = "${state.rightNumber}.")
            return
        }
    }
    private fun performDeletion(){
        when{
            //123+456
            //当右边的数字不为空时，删除右边的数字最后一位数
            state.rightNumber.isNotEmpty() -> state = state.copy(
                rightNumber = state.rightNumber.dropLast(1)
                //删除最后一个字符
            )
            //123+
            //当操作符不为空时，删除操作符
            state.operation!=null->state=state.copy(
                operation = null
            )
            //123
            //当左边的数字不为空时，删除左边的数字最后一位数
            state.leftNumber.isNotEmpty()->state=state.copy(
                leftNumber = state.leftNumber.dropLast(1)
            )

        }
    }
    private fun enterOperation(operation:CalculatorOperation){
        //如果左边的数字为空，那么就不添加操作符
        if(state.leftNumber.isEmpty()){
            return
        }
        //如果右边的数字不为空，那么就执行计算
        if(state.rightNumber.isNotEmpty()){
            performCalculation()
        }
        state = state.copy(operation = operation)
    }
    private fun enterNumber(number:Int){
        //如果操作符为空，那么就是左边的数字
        if(state.operation == null){
            //如果长度超过8位，那么就不再添加
            if(state.leftNumber.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(leftNumber = "${state.leftNumber}$number")
            return
        }
        else{
            //这是右边的数字
            if(state.rightNumber.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(rightNumber = "${state.rightNumber}$number")
        }
    }
}