package com.mmo.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class ProblemCategory{

	@SerializedName("ProblemCategory")
	private List<ProblemCategoryItem> problemCategory;

	public void setProblemCategory(List<ProblemCategoryItem> problemCategory){
		this.problemCategory = problemCategory;
	}

	public List<ProblemCategoryItem> getProblemCategory(){
		return problemCategory;
	}

	@Override
 	public String toString(){
		return 
			"ProblemCategory{" + 
			"problemCategory = '" + problemCategory + '\'' + 
			"}";
		}
}