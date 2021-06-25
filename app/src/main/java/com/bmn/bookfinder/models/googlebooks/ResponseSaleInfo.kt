package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseSaleInfo : Serializable {
    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("saleability")
    @Expose
    var saleability: String? = null

    @SerializedName("isEbook")
    @Expose
    var isIsEbook = false
        private set

    @SerializedName("buyLink")
    @Expose
    var buyLink: String? = null
    fun setIsEbook(isEbook: Boolean) {
        isIsEbook = isEbook
    }

    companion object {
        private const val serialVersionUID = 176552784746523342L
    }
}