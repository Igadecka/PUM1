package com.example.l5z2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l5z2.ui.theme.L5Z2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    var firstNum by rememberSaveable { mutableStateOf("") }
    var secondNum by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("N/A") }

    fun calculate(op: String) {
        val n1 = firstNum.toIntOrNull()
        val n2= secondNum.toIntOrNull()
        if (n1 == null || n2 == null) {
            result = "error"
            return
        }

        result = when (op) {
            "+" -> (n1 +n2).toString()
            "-" -> (n1- n2).toString()
            "*" -> (n1 * n2).toString()
            "/" -> { if (n2 == 0) "error" else (n1 /n2).toString() }
            else -> "N/A"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = firstNum,
            onValueChange = { firstNum = it },
            placeholder = { Text("Enter first number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = secondNum,
            onValueChange = { secondNum = it },
            placeholder = { Text("Enter second number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { calculate("+") },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text("+")
            }
            Button(
                onClick = { calculate("-") },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text("-")
            }
            Button(
                onClick = { calculate("*") },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text("*")
            }
            Button(
                onClick = { calculate("/") },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text("/")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Result: $result",
        )
    }
}
