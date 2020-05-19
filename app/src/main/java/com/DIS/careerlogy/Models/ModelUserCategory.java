package com.DIS.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelUserCategory{

	@SerializedName("UserCategory")
	private List<UserCategoryItem> userCategory;

	public void setUserCategory(List<UserCategoryItem> userCategory){
		this.userCategory = userCategory;
	}

	public List<UserCategoryItem> getUserCategory(){
		return userCategory;
	}

	@Override
 	public String toString(){
		return 
			"ModelUserCategory{" + 
			"userCategory = '" + userCategory + '\'' + 
			"}";
		}
}