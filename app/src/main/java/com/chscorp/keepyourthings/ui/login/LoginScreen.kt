package com.chscorp.keepyourthings.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chscorp.keepyourthings.ui.theme.KeepYourThingsTheme


@Composable
fun LoginScreenStateful(
    viewModel: LoginViewModel,
    context: Context
) {
    val state by viewModel.loginState.collectAsState()
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    LoginScreenStateless(
        state = state,
        onEmailChanged = {
            viewModel.onEvent(LoginEvent.EmailChanged(it))
        },
        onPasswordChanged = {
            viewModel.onEvent(LoginEvent.PasswordChanged(it))
        },
        onButtonClicked = {
            viewModel.onEvent(LoginEvent.Submit)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenStateless(
    state: LoginState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonClicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val email = state.email
        val password = state.password
        val isPasswordVisible = state.isPasswordVisible
        Spacer(modifier = Modifier.height(200.dp))
        TextField(
            value = email,
            onValueChange = { onEmailChanged(it) },
            isError = state.emailFieldError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Email") },
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )
        if (state.emailFieldError != null) {
            Text(
                text = state.emailFieldError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        val trailingIcon = @Composable {
            IconButton(onClick = { state.togglePasswordIcon() }) {
                Icon(
                    if (isPasswordVisible) {
                        Icons.Default.VisibilityOff
                    } else Icons.Default.Visibility,
                    contentDescription = ""
                )
            }
        }
        TextField(
            value = password,
            onValueChange = { onPasswordChanged(it) },
            isError = state.passwordFieldError != null,
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = { }
            ),
            placeholder = { Text("Senha") },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        if (state.passwordFieldError != null) {
            Text(
                text = state.passwordFieldError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onButtonClicked()
            },
            Modifier.fillMaxWidth(),
        ) {
            Text(text = "Entrar")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    KeepYourThingsTheme {
        Surface {
            //LoginScreenStateless()
        }
    }
}