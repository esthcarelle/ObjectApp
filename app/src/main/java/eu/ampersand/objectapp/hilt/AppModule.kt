package eu.ampersand.objectapp.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.ampersand.objectapp.network.ApiService
import eu.ampersand.objectapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/*
 * File: NetworkModule.kt
 * ------------------------------
 * Owner: Esther
 * © 2024 Ampersand. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    @Named("default")
    fun provideDefaultRetrofit(
        @Named("loggingInterceptor") loggingInterceptor: HttpLoggingInterceptor
    ): Retrofit {
        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            connectTimeout(1000, TimeUnit.SECONDS)
            readTimeout(1000, TimeUnit.SECONDS)
            writeTimeout(1000, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Named("default")
    fun provideApiClient(@Named("default") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
