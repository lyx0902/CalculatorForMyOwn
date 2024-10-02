package com.example.calculatorformyown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    modifier: Modifier, // This is a Modifier object that is used to modify the button
    symbol:String, // This is a string that represents the symbol on the button
    onClick: () -> Unit // This is a lambda function that takes no arguments and returns Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =Modifier
        .clip(CircleShape) //修剪为圆形
        .clickable{onClick()} //设置点击事件
        //由于我们的按钮是圆形的，所以我们需要设置宽度和高度相等
        //使用then将这个修饰符与另一个修饰符连接起来
        .then(modifier)
    ) {
        Text(text=symbol, fontSize = 36.sp,color= Color.White)
    }
}