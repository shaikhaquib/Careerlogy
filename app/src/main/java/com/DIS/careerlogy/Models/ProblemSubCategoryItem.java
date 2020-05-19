package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class ProblemSubCategoryItem{

	@SerializedName("PSC_Problem_Category_FK")
	private String pSCProblemCategoryFK;

	@SerializedName("PSC_Name")
	private String pSCName;

	@SerializedName("PSC_ID")
	private String pSCID;

	public void setPSCProblemCategoryFK(String pSCProblemCategoryFK){
		this.pSCProblemCategoryFK = pSCProblemCategoryFK;
	}

	public String getPSCProblemCategoryFK(){
		return pSCProblemCategoryFK;
	}

	public void setPSCName(String pSCName){
		this.pSCName = pSCName;
	}

	public String getPSCName(){
		return pSCName;
	}

	public void setPSCID(String pSCID){
		this.pSCID = pSCID;
	}

	public String getPSCID(){
		return pSCID;
	}

	@Override
 	public String toString(){
		return 
			"ProblemSubCategoryItem{" + 
			"pSC_Problem_Category_FK = '" + pSCProblemCategoryFK + '\'' + 
			",pSC_Name = '" + pSCName + '\'' + 
			",pSC_ID = '" + pSCID + '\'' + 
			"}";
		}
}