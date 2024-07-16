package com.cp.tasky.auth.shared.data.repository

import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.register.data.model.UserRegisterApiBody
import com.cp.tasky.auth.shared.data.mapper.LoginUserMapper
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.UserPreferences
import com.cp.tasky.auth.shared.domain.model.AuthenticatedUser
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserRegisterBody
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val userPreferencesImpl: UserPreferences,
) : AuthRepository {

    override suspend fun loginUser(userCredential: UserCredential): Result<LoginResponse, Error> {
        return try {
            val response = remoteDataSource.loginUser(
                LoginApiBody(
                    email = userCredential.email, password = userCredential.password
                )
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    userPreferencesImpl.saveAuthenticatedUser(
                        AuthenticatedUser(
                            fullName = it.fullName,
                            accessToken = it.accessToken,
                            refreshToken = it.refreshToken
                        )
                    )

                    Result.Success(
                        LoginUserMapper.toLoginResponse(it)
                    )
                } ?: run {
                    Result.Error(
                        error = DataError.Remote.UNKNOWN
                    )
                }
            } else {
                when (response.code()) {
                    in 400..499 -> Result.Error(error = DataError.Remote.UNAUTHORIZED)
                    in 500..599 -> Result.Error(error = DataError.Remote.SERVER_NOT_AVAILABLE)
                    else -> Result.Error(error = DataError.Remote.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            // Stop coroutine if it get cancelled, or time out
            if (e is CancellationException) throw e
            Result.Error(error = DataError.Remote.UNKNOWN)
        }
    }

    override suspend fun registerUser(userRegisterBody: UserRegisterBody) { //TODO: Handle error cases : Result<Nothing, Error>
        try {
            remoteDataSource.registerUser(
                UserRegisterApiBody(
                    fullName = userRegisterBody.fullName,
                    email = userRegisterBody.email,
                    password = userRegisterBody.password
                )
            )
        } catch (e: Exception) {
            // Stop coroutine if it get cancelled, or time out
            if (e is CancellationException) throw e
        }
    }
}
