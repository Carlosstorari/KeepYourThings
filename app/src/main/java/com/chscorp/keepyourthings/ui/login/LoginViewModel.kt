package com.chscorp.keepyourthings.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chscorp.keepyourthings.domain.useCase.validateEmail.ValidateEmailUseCase
import com.chscorp.keepyourthings.domain.useCase.validatePassword.ValidatePasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase
) : ViewModel() {
    private val _loginUiState: MutableStateFlow<LoginState> = MutableStateFlow(
        LoginState()
    )
    val loginState get() = _loginUiState.asStateFlow()

    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = _validationEventChannel.receiveAsFlow()

    init {
        _loginUiState.update { currentState ->
            currentState.copy(
                togglePasswordIcon = {
                    _loginUiState.value = _loginUiState.value.copy(
                        isPasswordVisible = !_loginUiState.value.isPasswordVisible
                    )
                }
            )
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginUiState.value = _loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginEvent.PasswordChanged -> {
                _loginUiState.value = _loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(_loginUiState.value.email)
        val passwordResult = validatePassword.execute(_loginUiState.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }
        if (hasError) {
            _loginUiState.value = _loginUiState.value.copy(
                emailFieldError = emailResult.errorMessage,
                passwordFieldError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            _validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}