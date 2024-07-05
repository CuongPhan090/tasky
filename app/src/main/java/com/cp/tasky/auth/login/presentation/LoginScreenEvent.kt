package com.cp.tasky.auth.login.presentation

sealed class LoginScreenEvent {
    data class LoginUser(val email: String, val password: String): LoginScreenEvent()
    data class SetEmail(val email: String): LoginScreenEvent()
    data class SetPassword(val password: String): LoginScreenEvent()
    data class SetHidePassword(val visibility: Boolean): LoginScreenEvent()
}
