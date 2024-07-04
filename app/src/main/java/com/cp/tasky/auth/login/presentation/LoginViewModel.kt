package com.cp.tasky.auth.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private val userLoginValidationUseCase = UserLoginValidationUseCase()

    var loginScreenState by mutableStateOf(LoginScreenState())
        private set

    fun onEvents(event: LoginScreenEvents) {
        when (event) {
            is LoginScreenEvents.LoginUser -> loginUser(
                email = event.email,
                password = event.password
            )

            is LoginScreenEvents.SetEmail -> setEmail(email = event.email)
            is LoginScreenEvents.SetHidePassword -> setHidePassword(visibility = event.visibility)
            is LoginScreenEvents.SetPassword -> setPassword(password = event.password)
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            authRepository.loginUser(
                UserCredential(
                    email = email,
                    password = password
                )
            )
        }
    }

    private fun setEmail(email: String) {
        val isValidEmail = userLoginValidationUseCase.isValidEmail(email)
        loginScreenState = loginScreenState.copy(
            email = email,
            isValidEmail = isValidEmail
        )
    }

    private fun setPassword(password: String) {
        val isPasswordValid = userLoginValidationUseCase.isValidPassword(password)
        loginScreenState = loginScreenState.copy(
            password = password,
            isValidPassword = isPasswordValid
        )
    }

    private fun setHidePassword(visibility: Boolean) {
        loginScreenState = loginScreenState.copy(
            shouldHidePassword = visibility
        )
    }
}
