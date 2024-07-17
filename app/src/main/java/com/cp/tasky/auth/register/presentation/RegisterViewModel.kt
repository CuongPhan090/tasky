package com.cp.tasky.auth.register.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.UserRegisterBody
import com.cp.tasky.auth.shared.domain.usecase.UserInputAuthValidationUseCase
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.core.data.util.onError
import com.cp.tasky.core.data.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val userInputAuthValidationUseCase = UserInputAuthValidationUseCase()

    var registerScreenState by mutableStateOf(RegisterViewState())
        private set

    val registerScreenNetworkState = MutableStateFlow<Result<Unit, Error>>(Result.Idle)

    fun onEvents(registerScreenEvent: RegisterScreenEvent) {
        when (registerScreenEvent) {
            RegisterScreenEvent.BackButtonOnClick -> {
                //TODO: go back to login screen
            }

            is RegisterScreenEvent.RegisterUser -> {
                registerUser(
                    userName = registerScreenEvent.userName,
                    email = registerScreenEvent.email,
                    password = registerScreenEvent.password
                )
            }

            is RegisterScreenEvent.SetEmail -> setEmail(registerScreenEvent.email)
            is RegisterScreenEvent.SetHidePassword -> setHidePassword(registerScreenEvent.visibility)
            is RegisterScreenEvent.SetPassword -> setPassword(registerScreenEvent.password)
            is RegisterScreenEvent.SetUserName -> setUserName(registerScreenEvent.userName)
        }
    }

    private fun registerUser(userName: String, email: String, password: String) {
        registerScreenNetworkState.value = Result.Loading

        viewModelScope.launch {
            authRepository.registerUser(
                UserRegisterBody(
                    fullName = userName,
                    email = email,
                    password = password
                )
            ).onSuccess {
                registerScreenNetworkState.value = Result.Success(Unit)
            }.onError { error ->
                registerScreenNetworkState.value = Result.Error(error)
            }
        }
    }

    private fun setEmail(email: String) {
        val isValidEmail = userInputAuthValidationUseCase.isValidEmail(email)
        registerScreenState = registerScreenState.copy(
            email = email,
            isValidEmail = isValidEmail
        )
    }

    private fun setHidePassword(visibility: Boolean) {
        registerScreenState = registerScreenState.copy(
            shouldHidePassword = visibility
        )
    }

    private fun setPassword(password: String) {
        val isValidPassword = userInputAuthValidationUseCase.isValidPassword(password)
        registerScreenState = registerScreenState.copy(
            password = password,
            isValidPassword = isValidPassword
        )
    }

    private fun setUserName(userName: String) {
        val isValidUserName = userInputAuthValidationUseCase.isValidName(userName)
        registerScreenState = registerScreenState.copy(
            userName = userName,
            isValidUserName = isValidUserName
        )
    }
}
