package com.yiyoupin.stock.ui.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoyapeng
 * @version create time:18/5/616:17
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class TimeUtil {

    public static String getDateToString(String milSecond, String pattern) {
        try {
            Date date = new Date(Long.parseLong(milSecond));
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }catch (Exception e){

            Log.e("tag","getDateToStringgetDateToString="+e);
            return "";
        }
    }
}
