package org.example.calculatorcmp

import CalculatorApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import utils.PreferencesFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PreferencesFactory.setInstance(AndroidPreferences(this))

        setContent {
            CalculatorApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    CalculatorApp()
}