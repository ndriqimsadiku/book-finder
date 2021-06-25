package com.bmn.bookfinder.data.network.remote

import com.bmn.bookfinder.models.ApiResponse

class ApiInterfaces {
    interface OnApiResponse<in T> {
        fun onApiResponseCallback(status: Boolean, apiResponse: T?, message: String)
    }
}