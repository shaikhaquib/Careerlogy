package com.DIS.careerlogy.Models;

import java.util.List;

public class CouponGenrateResponse {
    private List<CouponsListItem> couponsList;
    private boolean error;
    private String errormsg;

    public void setCouponsList(List<CouponsListItem> couponsList) {
        this.couponsList = couponsList;
    }

    public List<CouponsListItem> getCouponsList() {
        return couponsList;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getErrormsg() {
        return errormsg;
    }
}