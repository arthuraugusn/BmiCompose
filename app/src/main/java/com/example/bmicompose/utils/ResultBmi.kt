package com.example.bmicompose.utils

import androidx.compose.ui.graphics.Color
import com.example.bmicompose.R

fun resultBmi(bmi:Double):String{
    var textResult = ""

    if(bmi <16.9){
        textResult = R.string.bmiStatus0.toString()
    }else if(bmi>16.9 && bmi<25){
        textResult = R.string.bmiStatus1.toString()
    }else if(bmi >24.9 && bmi <30){
        textResult = R.string.bmiStatus2.toString()
    }else if(bmi >29.9 && bmi<35){
        textResult = R.string.bmiStatus3.toString()
    }else if(bmi >34.9 && bmi<40){
        textResult = R.string.bmiStatus4.toString()
    }else if(bmi > 39.9){
        textResult = R.string.bmiStatus5.toString()
    }
    return textResult
}

fun getColor(bmi:Double): Color {
    if(bmi <=18.5){
        return Color.Red
    }else if(bmi>18.5 && bmi <25){
        return Color.Green
    }else if(bmi >25 && bmi <30){
        return Color(255, 152, 0, 255)
    }else{
        return Color.Red
    }
}