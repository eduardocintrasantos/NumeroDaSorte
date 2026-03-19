package com.example.numerodasorte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numerodasorte.ui.theme.Green
import com.example.numerodasorte.ui.theme.NumeroDaSorteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumeroDaSorteTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") { HomeScreen(navController) }
                    composable("form") { FormScreen() }
                }
            }
        }
    }
}

@Composable
fun HomeScreen( navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LotteryItem("Mega Sena", navController)
    }
}

@Composable
fun LotteryItem(
    name: String,
    navController: NavController
) {
    Card(
        onClick = { navController.navigate("form") },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(Green)
        ) {
            Image(
                painter = painterResource(R.drawable.trevo),
                contentDescription = "Imagem de um trevo de quatro folhas",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
            Text(
                text = name,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun FormScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val qtdNumbers = remember { mutableStateOf("") }
        val qtdBets = remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.trevo),
                contentDescription = "Imagem de um trevo de quatro folhas",
                modifier = Modifier
                    .size(150.dp)
            )
            Text(
                text = "Mega Sena",
                style = TextStyle(
                    color = Green,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            OutlinedTextField(
                value = qtdNumbers.value,
                maxLines = 1,
                label = {
                        Text(stringResource(id = R.string.mega_rule))
                },
                placeholder = {
                    Text(stringResource(id = R.string.quantity))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),

                onValueChange = {  },
            )

            OutlinedTextField(
                value = qtdBets.value,
                maxLines = 1,
                label = {
                    Text(stringResource(id = R.string.bets))
                },
                placeholder = {
                    Text(stringResource(id = R.string.bets_quantity))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),

                onValueChange = {  },
            )

            OutlinedButton(
                onClick = {},
            ) {
                Text(stringResource(id = R.string.bets_generate))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NumeroDaSorteTheme {
        HomeScreen( rememberNavController() )
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    NumeroDaSorteTheme {
        FormScreen()
    }
}