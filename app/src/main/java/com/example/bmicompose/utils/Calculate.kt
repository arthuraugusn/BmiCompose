package com.example.bmicompose.utils

import kotlin.math.pow

fun calculateBmi(weight:Double, height: Double):Double {
    var bmi = weight/(height/100).pow(2)
    return bmi
}