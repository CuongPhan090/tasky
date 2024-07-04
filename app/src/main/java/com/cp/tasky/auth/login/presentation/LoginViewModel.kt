package com.cp.tasky.auth.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    private var _shouldHidePassword = mutableStateOf(true)
    val shouldHidePassword: State<Boolean> = _shouldHidePassword

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

    fun isValidEmail(): Boolean {
        return userLoginValidationUseCase.isValidEmail(email.value)
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun isValidPassword(): Boolean {
        return userLoginValidationUseCase.isValidPassword(password.value)
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setHidePassword(visibility: Boolean) {
        _shouldHidePassword.value = visibility
    }
}
