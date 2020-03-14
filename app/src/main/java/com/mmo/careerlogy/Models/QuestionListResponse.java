package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionListResponse{

	@SerializedName("QuestionsOfProblemSubCategory")
	private List<QuestionsOfProblemSubCategoryItem> questionsOfProblemSubCategory;

	public void setQuestionsOfProblemSubCategory(List<QuestionsOfProblemSubCategoryItem> questionsOfProblemSubCategory){
		this.questionsOfProblemSubCategory = questionsOfProblemSubCategory;
	}

	public List<QuestionsOfProblemSubCategoryItem> getQuestionsOfProblemSubCategory(){
		return questionsOfProblemSubCategory;
	}

	@Override
 	public String toString(){
		return 
			"QuestionListResponse{" + 
			"questionsOfProblemSubCategory = '" + questionsOfProblemSubCategory + '\'' + 
			"}";
		}
}