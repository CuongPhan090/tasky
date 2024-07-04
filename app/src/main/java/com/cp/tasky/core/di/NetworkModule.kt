package com.cp.tasky.core.di

import com.cp.tasky.BuildConfig
import com.cp.tasky.core.Constants
import com.cp.tasky.core.Constants.API_HEADER_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshiAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .callTimeout(Constants.CALL_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClientInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(API_HEADER_KEY, BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }
    }
}
