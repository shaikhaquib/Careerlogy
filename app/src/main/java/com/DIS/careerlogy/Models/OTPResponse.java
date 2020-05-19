package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class OTPResponse{

	@SerializedName("error")
	private boolean error;

	@SerializedName("errormsg")
	private String errormsg;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setErrormsg(String errormsg){
		this.errormsg = errormsg;
	}

	public String getErrormsg(){
		return errormsg;
	}

	@Override
 	public String toString(){
		return 
			"OTPResponse{" + 
			"error = '" + error + '\'' + 
			",errormsg = '" + errormsg + '\'' + 
			"}";
		}
}