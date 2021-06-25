package com.bmn.bookfinder.models.googlebooks

import com.bmn.bookfinder.models.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GBResponse : ApiResponse(), Serializable {
    @SerializedName("kind")
    @Expose
    var kind: String? = null

    @SerializedName("totalItems")
    @Expose
    var totalItems = 0

    @SerializedName("items")
    @Expose
    var items: List<ResponseItem> = emptyList()

    companion object {
        private const val serialVersionUID = 2414488511190131242L
    }
}