package com.cp.tasky.auth.register.presentation

sealed class RegisterScreenEvent {
    data class RegisterUser(val userName: String, val email: String, val password: String): RegisterScreenEvent()
    data class SetUserName(val userName: String): RegisterScreenEvent()
    data class SetEmail(val email: String): RegisterScreenEvent()
    data class SetPassword(val password: String): RegisterScreenEvent()
    data class SetHidePassword(val visibility: Boolean): RegisterScreenEvent()
    data object BackButtonOnClick : RegisterScreenEvent()
}
