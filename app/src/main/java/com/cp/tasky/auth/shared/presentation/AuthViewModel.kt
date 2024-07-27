package com.cp.tasky.auth.shared.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cp.tasky.auth.shared.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var isAuthenticatedUser by mutableStateOf(false)
        private set

    fun isAuthenticated() {
        isAuthenticatedUser = authRepository.isAuthenticatedUser()
    }
}
