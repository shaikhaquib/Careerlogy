package com.DIS.careerlogy.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class QuestionsHistoryItem {

    @SerializedName("QuestionsHistory")
    private List<QuestionsHistoryItem> questionsHistory;

    @SerializedName("answeredBy")
    private String answeredBy;

    @SerializedName("Q_Modified_DateTime")
    private Object qModifiedDateTime;

    @SerializedName("Q_Question_Title")
    private String qQuestionTitle;

    @SerializedName("Q_IsActive")
    private String qIsActive;

    @SerializedName("A_Added_DateTime")
    private String aAddedDateTime;

    @SerializedName("Q_Deleted_DateTime")
    private Object qDeletedDateTime;

    @SerializedName("Q_Question")
    private String qQuestion;

    @SerializedName("Q_Need_Clarification")
    private String qNeedClarification;

    @SerializedName("Q_ID")
    private String qID;

    @SerializedName("Q_Added_DateTime")
    private String qAddedDateTime;

    @SerializedName("A_Answer")
    private String aAnswer;

    @SerializedName("PSC_Answer_Document_URL")
    private String pSCAnswerDocumentURL;

    @SerializedName("Q_Answered")
    private String qAnswered;

    public List<QuestionsHistoryItem> getQuestionsHistory() {
        return questionsHistory;
    }

    public String getAnsweredBy() {
        return answeredBy;
    }

    public Object getQModifiedDateTime() {
        return qModifiedDateTime;
    }

    public String getQQuestionTitle() {
        return qQuestionTitle;
    }

    public String getQIsActive() {
        return qIsActive;
    }

    public String getAAddedDateTime() {
        return aAddedDateTime;
    }

    public Object getQDeletedDateTime() {
        return qDeletedDateTime;
    }

    public String getQQuestion() {
        return qQuestion;
    }

    public String getQNeedClarification() {
        return qNeedClarification;
    }

    public String getQID() {
        return qID;
    }

    public String getQAddedDateTime() {
        return qAddedDateTime;
    }

    public String getAAnswer() {
        return aAnswer;
    }

    public String getPSCAnswerDocumentURL() {
        return pSCAnswerDocumentURL;
    }

    public String getQAnswered() {
        return qAnswered;
    }
}