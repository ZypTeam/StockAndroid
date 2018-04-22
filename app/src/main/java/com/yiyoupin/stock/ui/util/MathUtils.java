package com.yiyoupin.stock.ui.util;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * @author wangcc
 * @date 2018/4/21
 * @describe
 */
public class MathUtils {

    public static String getRound(String string){
        if (TextUtils.isEmpty(string)){
            return "";
        }

        try{
            Double db=Double.parseDouble(string);
            BigDecimal bigDecimal=new BigDecimal(db/10000);
            return String.valueOf(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())+"万元";
        }catch (Exception e){
            return "";
        }
    }
}
