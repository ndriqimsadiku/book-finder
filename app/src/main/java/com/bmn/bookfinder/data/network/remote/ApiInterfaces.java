package com.bmn.bookfinder.data.network.remote;

import com.bmn.bookfinder.models.ApiResponse;

public class ApiInterfaces {
    public interface onApiResponse {
        void onApiResponse(boolean status, ApiResponse apiResponse, String message);
    }
}