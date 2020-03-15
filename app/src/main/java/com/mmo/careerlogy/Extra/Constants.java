package com.mmo.careerlogy.Extra;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.ParseException;
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
    public static String Date(String time)  {
        if (time != null) {
            String df = "yyyy-MM-dd hh:mm:ss";
            String resultFormat = "dd/MM/yyyy";
            Date date = null;
            try {
                date = new SimpleDateFormat(df).parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new SimpleDateFormat("dd MMM yyyy").format(date);
        }else {
            return "unknown";
        }
    }

    public static String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}
