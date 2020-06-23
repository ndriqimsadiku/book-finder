package com.bmn.bookfinder.data.network.remote;

public class ApiFunctions {

    ApiInterfaces.onApiResponse apiResponseListener;

    public void setApiGenresResponseListener(ApiInterfaces.onApiResponse apiResponseListener) {
        this.apiResponseListener = apiResponseListener;
    }
}