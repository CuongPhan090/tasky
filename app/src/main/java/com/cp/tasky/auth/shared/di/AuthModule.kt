package com.cp.tasky.auth.shared.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cp.tasky.auth.shared.data.remote.AuthApi
import com.cp.tasky.auth.shared.data.repository.AuthRepositoryImpl
import com.cp.tasky.auth.shared.data.repository.RemoteDataSource
import com.cp.tasky.auth.shared.data.repository.RemoteDataSourceImpl
import com.cp.tasky.auth.shared.data.repository.UserPreferencesImpl
import com.cp.tasky.auth.shared.domain.AuthRepository
import com.cp.tasky.auth.shared.domain.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(remoteDataSource: RemoteDataSource, userPreferences: UserPreferences): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource = remoteDataSource, userPreferencesImpl = userPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(authApi: AuthApi): RemoteDataSource {
        return RemoteDataSourceImpl(authApi)
    }


    @Provides
    @Singleton
    fun provideUserPreferences(sharedPreferences: SharedPreferences): UserPreferences {
        return UserPreferencesImpl(sharedPreferences = sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(UserPreferencesImpl.AUTHENTICATED_USER_SHARED_PREFERENCES, MODE_PRIVATE)
    }
}
