package com.cp.tasky.auth.shared.di

import com.cp.tasky.auth.shared.data.remote.AuthApi
import com.cp.tasky.auth.shared.data.repository.AuthRepositoryImpl
import com.cp.tasky.auth.shared.data.repository.RemoteDataSource
import com.cp.tasky.auth.shared.data.repository.RemoteDataSourceImpl
import com.cp.tasky.auth.shared.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(remoteDataSource: RemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(authApi: AuthApi): RemoteDataSource {
        return RemoteDataSourceImpl(authApi)
    }
}
