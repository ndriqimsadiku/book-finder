package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseVolumeInfo : Serializable {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("authors")
    @Expose
    var authors: List<String>? = null

    @SerializedName("publishedDate")
    @Expose
    var publishedDate: String? = null

    @SerializedName("industryIdentifiers")
    @Expose
    var industryIdentifiers: List<ResponseIndustryIdentifier>? = null

    @SerializedName("readingModes")
    @Expose
    var readingModes: ResponseReadingModes? = null

    @SerializedName("pageCount")
    @Expose
    var pageCount = 0

    @SerializedName("printType")
    @Expose
    var printType: String? = null

    @SerializedName("maturityRating")
    @Expose
    var maturityRating: String? = null

    @SerializedName("allowAnonLogging")
    @Expose
    var isAllowAnonLogging = false

    @SerializedName("contentVersion")
    @Expose
    var contentVersion: String? = null

    @SerializedName("panelizationSummary")
    @Expose
    var panelizationSummary: ResponsePanelizationSummary? = null

    @SerializedName("imageLinks")
    @Expose
    var imageLinks: ResponseImageLinks? = null

    @SerializedName("language")
    @Expose
    var language: String? = null

    @SerializedName("previewLink")
    @Expose
    var previewLink: String? = null

    @SerializedName("infoLink")
    @Expose
    var infoLink: String? = null

    @SerializedName("canonicalVolumeLink")
    @Expose
    var canonicalVolumeLink: String? = null

    @SerializedName("publisher")
    @Expose
    var publisher: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("categories")
    @Expose
    var categories: List<String>? = null

    @SerializedName("averageRating")
    @Expose
    var averageRating = 0.0

    @SerializedName("ratingsCount")
    @Expose
    var ratingsCount = 0

    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null

    companion object {
        private const val serialVersionUID = 6349116934596601090L
    }
}