package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class YouTubeVideoListItem{

	@SerializedName("YTT_Description")
	private String yTTDescription;

	@SerializedName("YTT_Title")
	private String yTTTitle;

	@SerializedName("YTT_IsActive")
	private String yTTIsActive;

	@SerializedName("YTT_ID")
	private String yTTID;

	@SerializedName("YTT_Video_Link")
	private String yTTVideoLink;

	public void setYTTDescription(String yTTDescription){
		this.yTTDescription = yTTDescription;
	}

	public String getYTTDescription(){
		return yTTDescription;
	}

	public void setYTTTitle(String yTTTitle){
		this.yTTTitle = yTTTitle;
	}

	public String getYTTTitle(){
		return yTTTitle;
	}

	public void setYTTIsActive(String yTTIsActive){
		this.yTTIsActive = yTTIsActive;
	}

	public String getYTTIsActive(){
		return yTTIsActive;
	}

	public void setYTTID(String yTTID){
		this.yTTID = yTTID;
	}

	public String getYTTID(){
		return yTTID;
	}

	public void setYTTVideoLink(String yTTVideoLink){
		this.yTTVideoLink = yTTVideoLink;
	}

	public String getYTTVideoLink(){
		return yTTVideoLink;
	}

	@Override
 	public String toString(){
		return 
			"YouTubeVideoListItem{" + 
			"yTT_Description = '" + yTTDescription + '\'' + 
			",yTT_Title = '" + yTTTitle + '\'' + 
			",yTT_IsActive = '" + yTTIsActive + '\'' + 
			",yTT_ID = '" + yTTID + '\'' + 
			",yTT_Video_Link = '" + yTTVideoLink + '\'' + 
			"}";
		}
}