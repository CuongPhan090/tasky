package com.cp.tasky.auth.shared.data.remote

import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import com.cp.tasky.auth.register.data.model.UserRegisterApiBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun loginUser(@Body loginBody: LoginApiBody): Response<LoginApiResponse>

    @POST("/register")
    suspend fun registerUser(@Body registerApiBody: UserRegisterApiBody): Response<Unit>
}
