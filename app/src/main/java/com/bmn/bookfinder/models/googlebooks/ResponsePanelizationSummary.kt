package com.bmn.bookfinder.models.googlebooks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponsePanelizationSummary {
    @SerializedName("containsEpubBubbles")
    @Expose
    var isContainsEpubBubbles = false

    @SerializedName("containsImageBubbles")
    @Expose
    var isContainsImageBubbles = false

    companion object {
        private const val serialVersionUID = -184837627883766434L
    }
}