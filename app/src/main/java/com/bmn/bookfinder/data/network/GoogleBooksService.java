package com.bmn.bookfinder.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoogleBooksService {
    @GET("volumes?q={query}&key={api_key} ")
    Call<List<Object>> getVolumes(@Path("query") String query, @Path("api_key") String apiKey);

    @GET("volumes?q={query}&filter={filter}&key={api_key}")
    Call<List<Object>> getVolumes(@Path("query") String query, @Path("filter") String filter, @Path("api_key") String apiKey);

    @GET("volumes/{volumeId}&key={api_key}")
    Call<List<Object>> getVolumeById(@Path("volumeId") String volumeId);
}
