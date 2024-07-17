package com.cp.tasky.auth.shared.domain.usecase

import android.util.Patterns

class UserInputAuthValidationUseCase {

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isDigit() } &&
                password.length >= 9
    }

    fun isValidName(name: String): Boolean {
        return name.length in 4..50
    }
}
