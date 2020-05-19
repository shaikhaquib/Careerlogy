package com.DIS.careerlogy.Network;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.DIS.careerlogy.Models.UserinfoItem;

import java.util.List;

@Dao
public interface DBAccess {

    @Insert
    void insertUser(UserinfoItem userinfoItem);

   /* @Insert
    @Query("SELECT * FROM UserinfoItem WHERE uMID = :uMID")
    UserinfoItem user(int uMID);*/

    @Query("Select * From UserinfoItem")
    List<UserinfoItem> getUserDetail();

    @Delete
    void deleteUser(UserinfoItem user);


}
