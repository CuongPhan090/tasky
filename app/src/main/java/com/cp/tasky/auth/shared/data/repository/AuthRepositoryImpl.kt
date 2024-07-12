package com.cp.tasky.auth.shared.data.repository

import android.content.SharedPreferences
import com.cp.tasky.auth.login.data.remote.models.LoginApiBody
import com.cp.tasky.auth.login.data.remote.models.LoginApiResponse
import com.cp.tasky.auth.shared.data.Constant
import com.cp.tasky.auth.shared.data.mapper.LoginUserMapper
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.model.LoginResponse
import com.cp.tasky.auth.shared.domain.model.UserCredential
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Time
import java.util.concurrent.TimeUnit

class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val sharedPreferences: SharedPreferences,
) : AuthRepository {

    override suspend fun loginUser(userCredential: UserCredential): Result<LoginResponse, Error> {
        return try {
            withContext(Dispatchers.IO) {
                val response = remoteDataSource.loginUser(
                    LoginApiBody(
                        email = userCredential.email, password = userCredential.password
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        putResponseToSharedPref(it)

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
            }
        } catch (e: Exception) {
            // Stop coroutine if it get cancelled, or time out
            if (e is CancellationException) throw e
            Result.Error(error = DataError.Remote.UNKNOWN)
        }
    }

    private fun putResponseToSharedPref(loginApiResponse: LoginApiResponse) {
        sharedPreferences.edit().apply {
            putString(Constant.FULL_NAME, loginApiResponse.fullName)
            putString(Constant.USER_ID, loginApiResponse.userId)
            putString(Constant.ACCESS_TOKEN, loginApiResponse.accessToken)
            putString(Constant.REFRESH_TOKEN, loginApiResponse.refreshToken)
            putString(Constant.ACCESS_TOKEN_EXP_TIME_STAMP, loginApiResponse.refreshToken)
        }.apply()
    }
}
