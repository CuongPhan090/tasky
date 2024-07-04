package com.cp.tasky.auth.login.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginApiResponse(
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
    val message: String? = null
)
