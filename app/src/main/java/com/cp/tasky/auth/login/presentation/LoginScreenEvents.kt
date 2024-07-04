package com.cp.tasky.auth.login.presentation

sealed class LoginScreenEvents {
    data class LoginUser(val email: String, val password: String): LoginScreenEvents()
    data class SetEmail(val email: String): LoginScreenEvents()
    data class SetPassword(val password: String): LoginScreenEvents()
    data class SetHidePassword(val visibility: Boolean): LoginScreenEvents()
}
