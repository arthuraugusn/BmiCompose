package com.example.bmicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                ) {
                Greeting("Lídia")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var textoState = remember { mutableStateOf("") }
    var texto2 = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.Yellow)
    )
    {
        for(x in 1 .. 10){
            Text(
                text = "Hello $name - $x"
            )
        }

        Row(
            modifier = Modifier
                .background(color = Color.Magenta)
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            for (x in 1 .. 3){
                Button(
                    onClick = { },
                    modifier = Modifier
                                .size(80.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.Gray
                    ),
                    colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                    ),
                    )
                {
                    Text(
                        text = "Botão $x",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(

                modifier = Modifier.padding(20.dp)

            ) {
                OutlinedTextField(
                    value = textoState.value,
                    onValueChange = {
                        textoState.value = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = texto2.value,
                    onValueChange = {
                        texto2.value = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        textoState.value =  ""
                        texto2.value = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.2f),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.limpar), contentDescription = "Limpar")
                        Text(text = "Limpar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiComposeTheme {
        Greeting("Android")
    }
}