package com.cp.tasky.auth.register.presentation

import com.cp.tasky.core.data.util.Error

data class RegisterViewState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val shouldHidePassword: Boolean = true,
    val isValidUserName: Boolean = false,
    val isValidPassword: Boolean = false,
    val isValidEmail: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null
)
