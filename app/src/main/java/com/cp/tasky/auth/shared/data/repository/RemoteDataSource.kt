package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.shared.data.remote.AuthApi
import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import retrofit2.Response
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun loginUser(loginApiBody: LoginApiBody): Response<LoginApiResponse>
}

class RemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : RemoteDataSource {
    override suspend fun loginUser(loginApiBody: LoginApiBody): Response<LoginApiResponse> {
        return authApi.loginUser(loginApiBody)
    }
}
