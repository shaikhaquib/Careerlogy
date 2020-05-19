package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class QuestionsHistoryItem{

	@SerializedName("Q_Added_DateTime")
	private String qAddedDateTime;

	@SerializedName("Q_Modified_DateTime")
	private String qModifiedDateTime;

	@SerializedName("Q_Question_Title")
	private String qQuestionTitle;

	@SerializedName("Q_IsActive")
	private String qIsActive;

	@SerializedName("Q_Deleted_DateTime")
	private String qDeletedDateTime;

	@SerializedName("A_Answer")
	private String aAnswer;

	@SerializedName("Q_Question")
	private String qQuestion;

	@SerializedName("Q_Answered")
	private String qAnswered;

	@SerializedName("Q_ID")
	private String qID;

	public void setQAddedDateTime(String qAddedDateTime){
		this.qAddedDateTime = qAddedDateTime;
	}

	public String getQAddedDateTime(){
		return qAddedDateTime;
	}

	public void setQModifiedDateTime(String qModifiedDateTime){
		this.qModifiedDateTime = qModifiedDateTime;
	}

	public String getQModifiedDateTime(){
		return qModifiedDateTime;
	}

	public void setQQuestionTitle(String qQuestionTitle){
		this.qQuestionTitle = qQuestionTitle;
	}

	public String getQQuestionTitle(){
		return qQuestionTitle;
	}

	public void setQIsActive(String qIsActive){
		this.qIsActive = qIsActive;
	}

	public String getQIsActive(){
		return qIsActive;
	}

	public void setQDeletedDateTime(String qDeletedDateTime){
		this.qDeletedDateTime = qDeletedDateTime;
	}

	public String getQDeletedDateTime(){
		return qDeletedDateTime;
	}

	public void setAAnswer(String aAnswer){
		this.aAnswer = aAnswer;
	}

	public String getAAnswer(){
		return aAnswer;
	}

	public void setQQuestion(String qQuestion){
		this.qQuestion = qQuestion;
	}

	public String getQQuestion(){
		return qQuestion;
	}

	public void setQAnswered(String qAnswered){
		this.qAnswered = qAnswered;
	}

	public String getQAnswered(){
		return qAnswered;
	}

	public void setQID(String qID){
		this.qID = qID;
	}

	public String getQID(){
		return qID;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsHistoryItem{" + 
			"q_Added_DateTime = '" + qAddedDateTime + '\'' + 
			",q_Modified_DateTime = '" + qModifiedDateTime + '\'' + 
			",q_Question_Title = '" + qQuestionTitle + '\'' + 
			",q_IsActive = '" + qIsActive + '\'' + 
			",q_Deleted_DateTime = '" + qDeletedDateTime + '\'' + 
			",a_Answer = '" + aAnswer + '\'' + 
			",q_Question = '" + qQuestion + '\'' + 
			",q_Answered = '" + qAnswered + '\'' + 
			",q_ID = '" + qID + '\'' + 
			"}";
		}
}