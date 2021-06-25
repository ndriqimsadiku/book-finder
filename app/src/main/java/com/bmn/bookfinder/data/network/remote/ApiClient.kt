package com.bmn.bookfinder.data.network.remote

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private  lateinit var retrofit: Retrofit
    fun getApi(context: Context?, baseUrl: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain: Interceptor.Chain ->
                    val builder = chain.request().newBuilder()
                    builder.addHeader("Content-Type", "application/json")
                    builder.method(chain.request().method(), chain.request().body())
                    chain.proceed(builder.build())
                }
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit
    }
}