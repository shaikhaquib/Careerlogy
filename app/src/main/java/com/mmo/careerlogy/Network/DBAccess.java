package com.mmo.careerlogy.Network;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.mmo.careerlogy.Models.UserinfoItem;

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
