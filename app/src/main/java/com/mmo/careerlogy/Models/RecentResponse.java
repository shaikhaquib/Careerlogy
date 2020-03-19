package com.mmo.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecentResponse{

	@SerializedName("RecentlyAnsweredQuestions")
	private List<RecentlyAnsweredQuestionsItem> recentlyAnsweredQuestions;

	public void setRecentlyAnsweredQuestions(List<RecentlyAnsweredQuestionsItem> recentlyAnsweredQuestions){
		this.recentlyAnsweredQuestions = recentlyAnsweredQuestions;
	}

	public List<RecentlyAnsweredQuestionsItem> getRecentlyAnsweredQuestions(){
		return recentlyAnsweredQuestions;
	}

	@Override
 	public String toString(){
		return 
			"RecentResponse{" + 
			"recentlyAnsweredQuestions = '" + recentlyAnsweredQuestions + '\'' + 
			"}";
		}
}