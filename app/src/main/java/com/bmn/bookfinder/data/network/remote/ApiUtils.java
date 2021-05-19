package com.bmn.bookfinder.data.network.remote;

import android.content.Context;

import com.bmn.bookfinder.helpers.Constants;

public class ApiUtils {
    public static final String QUERY_SUBJECT = "subject:%s";

    public static ApiCalls getGoogleApiService(Context context) {
        return ApiClient.getApi(context, Constants.Google.BASE_URL).create(ApiCalls.class);
    }
}