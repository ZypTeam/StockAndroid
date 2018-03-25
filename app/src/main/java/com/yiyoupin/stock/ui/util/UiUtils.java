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
    public static final void goAuthPhone(Context mContext,String phone){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        goActivity(mContext,bundle, AuthPhoneCodeActivity.class);
    }

    public static final void goRegisterName(Context mContext,UserModel userModel){
        Bundle bundle=new Bundle();
        bundle.putSerializable(USER_INFO,userModel);
        goActivity(mContext,bundle, RegisterNameActivity.class);
    }

    public static final void goLoginActivity(Context mContext){
        goActivity(mContext,null, LoginActivity.class);
    }

    public static final void goHomeActivity(Context mContext){
        goActivity(mContext,null, MainActivity.class);
    }

    public static final void goForgetPass(Context mContext){
        goActivity(mContext,null, ForgetPassActivity.class);
    }

    public static final void goUpdatePass(Context mContext,String phone){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        goActivity(mContext,bundle, UpdatePassActivity.class);
    }

    public static final void goSearch(Context mContext,String word){
        Bundle bundle=new Bundle();
        bundle.putString(WORD,word);
        goActivity(mContext,bundle, SearchActivity.class);
    }

    public static final void goNewsPaparList(Context mContext){
        goActivity(mContext,null, NewspaperListActivity.class);
    }

    public static final void goCharrtsList(Context mContext){
        goActivity(mContext,null, ChartsListActivity.class);
    }

    public static final void goNoticeList(Context mContext){
        goActivity(mContext,null, NewspaperListActivity.class);
    }

    public static final void goReplayList(Context mContext){
        goActivity(mContext,null, ReplayListActivity.class);
    }
    /**
     * 跳转界面
     * @param bundle 传递数据，为NULL不传递
     * @param cls 跳转的界面
     */
    public static void goActivity(Context mContext,Bundle bundle, Class<?> cls){
//        Context context=StockApplication.getBaseApplication();
        Intent intent=new Intent();
        intent.setClass(mContext,cls);
        if (bundle!=null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}
