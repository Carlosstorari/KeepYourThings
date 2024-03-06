package com.chscorp.keepyourthings.ui.login

data class LoginState(
    val email: String = "",
    val emailFieldError:String? = null,
    val password: String = "",
    val passwordFieldError: String? = null,
    val togglePasswordIcon: () -> Unit = {},
    val isPasswordVisible: Boolean = false
)
