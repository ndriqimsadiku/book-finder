package com.bmn.bookfinder.models;

import com.google.gson.annotations.SerializedName;

public class NYTimesResponseResultsBookDetails {

    private String title;
    private String description;
    private String contributor;
    private String author;
    @SerializedName("contributor_note")
    private String contributorNote;
    private double price;
    @SerializedName("age_group")
    private String ageGroup;
    private String publisher;

    public NYTimesResponseResultsBookDetails(String title, String description, String contributor, String author, String contributorNote, double price, String ageGroup, String publisher) {
        this.title = title;
        this.description = description;
        this.contributor = contributor;
        this.author = author;
        this.contributorNote = contributorNote;
        this.price = price;
        this.ageGroup = ageGroup;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributorNote() {
        return contributorNote;
    }

    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
