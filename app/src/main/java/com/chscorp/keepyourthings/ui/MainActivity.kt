package com.chscorp.keepyourthings.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.chscorp.keepyourthings.ui.login.LoginScreenStateful
import com.chscorp.keepyourthings.ui.login.LoginViewModel
import com.chscorp.keepyourthings.ui.theme.KeepYourThingsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepYourThingsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = koinViewModel<LoginViewModel>()
                    LoginScreenStateful(viewModel, this)
                }
            }
        }
    }
}
