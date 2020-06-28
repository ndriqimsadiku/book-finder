package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseReadingModes {

    private final static long serialVersionUID = 5405707963316151255L;
    @SerializedName("text")
    @Expose
    private boolean text;
    @SerializedName("image")
    @Expose
    private boolean image;

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

}
