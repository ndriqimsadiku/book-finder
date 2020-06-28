package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseEpub {

    private final static long serialVersionUID = -3197753727977801766L;
    @SerializedName("isAvailable")
    @Expose
    private boolean isAvailable;
    @SerializedName("downloadLink")
    @Expose
    private String downloadLink;
    @SerializedName("acsTokenLink")
    @Expose
    private String acsTokenLink;

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

}
