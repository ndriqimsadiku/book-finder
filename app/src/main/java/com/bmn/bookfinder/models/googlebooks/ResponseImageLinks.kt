package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseImageLinks {
    @SerializedName("smallThumbnail")
    @Expose
    var smallThumbnail: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    companion object {
        private const val serialVersionUID = -4543956965328143864L
    }
}