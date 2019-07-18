package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String date2string(Date date , String path){
        if (date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(path);
            return sdf.format(date);
        }
        return  null;
    }

    public static Date string2date(String str ,String path) throws ParseException {
         Date date=null;
         if (str!=null){
             SimpleDateFormat sdf = new SimpleDateFormat(path);
             date = sdf.parse(str);
         }
         return date;
    }
}
