package com.bmn.bookfinder.data.network.remote;

import com.bmn.bookfinder.models.googlebooks.GBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("volumes")
    Call<GBResponse> getBooksBySubject(@Query("q") String query, @Query("api_key") String apiKey);
}