package com.bmn.bookfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * *   "results": [
 *  *     {
 *  *       "list_name": "Hardcover Fiction",
 *  *       "display_name": "Hardcover Fiction",
 *  *       "bestsellers_date": "2016-03-05",
 *  *       "published_date": "2016-03-20",
 *  *       "rank": 5,
 *  *       "rank_last_week": 2,
 *  *       "weeks_on_list": 2,
 *  *       "asterisk": 0,
 *  *       "dagger": 0,
 *  *       "amazon_product_url": "http://www.amazon.com/Girls-Guide-Moving-On-Novel-ebook/dp/B00ZNE17B4?tag=thenewyorktim-20",
 *  *       "isbns": [
 *  *         {
 *  *           "isbn10": "0553391925",
 *  *           "isbn13": "9780553391923"
 *  *         }
 *  *       ],
 *  *       "book_details": [
 *  *         {
 *  *           "title": "A GIRL'S GUIDE TO MOVING ON",
 *  *           "description": "A mother and her daughter-in-law both leave unhappy marriages and take up with new men.",
 *  *           "contributor": "by Debbie Macomber",
 *  *           "author": "Debbie Macomber",
 *  *           "contributor_note": "",
 *  *           "price": 0,
 *  *           "age_group": "",
 *  *           "publisher": "Ballantine",
 *  *           "primary_isbn13": "9780553391923",
 *  *           "primary_isbn10": "0553391925"
 *  *         }
 *  *       ],
 *  *       "reviews": [
 *  *         {
 *  *           "book_review_link": "",
 *  *           "first_chapter_link": "",
 *  *           "sunday_review_link": "",
 *  *           "article_chapter_link": ""
 *  *         }
 *  *       ]
 *  *     }
 *  *   ]*/

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
