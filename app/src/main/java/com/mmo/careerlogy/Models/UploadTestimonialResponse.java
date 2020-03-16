package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class UploadTestimonialResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("errormsg")
    private String errormsg;

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

    @Override
    public String toString() {
        return
                "UploadTestimonialResponse{" +
                        "error = '" + error + '\'' +
                        ",errormsg = '" + errormsg + '\'' +
                        "}";
    }
}