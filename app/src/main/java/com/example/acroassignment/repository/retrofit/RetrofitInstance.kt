package com.example.acroassignment.repository.retrofit

import com.example.acroassignment.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS) //Optional
                .readTimeout(20, TimeUnit.SECONDS) //Optional
                .writeTimeout(20, TimeUnit.SECONDS) //Optional
        }.build()

        fun getMovieRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.MOVIE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun getBooksRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BOOKS_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun getAppsRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.APPS_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}