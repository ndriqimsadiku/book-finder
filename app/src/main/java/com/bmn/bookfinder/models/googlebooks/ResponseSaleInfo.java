package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseSaleInfo implements Serializable
{

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("saleability")
    @Expose
    private String saleability;
    @SerializedName("isEbook")
    @Expose
    private boolean isEbook;
    @SerializedName("buyLink")
    @Expose
    private String buyLink;
    private final static long serialVersionUID = 176552784746523342L;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public boolean isIsEbook() {
        return isEbook;
    }

    public void setIsEbook(boolean isEbook) {
        this.isEbook = isEbook;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

}
