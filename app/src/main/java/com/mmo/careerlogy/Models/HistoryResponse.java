package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResponse{

	@SerializedName("QuestionsHistory")
	private List<QuestionsHistoryItem> questionsHistory;

	public void setQuestionsHistory(List<QuestionsHistoryItem> questionsHistory){
		this.questionsHistory = questionsHistory;
	}

	public List<QuestionsHistoryItem> getQuestionsHistory(){
		return questionsHistory;
	}

	@Override
 	public String toString(){
		return 
			"HistoryResponse{" + 
			"questionsHistory = '" + questionsHistory + '\'' + 
			"}";
		}
}