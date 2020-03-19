package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryGraphItem{

	@SerializedName("CategoryName")
	private String categoryName;

	@SerializedName("Male")
	private String male;

	@SerializedName("Female")
	private String female;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setMale(String male){
		this.male = male;
	}

	public String getMale(){
		return male;
	}

	public void setFemale(String female){
		this.female = female;
	}

	public String getFemale(){
		return female;
	}

	@Override
 	public String toString(){
		return 
			"CategoryGraphItem{" + 
			"categoryName = '" + categoryName + '\'' + 
			",male = '" + male + '\'' + 
			",female = '" + female + '\'' + 
			"}";
		}
}