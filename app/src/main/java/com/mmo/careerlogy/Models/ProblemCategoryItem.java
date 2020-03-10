package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class ProblemCategoryItem{

	@SerializedName("PC_ID")
	private String pCID;

	@SerializedName("PC_Name")
	private String pCName;

	@SerializedName("PC_Icon_Url")
	private String pCIconUrl;

	public void setPCID(String pCID){
		this.pCID = pCID;
	}

	public String getPCID(){
		return pCID;
	}

	public void setPCName(String pCName){
		this.pCName = pCName;
	}

	public String getPCName(){
		return pCName;
	}

	public void setPCIconUrl(String pCIconUrl){
		this.pCIconUrl = pCIconUrl;
	}

	public String getPCIconUrl(){
		return pCIconUrl;
	}

	@Override
 	public String toString(){
		return 
			"ProblemCategoryItem{" + 
			"pC_ID = '" + pCID + '\'' + 
			",pC_Name = '" + pCName + '\'' + 
			",pC_Icon_Url = '" + pCIconUrl + '\'' + 
			"}";
		}
}