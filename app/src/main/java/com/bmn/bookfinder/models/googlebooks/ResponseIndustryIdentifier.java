package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseIndustryIdentifier {

    private final static long serialVersionUID = -6812774192738608339L;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
