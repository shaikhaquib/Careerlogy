package com.mmo.careerlogy.Network;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mmo.careerlogy.Models.UserinfoItem;

@Database(
        entities = {UserinfoItem.class},
        version = 1,
        exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract DBAccess dbAccess();
}