package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class UserCategoryItem{

	@SerializedName("User_Category_Sub_Name")
	private String userCategorySubName;

	@SerializedName("User_Category_Name")
	private String userCategoryName;

	public void setUserCategorySubName(String userCategorySubName){
		this.userCategorySubName = userCategorySubName;
	}

	public String getUserCategorySubName(){
		return userCategorySubName;
	}

	public void setUserCategoryName(String userCategoryName){
		this.userCategoryName = userCategoryName;
	}

	public String getUserCategoryName(){
		return userCategoryName;
	}

	@Override
 	public String toString(){
		return 
			"UserCategoryItem{" + 
			"user_Category_Sub_Name = '" + userCategorySubName + '\'' + 
			",user_Category_Name = '" + userCategoryName + '\'' + 
			"}";
		}
}