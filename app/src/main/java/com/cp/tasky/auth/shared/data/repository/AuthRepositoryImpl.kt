package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import com.cp.tasky.auth.shared.data.mapper.LoginUserMapper
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import kotlin.math.log

class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : AuthRepository {

    override suspend fun loginUser(userCredential: UserCredential): LoginResponse {
        return LoginUserMapper.toLoginResponse(
            remoteDataSource.loginUser(
                LoginApiBody(
                    email = userCredential.email,
                    password = userCredential.password
                )
            )
        )
    }
}
