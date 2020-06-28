package com.bmn.bookfinder.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

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
