package com.DIS.careerlogy.Models;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserinfoItem {

	@SerializedName("UM_DateOfBirth")
	private String uMDateOfBirth;

	@SerializedName("UM_Name")
	private String uMName;

	@SerializedName("UM_MobileNo")
	private String uMMobileNo;

	@SerializedName("User_Category_AddedBy")
	private String userCategoryAddedBy;

	@SerializedName("UM_Password")
	private String uMPassword;

	@SerializedName("UM_Modified_DateTime")
	private String uMModifiedDateTime;

	@SerializedName("UM_Category_FK")
	private String uMCategoryFK;

	@SerializedName("User_Category_IsActive")
	private String userCategoryIsActive;

	@SerializedName("User_Category_DeletedBy")
	private String userCategoryDeletedBy;

	@SerializedName("User_Category_Name")
	private String userCategoryName;

	@SerializedName("User_Category_Added_DateTime")
	private String userCategoryAddedDateTime;

	@SerializedName("UM_Country")
	private String uMCountry;

	@SerializedName("UM_Type")
	private String uMType;

	@SerializedName("UM_Added_DateTime")
	private String uMAddedDateTime;

	@SerializedName("UM_Deleted_DateTime")
	private String uMDeletedDateTime;

	@SerializedName("UM_ModifiedBy")
	private String uMModifiedBy;

	@SerializedName("UM_DeletedBy")
	private String uMDeletedBy;

	@SerializedName("User_Category_ID")
	private String userCategoryID;

	@SerializedName("UM_AddedBy")
	private String uMAddedBy;

	@SerializedName("UM_City")
	private String uMCity;

	@SerializedName("UM_CanDrop")
	private String uMCanDrop;

	@SerializedName("UM_Gender")
	private String uMGender;

	@SerializedName("UM_CanUpdate")
	private String uMCanUpdate;

	@SerializedName("User_Category_ModifiedBy")
	private String userCategoryModifiedBy;

	@SerializedName("UM_State")
	private String uMState;

	@SerializedName("UM_EmailID")
	private String uMEmailID;

	@SerializedName("UM_CanDelete")
	private String uMCanDelete;

    @NonNull
	@PrimaryKey
	@SerializedName("UM_ID")
	private String uMID;

	@SerializedName("UM_User_Status")
	private String uMUserStatus;

	@SerializedName("User_Category_Sub_Name")
	private String userCategorySubName;

	@SerializedName("User_Category_Modified_DateTime")
	private String userCategoryModifiedDateTime;

	@SerializedName("UM_CanSelect")
	private String uMCanSelect;

	@SerializedName("User_Category_Deleted_DateTime")
	private String userCategoryDeletedDateTime;

	public void setUMDateOfBirth(String uMDateOfBirth){
		this.uMDateOfBirth = uMDateOfBirth;
	}

	public String getUMDateOfBirth(){
		return uMDateOfBirth;
	}

	public void setUMName(String uMName){
		this.uMName = uMName;
	}

	public String getUMName(){
		return uMName;
	}

	public void setUMMobileNo(String uMMobileNo){
		this.uMMobileNo = uMMobileNo;
	}

	public String getUMMobileNo(){
		return uMMobileNo;
	}

	public void setUserCategoryAddedBy(String userCategoryAddedBy){
		this.userCategoryAddedBy = userCategoryAddedBy;
	}

	public String getUserCategoryAddedBy(){
		return userCategoryAddedBy;
	}

	public void setUMPassword(String uMPassword){
		this.uMPassword = uMPassword;
	}

	public String getUMPassword(){
		return uMPassword;
	}

	public void setUMModifiedDateTime(String uMModifiedDateTime){
		this.uMModifiedDateTime = uMModifiedDateTime;
	}

	public String getUMModifiedDateTime(){
		return uMModifiedDateTime;
	}

	public void setUMCategoryFK(String uMCategoryFK){
		this.uMCategoryFK = uMCategoryFK;
	}

	public String getUMCategoryFK(){
		return uMCategoryFK;
	}

	public void setUserCategoryIsActive(String userCategoryIsActive){
		this.userCategoryIsActive = userCategoryIsActive;
	}

	public String getUserCategoryIsActive(){
		return userCategoryIsActive;
	}

	public void setUserCategoryDeletedBy(String userCategoryDeletedBy){
		this.userCategoryDeletedBy = userCategoryDeletedBy;
	}

	public String getUserCategoryDeletedBy(){
		return userCategoryDeletedBy;
	}

	public void setUserCategoryName(String userCategoryName){
		this.userCategoryName = userCategoryName;
	}

	public String getUserCategoryName(){
		return userCategoryName;
	}

	public void setUserCategoryAddedDateTime(String userCategoryAddedDateTime){
		this.userCategoryAddedDateTime = userCategoryAddedDateTime;
	}

	public String getUserCategoryAddedDateTime(){
		return userCategoryAddedDateTime;
	}

	public void setUMCountry(String uMCountry){
		this.uMCountry = uMCountry;
	}

	public String getUMCountry(){
		return uMCountry;
	}

	public void setUMType(String uMType){
		this.uMType = uMType;
	}

	public String getUMType(){
		return uMType;
	}

	public void setUMAddedDateTime(String uMAddedDateTime){
		this.uMAddedDateTime = uMAddedDateTime;
	}

	public String getUMAddedDateTime(){
		return uMAddedDateTime;
	}

	public void setUMDeletedDateTime(String uMDeletedDateTime){
		this.uMDeletedDateTime = uMDeletedDateTime;
	}

	public String getUMDeletedDateTime(){
		return uMDeletedDateTime;
	}

	public void setUMModifiedBy(String uMModifiedBy){
		this.uMModifiedBy = uMModifiedBy;
	}

	public String getUMModifiedBy(){
		return uMModifiedBy;
	}

	public void setUMDeletedBy(String uMDeletedBy){
		this.uMDeletedBy = uMDeletedBy;
	}

	public String getUMDeletedBy(){
		return uMDeletedBy;
	}

	public void setUserCategoryID(String userCategoryID){
		this.userCategoryID = userCategoryID;
	}

	public String getUserCategoryID(){
		return userCategoryID;
	}

	public void setUMAddedBy(String uMAddedBy){
		this.uMAddedBy = uMAddedBy;
	}

	public String getUMAddedBy(){
		return uMAddedBy;
	}

	public void setUMCity(String uMCity){
		this.uMCity = uMCity;
	}

	public String getUMCity(){
		return uMCity;
	}

	public void setUMCanDrop(String uMCanDrop){
		this.uMCanDrop = uMCanDrop;
	}

	public String getUMCanDrop(){
		return uMCanDrop;
	}

	public void setUMGender(String uMGender){
		this.uMGender = uMGender;
	}

	public String getUMGender(){
		return uMGender;
	}

	public void setUMCanUpdate(String uMCanUpdate){
		this.uMCanUpdate = uMCanUpdate;
	}

	public String getUMCanUpdate(){
		return uMCanUpdate;
	}

	public void setUserCategoryModifiedBy(String userCategoryModifiedBy){
		this.userCategoryModifiedBy = userCategoryModifiedBy;
	}

	public String getUserCategoryModifiedBy(){
		return userCategoryModifiedBy;
	}

	public void setUMState(String uMState){
		this.uMState = uMState;
	}

	public String getUMState(){
		return uMState;
	}

	public void setUMEmailID(String uMEmailID){
		this.uMEmailID = uMEmailID;
	}

	public String getUMEmailID(){
		return uMEmailID;
	}

	public void setUMCanDelete(String uMCanDelete){
		this.uMCanDelete = uMCanDelete;
	}

	public String getUMCanDelete(){
		return uMCanDelete;
	}

	public void setUMID(String uMID){
		this.uMID = uMID;
	}

	public String getUMID(){
		return uMID;
	}

	public void setUMUserStatus(String uMUserStatus){
		this.uMUserStatus = uMUserStatus;
	}

	public String getUMUserStatus(){
		return uMUserStatus;
	}

	public void setUserCategorySubName(String userCategorySubName){
		this.userCategorySubName = userCategorySubName;
	}

	public String getUserCategorySubName(){
		return userCategorySubName;
	}

	public void setUserCategoryModifiedDateTime(String userCategoryModifiedDateTime){
		this.userCategoryModifiedDateTime = userCategoryModifiedDateTime;
	}

	public String getUserCategoryModifiedDateTime(){
		return userCategoryModifiedDateTime;
	}

	public void setUMCanSelect(String uMCanSelect){
		this.uMCanSelect = uMCanSelect;
	}

	public String getUMCanSelect(){
		return uMCanSelect;
	}

	public void setUserCategoryDeletedDateTime(String userCategoryDeletedDateTime){
		this.userCategoryDeletedDateTime = userCategoryDeletedDateTime;
	}

	public String getUserCategoryDeletedDateTime(){
		return userCategoryDeletedDateTime;
	}

	@Override
 	public String toString(){
		return 
			"UserinfoItem{" + 
			"uM_DateOfBirth = '" + uMDateOfBirth + '\'' + 
			",uM_Name = '" + uMName + '\'' + 
			",uM_MobileNo = '" + uMMobileNo + '\'' + 
			",user_Category_AddedBy = '" + userCategoryAddedBy + '\'' + 
			",uM_Password = '" + uMPassword + '\'' + 
			",uM_Modified_DateTime = '" + uMModifiedDateTime + '\'' + 
			",uM_Category_FK = '" + uMCategoryFK + '\'' + 
			",user_Category_IsActive = '" + userCategoryIsActive + '\'' + 
			",user_Category_DeletedBy = '" + userCategoryDeletedBy + '\'' + 
			",user_Category_Name = '" + userCategoryName + '\'' + 
			",user_Category_Added_DateTime = '" + userCategoryAddedDateTime + '\'' + 
			",uM_Country = '" + uMCountry + '\'' + 
			",uM_Type = '" + uMType + '\'' + 
			",uM_Added_DateTime = '" + uMAddedDateTime + '\'' + 
			",uM_Deleted_DateTime = '" + uMDeletedDateTime + '\'' + 
			",uM_ModifiedBy = '" + uMModifiedBy + '\'' + 
			",uM_DeletedBy = '" + uMDeletedBy + '\'' + 
			",user_Category_ID = '" + userCategoryID + '\'' + 
			",uM_AddedBy = '" + uMAddedBy + '\'' + 
			",uM_City = '" + uMCity + '\'' + 
			",uM_CanDrop = '" + uMCanDrop + '\'' + 
			",uM_Gender = '" + uMGender + '\'' + 
			",uM_CanUpdate = '" + uMCanUpdate + '\'' + 
			",user_Category_ModifiedBy = '" + userCategoryModifiedBy + '\'' + 
			",uM_State = '" + uMState + '\'' + 
			",uM_EmailID = '" + uMEmailID + '\'' + 
			",uM_CanDelete = '" + uMCanDelete + '\'' + 
			",uM_ID = '" + uMID + '\'' + 
			",uM_User_Status = '" + uMUserStatus + '\'' + 
			",user_Category_Sub_Name = '" + userCategorySubName + '\'' + 
			",user_Category_Modified_DateTime = '" + userCategoryModifiedDateTime + '\'' + 
			",uM_CanSelect = '" + uMCanSelect + '\'' + 
			",user_Category_Deleted_DateTime = '" + userCategoryDeletedDateTime + '\'' + 
			"}";
		}

}