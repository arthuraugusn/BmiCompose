package com.example.bmicompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.example.bmicompose.utils.calculateBmi

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
        BmiCalculate()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BmiCalculate(){
    //Header
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

    //Main
    var numberWeight by rememberSaveable { mutableStateOf("") }
    var numberHigh by rememberSaveable { mutableStateOf("") }
    var bmi by rememberSaveable { mutableStateOf(0.00) }
    var expandState by remember {
        mutableStateOf(false)
    }

    var isWeightError by remember{
        mutableStateOf(false)
    }

    var isHighError by remember{
        mutableStateOf(false)
    }

    //Objeto que controla a requisição de foco
    val weightFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Column {
            Text(
                text = stringResource(id = R.string.weight),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            OutlinedTextField(
                value = numberWeight,
                onValueChange = {newWeight->
                    Log.i("xxx", newWeight)
                    var lastChar =
                        if(newWeight.length == 0)
                            newWeight
                        else
                            newWeight.get(newWeight.length - 1)
                    var newValue =
                        if (lastChar == '.' || lastChar == ',')
                            newWeight.dropLast(1)
                        else
                            newWeight
                    Log.i("xxx", lastChar.toString())
                    Log.i("xxx", newValue)
                    numberWeight = newValue
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .focusRequester(weightFocusRequester),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Backup, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = "")
                },
                isError = isWeightError,
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
                onValueChange = {newHeight->
                    Log.i("xxx", newHeight)
                    var lastChar =
                        if(newHeight.length == 0)
                            newHeight
                        else
                            newHeight.get(newHeight.length - 1)
                    var newValue =
                        if (lastChar == '.' || lastChar == ',')
                            newHeight.dropLast(1)
                        else
                            newHeight
                    Log.i("xxx", lastChar.toString())
                    Log.i("xxx", newValue)
                    numberHigh = newValue
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Backup, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = "")
                },
                isError = isHighError,
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
                if (numberWeight != "" || numberHigh!=""  || numberWeight.length != 0 || numberHigh.length!=0 || numberHigh.toDouble() >300 || numberWeight.toDouble() > 300){
                    bmi = calculateBmi(numberWeight.toDouble(), numberHigh.toDouble())
                    expandState = true
                    isWeightError = false
                    isHighError = false
                }else{
                    isWeightError = true
                    isHighError = true
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.button_text),
                color = Color.White
            )
        }
    }

    //Footer
    AnimatedVisibility(
        visible = expandState,
        enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
        exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
    ) {
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
                    text = String.format("%.2f", bmi),
                    modifier = Modifier.padding(bottom = 30.dp),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Parabens, voce esta no seu peso ideal,felicidade",
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
                Row {

                    Button(
                        onClick = {
                            expandState = false
                            numberWeight = ""
                            numberHigh = ""
                            weightFocusRequester.requestFocus()
                        },
                    ) {
                        Text(text = stringResource(id = R.string.reset))
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                    Button(
                        onClick = {

                        },
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