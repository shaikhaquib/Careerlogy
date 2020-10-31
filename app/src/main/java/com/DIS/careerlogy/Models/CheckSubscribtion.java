package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class CheckSubscribtion {

    @SerializedName("DaysLeft")
    private int daysLeft;

    @SerializedName("PreviouslySubscribed")
    private boolean previouslySubscribed;

    @SerializedName("error")
    private boolean error;

    @SerializedName("errormsg")
    private String errormsg;

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public boolean isPreviouslySubscribed() {
        return previouslySubscribed;
    }

    public void setPreviouslySubscribed(boolean previouslySubscribed) {
        this.previouslySubscribed = previouslySubscribed;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}