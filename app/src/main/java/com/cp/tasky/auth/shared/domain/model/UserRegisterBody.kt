package com.cp.tasky.auth.shared.domain.model

data class UserRegisterBody(
    val fullName: String,
    val email: String,
    val password: String
)
