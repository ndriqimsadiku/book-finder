package com.bmn.bookfinder.data.network.remote;

import com.bmn.bookfinder.models.NYTimesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCalls {
    //lists/current/hardcover-fiction.json

    @GET("lists.json")
    Call<NYTimesResponse> getBestSellers(@Query("list") String topic, @Query("api-key") String apiKey);

    @GET("volumes?q={query}&key={api_key} ")
    Call<List<Object>> getVolumes(@Path("query") String query, @Path("api_key") String apiKey);

    @GET("volumes?q={query}&filter={filter}&key={api_key}")
    Call<List<Object>> getVolumes(@Path("query") String query, @Path("filter") String filter, @Path("api_key") String apiKey);

    @GET("volumes/{volumeId}&key={api_key}")
    Call<List<Object>> getVolumeById(@Path("volumeId") String volumeId);
}