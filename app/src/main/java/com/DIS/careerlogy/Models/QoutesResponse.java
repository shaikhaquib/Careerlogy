package com.DIS.careerlogy.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class QoutesResponse {

    @SerializedName("RecentQoutes")
    private List<RecentQoutesItem> recentQoutes;

    public List<RecentQoutesItem> getRecentQoutes() {
        return recentQoutes;
    }
}