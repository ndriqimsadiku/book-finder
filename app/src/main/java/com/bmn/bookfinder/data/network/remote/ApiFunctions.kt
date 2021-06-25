package com.bmn.bookfinder.data.network.remote

import android.content.Context
import com.bmn.bookfinder.data.network.remote.ApiUtils.getGoogleApiService
import com.bmn.bookfinder.helpers.Google
import com.bmn.bookfinder.models.googlebooks.GBResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiFunctions {
    var apiResponseListener: ApiInterfaces.OnApiResponse? =
        null

    fun setApiGenresResponseListener(apiResponseListener: ApiInterfaces.OnApiResponse?) {
        this.apiResponseListener = apiResponseListener
    }

    fun getBooksBySubject(context: Context?, subject: String?) {
        val apiService = getGoogleApiService(context)
        apiService.getBooksBySubject(
            String.format(ApiUtils.QUERY_SUBJECT, subject),
            Google.API_KEY
        )!!.enqueue(object : Callback<GBResponse?> {
            override fun onResponse(call: Call<GBResponse?>, response: Response<GBResponse?>) {
                var error: String?
                if (response.isSuccessful) {
                    apiResponseListener?.onApiResponseCallback(true, response.body(), "")
                } else {
                    try {
                        error = response.errorBody()!!.string()
                    } catch (e: Exception) {
                        error = "Fail"
                        e.printStackTrace()
                    }
                    apiResponseListener?.onApiResponseCallback(false, response.body(), error)
                }
            }

            override fun onFailure(call: Call<GBResponse?>, t: Throwable) {
                apiResponseListener?.onApiResponseCallback(false, null, t.message)
            }
        })
    }

    fun searchBooks(context: Context?, query: String?) {
        val apiService = getGoogleApiService(context)
        apiService.getBooksBySubject(
            query,
            Google.API_KEY
        )!!.enqueue(object : Callback<GBResponse?> {
            override fun onResponse(call: Call<GBResponse?>, response: Response<GBResponse?>) {
                var error: String?
                if (response.isSuccessful) {
                    apiResponseListener?.onApiResponseCallback(true, response.body(), "")
                } else {
                    try {
                        error = response.errorBody()!!.string()
                    } catch (e: Exception) {
                        error = "Fail"
                        e.printStackTrace()
                    }
                    apiResponseListener?.onApiResponseCallback(false, response.body(), error)
                }
            }

            override fun onFailure(call: Call<GBResponse?>, t: Throwable) {
                apiResponseListener?.onApiResponseCallback(false, null, t.message)
            }
        })
    }
}