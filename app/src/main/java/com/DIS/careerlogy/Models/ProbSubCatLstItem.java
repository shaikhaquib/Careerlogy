package com.DIS.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class ProbSubCatLstItem {

    @SerializedName("PSC_DeletedBy")
    private Object pSCDeletedBy;

    @SerializedName("PSC_Modified_DateTime")
    private Object pSCModifiedDateTime;

    @SerializedName("PSC_ModifiedBy")
    private Object pSCModifiedBy;

    @SerializedName("PC_Icon_Url")
    private String pCIconUrl;

    @SerializedName("PSC_ID")
    private String pSCID;

    @SerializedName("PC_Type")
    private String pCType;

    @SerializedName("PSC_IsActive")
    private String pSCIsActive;

    @SerializedName("PC_Name")
    private String pCName;

    @SerializedName("PSC_Added_DateTime")
    private String pSCAddedDateTime;

    @SerializedName("PSC_AddedBy")
    private String pSCAddedBy;

    @SerializedName("PSC_Problem_Category_FK")
    private String pSCProblemCategoryFK;

    @SerializedName("PSC_Answer_Document_URL")
    private String pSCAnswerDocumentURL;

    @SerializedName("PSC_Name")
    private String pSCName;

    @SerializedName("PSC_Deleted_DateTime")
    private Object pSCDeletedDateTime;

    public Object getPSCDeletedBy() {
        return pSCDeletedBy;
    }

    public Object getPSCModifiedDateTime() {
        return pSCModifiedDateTime;
    }

    public Object getPSCModifiedBy() {
        return pSCModifiedBy;
    }

    public String getPCIconUrl() {
        return pCIconUrl;
    }

    public String getPSCID() {
        return pSCID;
    }

    public String getPCType() {
        return pCType;
    }

    public String getPSCIsActive() {
        return pSCIsActive;
    }

    public String getPCName() {
        return pCName;
    }

    public String getPSCAddedDateTime() {
        return pSCAddedDateTime;
    }

    public String getPSCAddedBy() {
        return pSCAddedBy;
    }

    public String getPSCProblemCategoryFK() {
        return pSCProblemCategoryFK;
    }

    public String getPSCAnswerDocumentURL() {
        return pSCAnswerDocumentURL;
    }

    public String getPSCName() {
        return pSCName;
    }

    public Object getPSCDeletedDateTime() {
        return pSCDeletedDateTime;
    }
}