package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseItem : Serializable {
    @SerializedName("kind")
    @Expose
    var kind: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("etag")
    @Expose
    var etag: String? = null

    @SerializedName("selfLink")
    @Expose
    var selfLink: String? = null

    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: ResponseVolumeInfo? = null

    @SerializedName("saleInfo")
    @Expose
    var saleInfo: ResponseSaleInfo? = null

    @SerializedName("accessInfo")
    @Expose
    var accessInfo: ResponseAccessInfo? = null

    @SerializedName("searchInfo")
    @Expose
    var searchInfo: ResponseSearchInfo? = null

    companion object {
        private const val serialVersionUID = 6818353111860798447L
    }
}