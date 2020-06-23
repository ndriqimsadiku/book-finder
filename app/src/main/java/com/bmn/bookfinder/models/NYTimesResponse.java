package com.bmn.bookfinder.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/***
 * {
 *   "status": "OK",
 *   "copyright": "Copyright (c) 2019 The New York Times Company.  All Rights Reserved.",
 *   "num_results": 1,
 *   "last_modified": "2016-03-11T13:09:01-05:00",
 *   "results": [
 *     {
 *       "list_name": "Hardcover Fiction",
 *       "display_name": "Hardcover Fiction",
 *       "bestsellers_date": "2016-03-05",
 *       "published_date": "2016-03-20",
 *       "rank": 5,
 *       "rank_last_week": 2,
 *       "weeks_on_list": 2,
 *       "asterisk": 0,
 *       "dagger": 0,
 *       "amazon_product_url": "http://www.amazon.com/Girls-Guide-Moving-On-Novel-ebook/dp/B00ZNE17B4?tag=thenewyorktim-20",
 *       "isbns": [
 *         {
 *           "isbn10": "0553391925",
 *           "isbn13": "9780553391923"
 *         }
 *       ],
 *       "book_details": [
 *         {
 *           "title": "A GIRL'S GUIDE TO MOVING ON",
 *           "description": "A mother and her daughter-in-law both leave unhappy marriages and take up with new men.",
 *           "contributor": "by Debbie Macomber",
 *           "author": "Debbie Macomber",
 *           "contributor_note": "",
 *           "price": 0,
 *           "age_group": "",
 *           "publisher": "Ballantine",
 *           "primary_isbn13": "9780553391923",
 *           "primary_isbn10": "0553391925"
 *         }
 *       ],
 *       "reviews": [
 *         {
 *           "book_review_link": "",
 *           "first_chapter_link": "",
 *           "sunday_review_link": "",
 *           "article_chapter_link": ""
 *         }
 *       ]
 *     }
 *   ]
 * }
 */

public class NYTimesResponse extends ApiResponse {
    private String status;
    private String copyright;
    @SerializedName("num_results")
    private int numResults;
    @SerializedName("last_modified")
    private String lastModified;
    private List<NYTimesResponseResults> results;

    public NYTimesResponse(String status, String copyright, int numResults, String lastModified, List<NYTimesResponseResults> results) {
        this.status = status;
        this.copyright = copyright;
        this.numResults = numResults;
        this.lastModified = lastModified;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<NYTimesResponseResults> getResults() {
        return results;
    }

    public void setResults(List<NYTimesResponseResults> results) {
        this.results = results;
    }
}
