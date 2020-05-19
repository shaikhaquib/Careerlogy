package com.DIS.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProblemSubCategoryResponse {

	@SerializedName("ProblemSubCategory")
	private List<ProblemSubCategoryItem> problemSubCategory;

	public void setProblemSubCategory(List<ProblemSubCategoryItem> problemSubCategory){
		this.problemSubCategory = problemSubCategory;
	}

	public List<ProblemSubCategoryItem> getProblemSubCategory(){
		return problemSubCategory;
	}

	@Override
 	public String toString(){
		return 
			"ProblemCategory{" + 
			"problemSubCategory = '" + problemSubCategory + '\'' + 
			"}";
		}
}