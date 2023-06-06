package com.simbadev.youtubeclone.remote

import com.simbadev.youtubeclone.BuildConfig
import com.simbadev.youtubeclone.BuildConfig.*
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class RetrofitClient {

    companion object {

        fun create(): ApiService {
            val intercepor = HttpLoggingInterceptor()
            intercepor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(intercepor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}