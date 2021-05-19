package com.bmn.bookfinder.models

data class Topic(
    var id: Int? = null,
    var backgroundColor: Int = 0,
    var thumbnailUrl: String? = null,
    var text: String,
    var image: Int = 0,
)