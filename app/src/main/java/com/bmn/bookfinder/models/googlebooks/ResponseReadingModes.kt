package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseReadingModes {
    @SerializedName("text")
    @Expose
    var isText = false

    @SerializedName("image")
    @Expose
    var isImage = false

    companion object {
        private const val serialVersionUID = 5405707963316151255L
    }
}