package com.example.numerodasorte

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numerodasorte.ui.theme.NumeroDaSorteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumeroDaSorteTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val context = LocalContext.current
    val result = remember {
        mutableStateOf("Resultado aparece aqui!")
    }
    val textFieldValue = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background)
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Boa sorte!",
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    color = Color(0xFF50C878),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    keyboardOptions = KeyboardOptions(
                      keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = textFieldValue.value,
                    label = {
                        Text("Digite um numero entre 6 e 15")
                    },
                    onValueChange = {
                        textFieldValue.value = validateInput(it)
                    }
                )

                Text(
                    result.value,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Button(onClick = {
                val numberIsValid = validateTextField(textFieldValue.value)

                if (!numberIsValid){
                    Toast.makeText(
                        context,
                        "Digite um numero entre 6 e 16",
                        Toast.LENGTH_LONG
                    ).show()
                    return@Button
                }
                val res = numbersGenerator(context,textFieldValue.value.toInt())
                result.value = res
            }) {
                Text("Gerar Numeros")
            }
        }
    }
}

fun validateInput(input: String):String {
    val filteredChars = input.filter {
        it in "0123456789"
    }
    return filteredChars
}

fun validateTextField(text:String): Boolean {
    if (text.isEmpty()) {
        return false
    }

    val qtd = text.toInt()
    if (qtd < 6 || qtd > 15) {
        return false
    }

    return true
}

fun numbersGenerator(context: Context, qtd: Int): String {
    var result = ""

    if (qtd >= 6 && qtd <= 16) {
        val numbers = mutableSetOf<Int>()

        while(true) {
            val n = java.util.Random().nextInt(60)
            numbers.add(n+1)

            if (numbers.size == qtd) {
                break
            }
        }

        result = numbers.joinToString (" - ")
    } else {
        Toast.makeText(
            context,
            "Digite um numero entre 6 e 16",
            Toast.LENGTH_LONG
        ).show()
    }

    return result
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NumeroDaSorteTheme {
        MainApp()
    }
}