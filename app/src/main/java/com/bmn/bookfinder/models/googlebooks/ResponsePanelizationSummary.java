package com.bmn.bookfinder.models.googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePanelizationSummary {

    private final static long serialVersionUID = -184837627883766434L;
    @SerializedName("containsEpubBubbles")
    @Expose
    private boolean containsEpubBubbles;
    @SerializedName("containsImageBubbles")
    @Expose
    private boolean containsImageBubbles;

    public boolean isContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public boolean isContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }

}

