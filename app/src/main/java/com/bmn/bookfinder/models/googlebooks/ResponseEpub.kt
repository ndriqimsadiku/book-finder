package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseEpub {
    @SerializedName("isAvailable")
    @Expose
    var isIsAvailable = false
        private set

    @SerializedName("downloadLink")
    @Expose
    var downloadLink: String? = null

    @SerializedName("acsTokenLink")
    @Expose
    var acsTokenLink: String? = null
    fun setIsAvailable(isAvailable: Boolean) {
        isIsAvailable = isAvailable
    }

    companion object {
        private const val serialVersionUID = -3197753727977801766L
    }
}