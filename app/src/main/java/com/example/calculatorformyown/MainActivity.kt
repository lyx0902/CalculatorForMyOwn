package com.example.calculatorformyown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorformyown.ui.theme.BackgroundColor
import com.example.calculatorformyown.ui.theme.CalculatorForMyOwnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorForMyOwnTheme {
                val viewModel= viewModel<CalculatorViewModel>()
                val state = viewModel.state
                CalculatorScreen(state = state, onEvents = viewModel::onEvents,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor)
                        .padding(16.dp)
                )
            }
        }
    }
}
