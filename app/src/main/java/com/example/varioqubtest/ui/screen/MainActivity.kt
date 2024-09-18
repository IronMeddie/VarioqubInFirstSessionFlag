package com.example.varioqubtest.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.varioqubtest.ui.theme.VarioqubTestTheme
import com.example.varioqubtest.varioqub.GetRemoteConfigString


class MainActivity : ComponentActivity() {

    private val remoteFlag = GetRemoteConfigString("feature_alert", "")

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VarioqubTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLoaded = viewModel.isLoaded.collectAsState().value
                    if (isLoaded) {
                        Screen(remoteFlag())
                    } else {
                        Loading()
                    }
                }
            }
        }
    }

    @Composable
    fun Loading() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun Screen(value: String) {
        val isNewColor = viewModel.isNewColor.collectAsState().value
        Box(
            modifier = Modifier.fillMaxSize()
                .background(if (isNewColor) Color.Blue else Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "feature_alert = ${value.ifBlank { "value is blank" }}",
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        VarioqubTestTheme {
            Screen("")
        }
    }
}