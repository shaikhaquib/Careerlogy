package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class DownloadlinksModel {

    @SerializedName("PolicyDocumentUrl")
    private String policyDocumentUrl;

    public void setPolicyDocumentUrl(String policyDocumentUrl) {
        this.policyDocumentUrl = policyDocumentUrl;
    }

    public String getPolicyDocumentUrl() {
        return policyDocumentUrl;
    }

    @Override
    public String toString() {
        return
                "DownloadlinksModel{" +
                        "policyDocumentUrl = '" + policyDocumentUrl + '\'' +
                        "}";
    }
}