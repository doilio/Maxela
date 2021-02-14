package com.maxela.android.di

import com.maxela.android.BuildConfig
import com.maxela.android.utils.BASE_URL
import com.maxela.android.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val client = OkHttpClient.Builder()

        // Only show logs in debug versions
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            client.addInterceptor(logger)
        }

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client.build())
            .build()
            .create(ApiService::class.java)
    }

}