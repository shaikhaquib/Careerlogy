package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryOperationsEditResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("errormsg")
    private String errormsg;

    public boolean isError() {
        return error;
    }

    public String getErrormsg() {
        return errormsg;
    }
}