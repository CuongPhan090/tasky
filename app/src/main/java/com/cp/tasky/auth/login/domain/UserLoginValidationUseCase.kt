package com.cp.tasky.auth.login.domain

import android.util.Patterns

class UserLoginValidationUseCase {

    fun isValidUserLogin(email: String, password: String): Boolean {
        return isValidEmail(email) && isValidPassword(password)
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isDigit() } &&
                password.length >= 9
    }
}
