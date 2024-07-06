package com.cp.tasky.auth.shared.domain

import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.NetworkResult

interface AuthRepository {

    suspend fun loginUser(userCredential: UserCredential): NetworkResult<LoginResponse>
}