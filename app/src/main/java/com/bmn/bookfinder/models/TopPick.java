package com.bmn.bookfinder.models;

public class TopPick {
    private String title;
    private String imageUrl;
    private String bookId;

    public TopPick(String title, String imageUrl, String bookId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
