package com.bmn.bookfinder.data.network.remote;

import com.bmn.bookfinder.models.NYTimesResponse;
import com.bmn.bookfinder.models.googlebooks.GBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {

    @GET("lists.json")
    Call<NYTimesResponse> getBestSellers(@Query("list") String topic, @Query("api-key") String apiKey);

    @GET("volumes")
    Call<List<Object>> getVolumes(@Query("q") String query, @Query("api_key") String apiKey);

    @GET("volumes")
    Call<GBResponse> getBooksBySubject(@Query("q") String query, @Query("api_key") String apiKey);
}