package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseIndustryIdentifier {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

    companion object {
        private const val serialVersionUID = -6812774192738608339L
    }
}