package com.mmo.careerlogy.Models;

import java.util.List;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("userinfo")
	private List<UserinfoItem> userinfo;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUserinfo(List<UserinfoItem> userinfo){
		this.userinfo = userinfo;
	}

	public List<UserinfoItem> getUserinfo(){
		return userinfo;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",userinfo = '" + userinfo + '\'' + 
			"}";
		}
}