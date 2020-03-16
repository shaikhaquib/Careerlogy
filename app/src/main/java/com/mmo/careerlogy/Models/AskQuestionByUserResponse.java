package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AskQuestionByUserResponse {

    @SerializedName("AskedQuestionList")
    private List<AskedQuestionListItem> askedQuestionList;

    public List<AskedQuestionListItem> getAskedQuestionList() {
        return askedQuestionList;
    }

    public void setAskedQuestionList(List<AskedQuestionListItem> askedQuestionList) {
        this.askedQuestionList = askedQuestionList;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "askedQuestionList = '" + askedQuestionList + '\'' +
                        "}";
    }
}