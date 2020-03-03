package com.mmo.careerlogy.Extra;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants
{


    public static final String DATABASE_NAME = "UserDB";
    public static void Alert(Context context,String message) {
         new MaterialAlertDialogBuilder(context)
                 .setTitle("Alert")
                 .setMessage(message)
                 .setPositiveButton("Ok",null)
                 .show();

    }

    public static String longToDate(String format,Long rawDate){
        Date date=new Date(rawDate);
        SimpleDateFormat df2 = new SimpleDateFormat(format);
        return df2.format(date);
    }

}
