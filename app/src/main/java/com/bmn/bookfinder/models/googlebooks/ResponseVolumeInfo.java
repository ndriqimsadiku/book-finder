package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseVolumeInfo implements Serializable {

    private final static long serialVersionUID = 6349116934596601090L;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("industryIdentifiers")
    @Expose
    private List<ResponseIndustryIdentifier> industryIdentifiers = null;
    @SerializedName("readingModes")
    @Expose
    private ResponseReadingModes readingModes;
    @SerializedName("pageCount")
    @Expose
    private int pageCount;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("maturityRating")
    @Expose
    private String maturityRating;
    @SerializedName("allowAnonLogging")
    @Expose
    private boolean allowAnonLogging;
    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;
    @SerializedName("panelizationSummary")
    @Expose
    private ResponsePanelizationSummary panelizationSummary;
    @SerializedName("imageLinks")
    @Expose
    private ResponseImageLinks imageLinks;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("previewLink")
    @Expose
    private String previewLink;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("canonicalVolumeLink")
    @Expose
    private String canonicalVolumeLink;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("averageRating")
    @Expose
    private double averageRating;
    @SerializedName("ratingsCount")
    @Expose
    private int ratingsCount;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<ResponseIndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<ResponseIndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ResponseReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ResponseReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public ResponsePanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(ResponsePanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public ResponseImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ResponseImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}