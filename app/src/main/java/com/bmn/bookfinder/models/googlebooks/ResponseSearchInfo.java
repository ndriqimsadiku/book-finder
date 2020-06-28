package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearchInfo {

    private final static long serialVersionUID = 4170785424721980651L;
    @SerializedName("textSnippet")
    @Expose
    private String textSnippet;

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }
}

