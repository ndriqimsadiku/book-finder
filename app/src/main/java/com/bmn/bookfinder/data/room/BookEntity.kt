package com.bmn.bookfinder.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
@Entity
data class BookEntity(
    @PrimaryKey var id: String,
    var topicId: Long,
    var topic: String,
    var title: String,
    var description: String,
    var thumbnailUrl: String,
    @TypeConverters(Converters::class) var authors: List<String>,
    var averageRating: Double,
    var pageCount: Int,
    var publishedDate: String,
    var isFavorite: Boolean
)