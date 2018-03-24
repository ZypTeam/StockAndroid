package com.yiyoupin.stock.ui.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.activity.AuthPhoneCodeActivity;
import com.yiyoupin.stock.ui.activity.ChartsListActivity;
import com.yiyoupin.stock.ui.activity.ForgetPassActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.activity.MainActivity;
import com.yiyoupin.stock.ui.activity.NewspaperListActivity;
import com.yiyoupin.stock.ui.activity.RegisterNameActivity;
import com.yiyoupin.stock.ui.activity.ReplayListActivity;
import com.yiyoupin.stock.ui.activity.SearchActivity;
import com.yiyoupin.stock.ui.activity.UpdatePassActivity;

/**
 * @author wangcc
 * @date 2018/3/22
 * @describe
 */

public class UiUtils {

    public static final String PHONE="phone";
    public static final String WORD="word";
    public static final String USER_INFO="user_info";
    public static final void goAuthPhone(String phone){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        goActivity(bundle, AuthPhoneCodeActivity.class);
    }

    public static final void goRegisterName(UserModel userModel){
        Bundle bundle=new Bundle();
        bundle.putSerializable(USER_INFO,userModel);
        goActivity(bundle, RegisterNameActivity.class);
    }

    public static final void goLoginActivity(){
        goActivity(null, LoginActivity.class);
    }

    public static final void goHomeActivity(){
        goActivity(null, MainActivity.class);
    }

    public static final void goForgetPass(){
        goActivity(null, ForgetPassActivity.class);
    }

    public static final void goUpdatePass(String phone){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        goActivity(bundle, UpdatePassActivity.class);
    }

    public static final void goSearch(String word){
        Bundle bundle=new Bundle();
        bundle.putString(WORD,word);
        goActivity(bundle, SearchActivity.class);
    }

    public static final void goNewsPaparList(){
        goActivity(null, NewspaperListActivity.class);
    }

    public static final void goCharrtsList(){
        goActivity(null, ChartsListActivity.class);
    }

    public static final void goNoticeList(){
        goActivity(null, NewspaperListActivity.class);
    }

    public static final void goReplayList(){
        goActivity(null, ReplayListActivity.class);
    }
    /**
     * 跳转界面
     * @param bundle 传递数据，为NULL不传递
     * @param cls 跳转的界面
     */
    public static void goActivity(Bundle bundle, Class<?> cls){
        Context context=StockApplication.getBaseApplication();
        Intent intent=new Intent();
        intent.setClass(context,cls);
        if (bundle!=null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
