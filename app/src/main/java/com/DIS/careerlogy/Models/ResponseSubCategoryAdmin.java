package com.DIS.careerlogy.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseSubCategoryAdmin {

    @SerializedName("ProbSubCatLst")
    private List<ProbSubCatLstItem> probSubCatLst;

    public List<ProbSubCatLstItem> getProbSubCatLst() {
        return probSubCatLst;
    }
}