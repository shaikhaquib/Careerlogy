package com.mmo.careerlogy.Network;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class UserDB {
    private String uMDateOfBirth;
    private String uMName;
    private String uMMobileNo;
    private String userCategoryAddedBy;
    private String uMPassword;
    private String uMModifiedDateTime;
    private String uMCategoryFK;
    private String userCategoryIsActive;
    private String userCategoryDeletedBy;
    private String userCategoryName;
    private String userCategoryAddedDateTime;
    private String uMCountry;
    private String uMType;
    private String uMAddedDateTime;
    private String uMDeletedDateTime;
    private String uMModifiedBy;
    private String uMDeletedBy;
    private String userCategoryID;
    private String uMAddedBy;
    private String uMCity;
    private String uMCanDrop;
    private String uMGender;
    private String uMCanUpdate;
    private String userCategoryModifiedBy;
    private String uMState;
    private String uMEmailID;
    private String uMCanDelete;
    @NonNull
    @PrimaryKey
    private String uMID;
    private String uMUserStatus;
    private String userCategorySubName;
    private String userCategoryModifiedDateTime;
    private String uMCanSelect;

    public String getuMDateOfBirth() {
        return uMDateOfBirth;
    }

    public void setuMDateOfBirth(String uMDateOfBirth) {
        this.uMDateOfBirth = uMDateOfBirth;
    }

    public String getuMName() {
        return uMName;
    }

    public void setuMName(String uMName) {
        this.uMName = uMName;
    }

    public String getuMMobileNo() {
        return uMMobileNo;
    }

    public void setuMMobileNo(String uMMobileNo) {
        this.uMMobileNo = uMMobileNo;
    }

    public String getUserCategoryAddedBy() {
        return userCategoryAddedBy;
    }

    public void setUserCategoryAddedBy(String userCategoryAddedBy) {
        this.userCategoryAddedBy = userCategoryAddedBy;
    }

    public String getuMPassword() {
        return uMPassword;
    }

    public void setuMPassword(String uMPassword) {
        this.uMPassword = uMPassword;
    }

    public String getuMModifiedDateTime() {
        return uMModifiedDateTime;
    }

    public void setuMModifiedDateTime(String uMModifiedDateTime) {
        this.uMModifiedDateTime = uMModifiedDateTime;
    }

    public String getuMCategoryFK() {
        return uMCategoryFK;
    }

    public void setuMCategoryFK(String uMCategoryFK) {
        this.uMCategoryFK = uMCategoryFK;
    }

    public String getUserCategoryIsActive() {
        return userCategoryIsActive;
    }

    public void setUserCategoryIsActive(String userCategoryIsActive) {
        this.userCategoryIsActive = userCategoryIsActive;
    }

    public String getUserCategoryDeletedBy() {
        return userCategoryDeletedBy;
    }

    public void setUserCategoryDeletedBy(String userCategoryDeletedBy) {
        this.userCategoryDeletedBy = userCategoryDeletedBy;
    }

    public String getUserCategoryName() {
        return userCategoryName;
    }

    public void setUserCategoryName(String userCategoryName) {
        this.userCategoryName = userCategoryName;
    }

    public String getUserCategoryAddedDateTime() {
        return userCategoryAddedDateTime;
    }

    public void setUserCategoryAddedDateTime(String userCategoryAddedDateTime) {
        this.userCategoryAddedDateTime = userCategoryAddedDateTime;
    }

    public String getuMCountry() {
        return uMCountry;
    }

    public void setuMCountry(String uMCountry) {
        this.uMCountry = uMCountry;
    }

    public String getuMType() {
        return uMType;
    }

    public void setuMType(String uMType) {
        this.uMType = uMType;
    }

    public String getuMAddedDateTime() {
        return uMAddedDateTime;
    }

    public void setuMAddedDateTime(String uMAddedDateTime) {
        this.uMAddedDateTime = uMAddedDateTime;
    }

    public String getuMDeletedDateTime() {
        return uMDeletedDateTime;
    }

    public void setuMDeletedDateTime(String uMDeletedDateTime) {
        this.uMDeletedDateTime = uMDeletedDateTime;
    }

    public String getuMModifiedBy() {
        return uMModifiedBy;
    }

    public void setuMModifiedBy(String uMModifiedBy) {
        this.uMModifiedBy = uMModifiedBy;
    }

    public String getuMDeletedBy() {
        return uMDeletedBy;
    }

    public void setuMDeletedBy(String uMDeletedBy) {
        this.uMDeletedBy = uMDeletedBy;
    }

    public String getUserCategoryID() {
        return userCategoryID;
    }

    public void setUserCategoryID(String userCategoryID) {
        this.userCategoryID = userCategoryID;
    }

    public String getuMAddedBy() {
        return uMAddedBy;
    }

    public void setuMAddedBy(String uMAddedBy) {
        this.uMAddedBy = uMAddedBy;
    }

    public String getuMCity() {
        return uMCity;
    }

    public void setuMCity(String uMCity) {
        this.uMCity = uMCity;
    }

    public String getuMCanDrop() {
        return uMCanDrop;
    }

    public void setuMCanDrop(String uMCanDrop) {
        this.uMCanDrop = uMCanDrop;
    }

    public String getuMGender() {
        return uMGender;
    }

    public void setuMGender(String uMGender) {
        this.uMGender = uMGender;
    }

    public String getuMCanUpdate() {
        return uMCanUpdate;
    }

    public void setuMCanUpdate(String uMCanUpdate) {
        this.uMCanUpdate = uMCanUpdate;
    }

    public String getUserCategoryModifiedBy() {
        return userCategoryModifiedBy;
    }

    public void setUserCategoryModifiedBy(String userCategoryModifiedBy) {
        this.userCategoryModifiedBy = userCategoryModifiedBy;
    }

    public String getuMState() {
        return uMState;
    }

    public void setuMState(String uMState) {
        this.uMState = uMState;
    }

    public String getuMEmailID() {
        return uMEmailID;
    }

    public void setuMEmailID(String uMEmailID) {
        this.uMEmailID = uMEmailID;
    }

    public String getuMCanDelete() {
        return uMCanDelete;
    }

    public void setuMCanDelete(String uMCanDelete) {
        this.uMCanDelete = uMCanDelete;
    }

    @NonNull
    public String getuMID() {
        return uMID;
    }

    public void setuMID(@NonNull String uMID) {
        this.uMID = uMID;
    }

    public String getuMUserStatus() {
        return uMUserStatus;
    }

    public void setuMUserStatus(String uMUserStatus) {
        this.uMUserStatus = uMUserStatus;
    }

    public String getUserCategorySubName() {
        return userCategorySubName;
    }

    public void setUserCategorySubName(String userCategorySubName) {
        this.userCategorySubName = userCategorySubName;
    }

    public String getUserCategoryModifiedDateTime() {
        return userCategoryModifiedDateTime;
    }

    public void setUserCategoryModifiedDateTime(String userCategoryModifiedDateTime) {
        this.userCategoryModifiedDateTime = userCategoryModifiedDateTime;
    }

    public String getuMCanSelect() {
        return uMCanSelect;
    }

    public void setuMCanSelect(String uMCanSelect) {
        this.uMCanSelect = uMCanSelect;
    }
}
