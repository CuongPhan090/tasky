package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.shared.data.mapper.LoginUserMapper
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.util.NetworkResult

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
            NetworkResult.Error(e)
        }
    }
}
