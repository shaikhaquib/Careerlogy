package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class QuestionsOfProblemSubCategoryItem{

	@SerializedName("Q_Added_DateTime")
	private String qAddedDateTime;

	@SerializedName("UM_Name")
	private String uMName;

	@SerializedName("Q_Modified_DateTime")
	private String qModifiedDateTime;

	@SerializedName("Q_Question_Title")
	private String qQuestionTitle;

	@SerializedName("Q_IsActive")
	private String qIsActive;

	@SerializedName("A_Added_DateTime")
	private String aAddedDateTime;

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

	public void setUMName(String uMName){
		this.uMName = uMName;
	}

	public String getUMName(){
		return uMName;
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

	public void setAAddedDateTime(String aAddedDateTime){
		this.aAddedDateTime = aAddedDateTime;
	}

	public String getAAddedDateTime(){
		return aAddedDateTime;
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
			"QuestionsOfProblemSubCategoryItem{" + 
			"q_Added_DateTime = '" + qAddedDateTime + '\'' + 
			",uM_Name = '" + uMName + '\'' + 
			",q_Modified_DateTime = '" + qModifiedDateTime + '\'' + 
			",q_Question_Title = '" + qQuestionTitle + '\'' + 
			",q_IsActive = '" + qIsActive + '\'' + 
			",a_Added_DateTime = '" + aAddedDateTime + '\'' + 
			",q_Deleted_DateTime = '" + qDeletedDateTime + '\'' + 
			",a_Answer = '" + aAnswer + '\'' + 
			",q_Question = '" + qQuestion + '\'' + 
			",q_Answered = '" + qAnswered + '\'' + 
			",q_ID = '" + qID + '\'' + 
			"}";
		}
}