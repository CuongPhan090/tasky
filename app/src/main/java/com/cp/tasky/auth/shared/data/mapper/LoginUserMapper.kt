package com.cp.tasky.auth.shared.data.mapper

import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import com.cp.tasky.auth.shared.domain.model.LoginResponse

object LoginUserMapper {

    fun toLoginResponse(loginApiResponse: LoginApiResponse): LoginResponse {
        return LoginResponse(
            accessToken = loginApiResponse.accessToken,
            refreshToken = loginApiResponse.refreshToken,
            fullName = loginApiResponse.fullName,
            userId = loginApiResponse.userId,
            accessTokenExpirationTimestamp = loginApiResponse.accessTokenExpirationTimestamp,
            message = loginApiResponse.message
        )
    }
}
