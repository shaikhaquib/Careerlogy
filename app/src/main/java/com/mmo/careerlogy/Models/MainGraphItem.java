package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class MainGraphItem{

	@SerializedName("age_group")
	private String ageGroup;

	@SerializedName("MALE")
	private String mALE;

	@SerializedName("FEMALE")
	private String fEMALE;

	public void setAgeGroup(String ageGroup){
		this.ageGroup = ageGroup;
	}

	public String getAgeGroup(){
		return ageGroup;
	}

	public void setMALE(String mALE){
		this.mALE = mALE;
	}

	public String getMALE(){
		return mALE;
	}

	public void setFEMALE(String fEMALE){
		this.fEMALE = fEMALE;
	}

	public String getFEMALE(){
		return fEMALE;
	}

	@Override
 	public String toString(){
		return 
			"MainGraphItem{" + 
			"age_group = '" + ageGroup + '\'' + 
			",mALE = '" + mALE + '\'' + 
			",fEMALE = '" + fEMALE + '\'' + 
			"}";
		}
}