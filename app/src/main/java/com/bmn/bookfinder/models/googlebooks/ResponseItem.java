package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseItem implements Serializable {

    private final static long serialVersionUID = 6818353111860798447L;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("selfLink")
    @Expose
    private String selfLink;
    @SerializedName("volumeInfo")
    @Expose
    private ResponseVolumeInfo volumeInfo;
    @SerializedName("saleInfo")
    @Expose
    private ResponseSaleInfo saleInfo;
    @SerializedName("accessInfo")
    @Expose
    private ResponseAccessInfo accessInfo;
    @SerializedName("searchInfo")
    @Expose
    private ResponseSearchInfo searchInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public ResponseVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(ResponseVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public ResponseSaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(ResponseSaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public ResponseAccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(ResponseAccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    public ResponseSearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(ResponseSearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

}