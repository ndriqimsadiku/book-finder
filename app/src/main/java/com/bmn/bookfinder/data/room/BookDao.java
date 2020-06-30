package com.bmn.bookfinder.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(ArrayList<BookEntity> books);

    @Query("SELECT * FROM bookentity")
    List<BookEntity> getAllBooks();

    @Query("SELECT * FROM bookentity WHERE topicId=:topicId")
    List<BookEntity> getBookByTopicId(long topicId);

    @Query("SELECT * FROM bookentity WHERE id=:bookId")
    BookEntity getBookById(String bookId);

    @Query("SELECT * FROM bookentity WHERE isFavorite=1")
    List<BookEntity> getFavoriteBooks();

    @Query("UPDATE bookentity SET isFavorite=:isFavorite WHERE  id=:bookId")
    List<BookEntity> setBookAsFavoriteById(String bookId, boolean isFavorite);
}
