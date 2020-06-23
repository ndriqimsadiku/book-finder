package com.bmn.bookfinder.data.network.remote;

import android.content.Context;

import com.bmn.bookfinder.helpers.Constants;

public class ApiUtils {

    public static ApiCalls getGoogleApiService(Context context) {
        return ApiClient.getApi(context, Constants.Google.BASE_URL).create(ApiCalls.class);
    }

    public static ApiCalls getNYTimesApiService(Context context) {
        return ApiClient.getApi(context, Constants.NYTimes.NY_BASE_URL).create(ApiCalls.class);
    }
}