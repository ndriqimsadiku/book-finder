package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseAccessInfo {
    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("viewability")
    @Expose
    var viewability: String? = null

    @SerializedName("embeddable")
    @Expose
    var isEmbeddable = false

    @SerializedName("publicDomain")
    @Expose
    var isPublicDomain = false

    @SerializedName("textToSpeechPermission")
    @Expose
    var textToSpeechPermission: String? = null

    @SerializedName("epub")
    @Expose
    var epub: com.bmn.bookfinder.models.googlebooks.ResponseEpub? = null

    @SerializedName("pdf")
    @Expose
    var pdf: com.bmn.bookfinder.models.googlebooks.ResponsePdf? = null

    @SerializedName("webReaderLink")
    @Expose
    var webReaderLink: String? = null

    @SerializedName("accessViewStatus")
    @Expose
    var accessViewStatus: String? = null

    @SerializedName("quoteSharingAllowed")
    @Expose
    var isQuoteSharingAllowed = false

    companion object {
        private const val serialVersionUID = 1320512232273578548L
    }
}