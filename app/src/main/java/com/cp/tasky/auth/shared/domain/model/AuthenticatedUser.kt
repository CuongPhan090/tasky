package com.cp.tasky.auth.shared.domain.model

data class AuthenticatedUser(
    val fullName: String,
    val accessToken: String,
    val refreshToken: String
)
