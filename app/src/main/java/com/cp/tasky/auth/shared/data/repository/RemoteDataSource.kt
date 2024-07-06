package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.shared.data.remote.AuthApi
import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun loginUser(loginApiBody: LoginApiBody): LoginApiResponse
}

class RemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : RemoteDataSource {
    override suspend fun loginUser(loginApiBody: LoginApiBody): LoginApiResponse {
        return authApi.loginUser(loginApiBody)
    }
}
