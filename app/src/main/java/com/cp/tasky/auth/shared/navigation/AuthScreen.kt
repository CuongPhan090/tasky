package com.cp.tasky.auth.shared.navigation

import kotlinx.serialization.Serializable

sealed class AuthScreen {
    @Serializable
    data object Route: AuthScreen()

    @Serializable
    data object Login : AuthScreen()

    @Serializable
    data object Register : AuthScreen()
}
