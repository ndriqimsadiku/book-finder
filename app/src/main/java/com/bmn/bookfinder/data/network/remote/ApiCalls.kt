package com.bmn.bookfinder.data.network.remote

import com.bmn.bookfinder.models.googlebooks.GBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("volumes")
    fun getBooksBySubject(
        @Query("q") query: String?,
        @Query("api_key") apiKey: String?
    ): Call<GBResponse?>?
}