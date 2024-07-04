package com.cp.tasky.auth.shared.domain

import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential

interface AuthRepository {

    suspend fun loginUser(userCredential: UserCredential): LoginResponse
}
