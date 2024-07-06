package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.shared.data.mapper.LoginUserMapper
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.NetworkResult
import kotlinx.coroutines.CancellationException

class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : AuthRepository {

    override suspend fun loginUser(userCredential: UserCredential): NetworkResult<LoginResponse> {
        return try {
            val response = remoteDataSource.loginUser(
                LoginApiBody(
                    email = userCredential.email, password = userCredential.password
                )
            )

            NetworkResult.Success(
                LoginUserMapper.toLoginResponse(response)
            )

        } catch (e: Exception) {
            // Stop coroutine if it get cancelled, or timeout
            if (e is CancellationException) throw e

            NetworkResult.Error(e)
        }
    }
}
