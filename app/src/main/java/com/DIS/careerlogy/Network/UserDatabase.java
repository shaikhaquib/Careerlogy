package com.DIS.careerlogy.Network;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.DIS.careerlogy.Models.UserinfoItem;

@Database(
        entities = {UserinfoItem.class},
        version = 1,
        exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract DBAccess dbAccess();
}