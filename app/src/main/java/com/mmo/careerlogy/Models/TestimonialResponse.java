package com.mmo.careerlogy.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestimonialResponse{

	@SerializedName("YouTubeVideoList")
	private List<YouTubeVideoListItem> youTubeVideoList;

	public void setYouTubeVideoList(List<YouTubeVideoListItem> youTubeVideoList){
		this.youTubeVideoList = youTubeVideoList;
	}

	public List<YouTubeVideoListItem> getYouTubeVideoList(){
		return youTubeVideoList;
	}

	@Override
 	public String toString(){
		return 
			"TestimonialResponse{" + 
			"youTubeVideoList = '" + youTubeVideoList + '\'' + 
			"}";
		}
}