package com.example.bmicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicompose.ui.theme.BmiComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    BmiCalculator()
                }
            }
        }
    }
}

@Composable
fun BmiCalculator(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderBmi()
        MainBmi()
    }
}

@Composable
fun HeaderBmi(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bmi),
            contentDescription = "",
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(id = R.string.app_title),
            modifier = Modifier.padding(bottom = 15.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 5.sp

        )
    }
}

@Composable
fun MainBmi(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var numberWeight by rememberSaveable { mutableStateOf("") }
        var numberHigh by rememberSaveable { mutableStateOf("") }
        var bmi by rememberSaveable { mutableStateOf(0.0) }


        Column {
            Text(
                text = stringResource(id = R.string.weight),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            OutlinedTextField(
                value = numberWeight,
                onValueChange = {
                    numberWeight = it
                },
                modifier = Modifier.padding(bottom = 10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(15.dp)
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.high),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            OutlinedTextField(
                value = numberHigh,
                onValueChange = {
                    numberHigh = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(15.dp)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp, vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(Color(
                red = 87,
                green = 197,
                blue = 27,
                alpha = 255
            )
            ),
            onClick = {
                bmi = calculateBmi(numberWeight.toDouble(), numberHigh.toDouble())
            }
        ) {
            Text(
                text = stringResource(id = R.string.button_text),
                color = Color.White
            )
        }
        FooterBmi(bmi = bmi, weight= numberWeight.toDouble(), height = numberHigh.toDouble())
    }
}

fun calculateBmi(weight:Double, height: Double):Double {
    val bmi = weight/(height*height)
    return bmi*100
}

@Composable
fun FooterBmi(bmi: Double, weight: Double, height: Double){
    Column {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.your_score),
                    modifier = Modifier.padding(bottom = 50.dp),
                    fontSize = 32.sp
                )
                Text(
                    text = bmi.toString(),
                    modifier = Modifier.padding(bottom = 30.dp),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Parabens, voce esta no seu peso ideal,felicidade",
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
                Row{

                    Button(
                        onClick = {
                            
                        },
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text(text = stringResource(id = R.string.reset))
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text(text = stringResource(id = R.string.share))
                    }
                    
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BmiCalculatorPreview() {
    BmiCalculator()
}