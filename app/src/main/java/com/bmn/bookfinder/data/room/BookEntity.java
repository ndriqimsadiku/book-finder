package com.bmn.bookfinder.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
@Entity
public class BookEntity {
    @PrimaryKey
    @ColumnInfo
    @NotNull
    private String id;
    @ColumnInfo
    private long topicId;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String thumbnailUrl;
    @ColumnInfo
    @TypeConverters(Converters.class)
    private List<String> authors;
    @ColumnInfo
    private double averageRating;
    @ColumnInfo
    private int pageCount;
    @ColumnInfo
    private String publishedDate;
    @ColumnInfo
    private boolean isFavorite;

    public BookEntity(String id, long topicId, String title, String description, String thumbnailUrl, List<String> authors, double averageRating, int pageCount, String publishedDate, boolean isFavorite) {
        this.id = id;
        this.topicId = topicId;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.authors = authors;
        this.averageRating = averageRating;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
