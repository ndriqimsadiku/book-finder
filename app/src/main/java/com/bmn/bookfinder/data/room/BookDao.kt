package com.bmn.bookfinder.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: ArrayList<BookEntity?>?)

    @get:Query("SELECT * FROM bookentity")
    val allBooks: List<BookEntity?>?

    @Query("SELECT * FROM bookentity WHERE topicId=:topicId")
    fun getBookByTopicId(topicId: Long): List<BookEntity?>?

    @Query("SELECT * FROM bookentity WHERE id=:bookId")
    fun getBookById(bookId: String): BookEntity?

    @get:Query("SELECT * FROM bookentity WHERE isFavorite=1")
    val favoriteBooks: List<BookEntity?>?

    @Query("UPDATE bookentity SET isFavorite=:isFavorite WHERE  id=:bookId")
    fun setBookAsFavoriteById(bookId: String?, isFavorite: Boolean)

    @get:Query("SELECT * FROM bookentity WHERE isFavorite=1 GROUP BY topicId")
    val favoriteTopics: List<BookEntity?>?

    @Query("SELECT * FROM bookentity WHERE topicId = :topicId GROUP BY topicId")
    fun getCheckedTopic(topicId: Long): BookEntity?
}