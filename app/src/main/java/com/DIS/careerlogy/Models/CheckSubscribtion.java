package com.DIS.careerlogy.Models;

public class CheckSubscribtion {
	private boolean error;
	private String errormsg;

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
