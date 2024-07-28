package com.cp.tasky.agenda.overview.di

import com.cp.tasky.agenda.overview.data.remote.OverviewApi
import com.cp.tasky.agenda.overview.data.remote.RemoteDataResource
import com.cp.tasky.agenda.overview.data.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OverviewModule {

    @Provides
    @Singleton
    fun providePlannerHubApi(retrofit: Retrofit): OverviewApi {
        return retrofit.create(OverviewApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(overviewApi: OverviewApi): RemoteDataResource {
        return RemoteDataSourceImpl(overviewApi)
    }
}
