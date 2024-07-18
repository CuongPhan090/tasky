package com.cp.tasky.auth.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.tasky.auth.shared.domain.usecase.UserInputAuthValidationUseCase
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.core.data.util.onError
import com.cp.tasky.core.data.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val userLoginValidationUseCase = UserInputAuthValidationUseCase()

    var screenState by mutableStateOf(LoginScreenState())
        private set

    private val _networkState = MutableStateFlow<Result<LoginResponse, Error>>(Result.Idle)
    val networkState = _networkState.asStateFlow()

    fun onEvents(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.LoginUser -> loginUser(
                email = event.email,
                password = event.password
            )

            is LoginScreenEvent.SetEmail -> setEmail(email = event.email)
            is LoginScreenEvent.SetHidePassword -> setHidePassword(visibility = event.visibility)
            is LoginScreenEvent.SetPassword -> setPassword(password = event.password)
        }
    }

    private fun loginUser(email: String, password: String) {
        _networkState.value = Result.Loading

        viewModelScope.launch {
            authRepository.loginUser(
                UserCredential(
                    email = email,
                    password = password
                )
            ).onSuccess { responseData ->
                _networkState.value = Result.Success(responseData)
            }.onError { error ->
                _networkState.value = Result.Error(error)
            }
        }
    }

    private fun setEmail(email: String) {
        val isValidEmail = userLoginValidationUseCase.isValidEmail(email)
        screenState = screenState.copy(
            email = email,
            isValidEmail = isValidEmail
        )
    }

    private fun setPassword(password: String) {
        val isPasswordValid = userLoginValidationUseCase.isValidPassword(password)
        screenState = screenState.copy(
            password = password,
            isValidPassword = isPasswordValid
        )
    }

    private fun setHidePassword(visibility: Boolean) {
        screenState = screenState.copy(
            shouldHidePassword = visibility
        )
    }
}
