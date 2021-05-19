package com.bmn.bookfinder.data.network.remote

import android.content.Context
import com.bmn.bookfinder.helpers.Google

object ApiUtils {
    const val QUERY_SUBJECT = "subject:%s"
    @JvmStatic
    fun getGoogleApiService(context: Context?): ApiCalls {
        return ApiClient.getApi(context, Google.BASE_URL).create(ApiCalls::class.java)
    }
}