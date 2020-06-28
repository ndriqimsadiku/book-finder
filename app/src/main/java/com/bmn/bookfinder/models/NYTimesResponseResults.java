package com.bmn.bookfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NYTimesResponseResults {
    @SerializedName("list_name")
    private String listName;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("bestsellers_date")
    private String bestsellersDate;
    @SerializedName("published_date")
    private String publishedDate;
    private int rank;
    @SerializedName("rank_last_week")
    private int rankLastWeek;
    @SerializedName("weeks_on_list")
    private int weeksOnList;
    @SerializedName("amazon_product_url")
    private String amazonProductUrl;
    @SerializedName("book_details")
    private List<NYTimesResponseResultsBookDetails> bookDetails;

    public NYTimesResponseResults(String listName, String displayName, String bestsellersDate, String publishedDate, int rank, int rankLastWeek, int weeksOnList, String amazonProductUrl, List<NYTimesResponseResultsBookDetails> bookDetails) {
        this.listName = listName;
        this.displayName = displayName;
        this.bestsellersDate = bestsellersDate;
        this.publishedDate = publishedDate;
        this.rank = rank;
        this.rankLastWeek = rankLastWeek;
        this.weeksOnList = weeksOnList;
        this.amazonProductUrl = amazonProductUrl;
        this.bookDetails = bookDetails;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBestsellersDate() {
        return bestsellersDate;
    }

    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRankLastWeek() {
        return rankLastWeek;
    }

    public void setRankLastWeek(int rankLastWeek) {
        this.rankLastWeek = rankLastWeek;
    }

    public int getWeeksOnList() {
        return weeksOnList;
    }

    public void setWeeksOnList(int weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    public String getAmazonProductUrl() {
        return amazonProductUrl;
    }

    public void setAmazonProductUrl(String amazonProductUrl) {
        this.amazonProductUrl = amazonProductUrl;
    }

    public List<NYTimesResponseResultsBookDetails> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<NYTimesResponseResultsBookDetails> bookDetails) {
        this.bookDetails = bookDetails;
    }
}
