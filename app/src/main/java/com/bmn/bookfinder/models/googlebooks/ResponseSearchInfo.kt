package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseSearchInfo {
    @SerializedName("textSnippet")
    @Expose
    var textSnippet: String? = null

    companion object {
        private const val serialVersionUID = 4170785424721980651L
    }
}