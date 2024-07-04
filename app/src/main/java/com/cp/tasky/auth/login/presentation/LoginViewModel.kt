package com.cp.tasky.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.tasky.auth.login.domain.UserLoginValidationUseCase
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.UserCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val userLoginValidationUseCase: UserLoginValidationUseCase = UserLoginValidationUseCase()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            authRepository.loginUser(
                UserCredential(
                    email = email,
                    password = password
                )
            )
        }
    }

    fun isValidEmail(email: String): Boolean {
        return userLoginValidationUseCase.isValidEmail(email)
    }

    fun isValidPassword(password: String): Boolean {
        return userLoginValidationUseCase.isValidPassword(password)
    }
}
