package com.bmn.bookfinder.data.network.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getApi(Context context, String baseUrl) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Content-Type", "application/json");

            builder.method(chain.request().method(), chain.request().body());
            return chain.proceed(builder.build());
        }).readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

}