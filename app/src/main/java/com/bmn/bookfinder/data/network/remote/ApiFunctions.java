package com.bmn.bookfinder.data.network.remote;

import android.content.Context;

import com.bmn.bookfinder.helpers.Google;
import com.bmn.bookfinder.models.googlebooks.GBResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiFunctions {

    ApiInterfaces.onApiResponse apiResponseListener;

    public void setApiGenresResponseListener(ApiInterfaces.onApiResponse apiResponseListener) {
        this.apiResponseListener = apiResponseListener;
    }

    public void getBooksBySubject(Context context, String subject) {
        ApiCalls apiService = ApiUtils.getGoogleApiService(context);
        apiService.getBooksBySubject(
                String.format(ApiUtils.QUERY_SUBJECT, subject),
                Google.API_KEY).enqueue(new Callback<GBResponse>() {
            @Override
            public void onResponse(@NotNull Call<GBResponse> call, @NotNull Response<GBResponse> response) {
                String error;
                if (response.isSuccessful()) {
                    apiResponseListener.onApiResponse(true, response.body(), "");
                } else {
                    try {
                        error = response.errorBody().string();
                    } catch (Exception e) {
                        error = "Fail";
                        e.printStackTrace();
                    }
                    apiResponseListener.onApiResponse(false, response.body(), error);
                }
            }

            @Override
            public void onFailure(@NotNull Call<GBResponse> call, @NotNull Throwable t) {
                apiResponseListener.onApiResponse(false, null, t.getMessage());
            }
        });
    }

    public void searchBooks(Context context, String query) {
        ApiCalls apiService = ApiUtils.getGoogleApiService(context);
        apiService.getBooksBySubject(
                query,
                Google.API_KEY).enqueue(new Callback<GBResponse>() {
            @Override
            public void onResponse(@NotNull Call<GBResponse> call, @NotNull Response<GBResponse> response) {
                String error;
                if (response.isSuccessful()) {
                    apiResponseListener.onApiResponse(true, response.body(), "");
                } else {
                    try {
                        error = response.errorBody().string();
                    } catch (Exception e) {
                        error = "Fail";
                        e.printStackTrace();
                    }
                    apiResponseListener.onApiResponse(false, response.body(), error);
                }
            }

            @Override
            public void onFailure(@NotNull Call<GBResponse> call, @NotNull Throwable t) {
                apiResponseListener.onApiResponse(false, null, t.getMessage());
            }
        });
    }


}