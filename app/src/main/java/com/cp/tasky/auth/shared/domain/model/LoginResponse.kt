package com.cp.tasky.auth.shared.domain.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
    val message: String? = null
)
