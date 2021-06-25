package com.bmn.bookfinder.data.network.remote

import com.bmn.bookfinder.models.ApiResponse

class ApiInterfaces {
    interface OnApiResponse {
        fun onApiResponseCallback(status: Boolean, apiResponse: ApiResponse, message: String)
    }
}