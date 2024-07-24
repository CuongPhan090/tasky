package com.cp.tasky.auth.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.auth.shared.domain.usecase.UserInputAuthValidationUseCase
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.core.data.util.onError
import com.cp.tasky.core.data.util.onSuccess
import com.cp.tasky.core.presentation.util.UiText
import com.cp.tasky.core.presentation.util.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val userLoginValidationUseCase = UserInputAuthValidationUseCase()

    var screenState by mutableStateOf(LoginScreenState())
        private set

    private val _networkResponse = Channel<LoginEvent?>(Channel.BUFFERED)
    val networkResponse = _networkResponse.receiveAsFlow()

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
        viewModelScope.launch {
            screenState = screenState.copy(isLoading = true, errorMessage = null)

            authRepository.loginUser(
                UserCredential(
                    email = email,
                    password = password
                )
            ).onSuccess {
                _networkResponse.send(LoginEvent.LoginSuccess)
                screenState = screenState.copy(isLoading = false, errorMessage = null)
            }.onError { error ->
                screenState = screenState.copy(isLoading = false, errorMessage = error)
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
