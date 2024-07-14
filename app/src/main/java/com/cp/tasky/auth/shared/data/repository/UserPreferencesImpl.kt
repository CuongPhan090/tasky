package com.cp.tasky.auth.shared.data.repository

import android.content.SharedPreferences
import com.cp.tasky.auth.shared.domain.UserPreferences
import com.cp.tasky.auth.shared.domain.model.AuthenticatedUser
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : UserPreferences {

    companion object {
        const val AUTHENTICATED_USER_SHARED_PREFERENCES = "authenticated_user_shared_preferences"
        const val FULL_NAME = "full_name"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }


    override fun saveAuthenticatedUser(authenticatedUser: AuthenticatedUser) {
        sharedPreferences.edit().apply {
            putString(FULL_NAME, authenticatedUser.fullName)
            putString(ACCESS_TOKEN, authenticatedUser.accessToken)
            putString(REFRESH_TOKEN, authenticatedUser.refreshToken)
        }.apply()
    }

    override fun getAuthenticatedUser(): AuthenticatedUser? {
        return try {
            val fullName = sharedPreferences.getString(FULL_NAME, "").toString()
            val accessToken = sharedPreferences.getString(ACCESS_TOKEN, "").toString()
            val refreshToken = sharedPreferences.getString(REFRESH_TOKEN, "").toString()

            AuthenticatedUser(
                fullName = fullName,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } catch (e: Exception) {
            null
        }
    }

    override fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
