package com.cp.tasky.auth.shared.domain

import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserRegisterBody
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result

interface AuthRepository {

    suspend fun loginUser(userCredential: UserCredential): Result<LoginResponse, Error>

    suspend fun registerUser(userRegisterBody: UserRegisterBody): Result<Unit, Error>

    fun isAuthenticatedUser(): Boolean
}
