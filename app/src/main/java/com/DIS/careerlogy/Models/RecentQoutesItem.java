package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class RecentQoutesItem {

    @SerializedName("qoutes_url")
    private String qoutesUrl;

    @SerializedName("qoutes_id")
    private String qoutesId;

    public String getQoutesUrl() {
        return qoutesUrl;
    }

    public String getQoutesId() {
        return qoutesId;
    }
}