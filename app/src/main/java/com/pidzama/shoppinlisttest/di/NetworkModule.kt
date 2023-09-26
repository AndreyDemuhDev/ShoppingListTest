package com.pidzama.shoppinlisttest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.ApiService
import com.pidzama.shoppinlisttest.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): ApiService {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()


    @Singleton
    @Provides
    fun providesPostRepository(apiService: ApiService): ShoppingRepository {
        return ShoppingRepository(apiService = apiService)
    }
}