package com.bmn.bookfinder.models;

public class Topic {
    public long id;
    public int backgroundColor;
    private String text;
    private int image;

    public Topic(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public Topic(String text, int image, int backgroundColor) {
        this.text = text;
        this.image = image;
        this.backgroundColor = backgroundColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
