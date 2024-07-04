package com.cp.tasky.auth.shared.data.remote

import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun loginUser(@Body loginBody: LoginApiBody): LoginApiResponse
}
