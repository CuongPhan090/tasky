package com.cp.tasky.auth.shared.domain

import com.cp.tasky.auth.shared.domain.model.AuthenticatedUser

interface UserPreferences {
    fun saveAuthenticatedUser(authenticatedUser: AuthenticatedUser)
    fun getAuthenticatedUser(): AuthenticatedUser?
    fun clearPreferences()
}
