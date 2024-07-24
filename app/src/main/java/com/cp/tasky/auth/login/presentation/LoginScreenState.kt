package com.cp.tasky.auth.login.presentation

import com.cp.tasky.core.data.util.Error

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val shouldHidePassword: Boolean = true,
    val isValidPassword: Boolean = false,
    val isValidEmail: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: Error? = null
)
