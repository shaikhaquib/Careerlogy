package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class AskedQuestionListItem {

    @SerializedName("UM_Name")
    private String uMName;

    @SerializedName("UM_MobileNo")
    private String uMMobileNo;

    @SerializedName("Q_Question_Title")
    private String qQuestionTitle;

    @SerializedName("UM_Gender")
    private String uMGender;

    @SerializedName("Q_User_Age")
    private String qUserAge;

    @SerializedName("Q_AddedBy")
    private String qAddedBy;

    @SerializedName("Q_Question")
    private String qQuestion;

    @SerializedName("UM_EmailID")
    private String uMEmailID;

    @SerializedName("Q_ID")
    private String qID;

    @SerializedName("PC_Type")
    private String pCType;

    @SerializedName("PC_Name")
    private String pCName;

    @SerializedName("Q_Added_DateTime")
    private String qAddedDateTime;

    @SerializedName("PSC_Name")
    private String pSCName;

    public String getUMName() {
        return uMName;
    }

    public void setUMName(String uMName) {
        this.uMName = uMName;
    }

    public String getUMMobileNo() {
        return uMMobileNo;
    }

    public void setUMMobileNo(String uMMobileNo) {
        this.uMMobileNo = uMMobileNo;
    }

    public String getQQuestionTitle() {
        return qQuestionTitle;
    }

    public void setQQuestionTitle(String qQuestionTitle) {
        this.qQuestionTitle = qQuestionTitle;
    }

    public String getUMGender() {
        return uMGender;
    }

    public void setUMGender(String uMGender) {
        this.uMGender = uMGender;
    }

    public String getQUserAge() {
        return qUserAge;
    }

    public void setQUserAge(String qUserAge) {
        this.qUserAge = qUserAge;
    }

    public String getQAddedBy() {
        return qAddedBy;
    }

    public void setQAddedBy(String qAddedBy) {
        this.qAddedBy = qAddedBy;
    }

    public String getQQuestion() {
        return qQuestion;
    }

    public void setQQuestion(String qQuestion) {
        this.qQuestion = qQuestion;
    }

    public String getUMEmailID() {
        return uMEmailID;
    }

    public void setUMEmailID(String uMEmailID) {
        this.uMEmailID = uMEmailID;
    }

    public String getQID() {
        return qID;
    }

    public void setQID(String qID) {
        this.qID = qID;
    }

    public String getPCType() {
        return pCType;
    }

    public void setPCType(String pCType) {
        this.pCType = pCType;
    }

    public String getPCName() {
        return pCName;
    }

    public void setPCName(String pCName) {
        this.pCName = pCName;
    }

    public String getQAddedDateTime() {
        return qAddedDateTime;
    }

    public void setQAddedDateTime(String qAddedDateTime) {
        this.qAddedDateTime = qAddedDateTime;
    }

    public String getPSCName() {
        return pSCName;
    }

    public void setPSCName(String pSCName) {
        this.pSCName = pSCName;
    }

    @Override
    public String toString() {
        return
                "AskedQuestionListItem{" +
                        "uM_Name = '" + uMName + '\'' +
                        ",uM_MobileNo = '" + uMMobileNo + '\'' +
                        ",q_Question_Title = '" + qQuestionTitle + '\'' +
                        ",uM_Gender = '" + uMGender + '\'' +
                        ",q_User_Age = '" + qUserAge + '\'' +
                        ",q_AddedBy = '" + qAddedBy + '\'' +
                        ",q_Question = '" + qQuestion + '\'' +
                        ",uM_EmailID = '" + uMEmailID + '\'' +
                        ",q_ID = '" + qID + '\'' +
                        ",pC_Type = '" + pCType + '\'' +
                        ",pC_Name = '" + pCName + '\'' +
                        ",q_Added_DateTime = '" + qAddedDateTime + '\'' +
                        ",pSC_Name = '" + pSCName + '\'' +
                        "}";
    }
}