package com.cp.tasky.auth.login.presentation

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val shouldHidePassword: Boolean = true,
    val isValidPassword: Boolean = false,
    val isValidEmail: Boolean = false
)