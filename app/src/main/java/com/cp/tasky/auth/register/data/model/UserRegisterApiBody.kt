package com.cp.tasky.auth.register.data.model

data class UserRegisterApiBody (
    val fullName: String,
    val email: String,
    val password: String
)
