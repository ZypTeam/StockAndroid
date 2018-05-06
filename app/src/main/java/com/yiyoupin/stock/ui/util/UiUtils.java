package com.yiyoupin.stock.ui.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.activity.AboutUsActivity;
import com.yiyoupin.stock.ui.activity.AuthChangePassActivity;
import com.yiyoupin.stock.ui.activity.AuthPhoneCodeActivity;
import com.yiyoupin.stock.ui.activity.BayVipActivity;
import com.yiyoupin.stock.ui.activity.ChangePassActivity;
import com.yiyoupin.stock.ui.activity.ChartsDetailActivity;
import com.yiyoupin.stock.ui.activity.ChartsListActivity;
import com.yiyoupin.stock.ui.activity.EditPersonInfoActivity;
import com.yiyoupin.stock.ui.activity.ForgetPassActivity;
import com.yiyoupin.stock.ui.activity.GetPhoneCodeActivity;
import com.yiyoupin.stock.ui.activity.GuideActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.activity.MainActivity;
import com.yiyoupin.stock.ui.activity.MsgListActivity;
import com.yiyoupin.stock.ui.activity.NewspaperListActivity;
import com.yiyoupin.stock.ui.activity.NoticeListActivity;
import com.yiyoupin.stock.ui.activity.PayListActivity;
import com.yiyoupin.stock.ui.activity.RegisterActivity;
import com.yiyoupin.stock.ui.activity.RegisterNameActivity;
import com.yiyoupin.stock.ui.activity.RemindActivity;
import com.yiyoupin.stock.ui.activity.ReplayListActivity;
import com.yiyoupin.stock.ui.activity.SearchActivity;
import com.yiyoupin.stock.ui.activity.SearchResultActivity;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.activity.StrategiesDetailActivity;
import com.yiyoupin.stock.ui.activity.UpdatePassActivity;
import com.yiyoupin.stock.ui.activity.WebViewActivity;

import java.net.URI;

/**
 * @author wangcc
 * @date 2018/3/22
 * @describe
 */

public class UiUtils {

    public static final String PHONE="phone";
    public static final String WORD="word";
    public static final String PHONE_CODE="phone_code";
    public static final String USER_INFO="user_info";
    public static final String TITLE="title";
    public static final String WEB_URL="web_url";
    public static final String PASSWORD="password";
    public static final String DETAIL_ID="detail_id";

    public static final String SEARCH_MODLE="search_modle";
    public static final String SEARCH_KEY="search_key";

    public static final void goSearchResult(Context context, String searchKey){
        Bundle bundle=new Bundle();
        bundle.putString(SEARCH_KEY,searchKey);
        goActivity(context,bundle, SearchResultActivity.class);
    }
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

    public static final void goPhoneCodeActivity(Context mContext){
        goActivity(mContext,null, GetPhoneCodeActivity.class);
    }

    public static final void goHomeActivity(Context mContext){
        goActivity(mContext,null, MainActivity.class);
    }

    public static final void goGuideActivity(Context mContext){
        goActivity(mContext,null, GuideActivity.class);
    }

    public static final void goForgetPass(Context mContext){
        goActivity(mContext,null, ForgetPassActivity.class);
    }

    public static final void goEditInfo(Context mContext){
        goActivity(mContext,null, EditPersonInfoActivity.class);
    }

    public static final void goChangePass(Context mContext){
        goActivity(mContext,null, ChangePassActivity.class);
    }

    public static final void goAuthChangePass(String password,Context mContext){
        Bundle bundle=new Bundle();
        bundle.putString(PASSWORD,password);
        goActivity(mContext,bundle, AuthChangePassActivity.class);
    }

    public static final void goRegister(String phone, String code, Context context){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        bundle.putString(PHONE_CODE,code);
        goActivity(context,bundle, RegisterActivity.class);
    }


    public static final void goUpdatePass(Context mContext,String phone,String code){
        Bundle bundle=new Bundle();
        bundle.putString(PHONE,phone);
        bundle.putString(PHONE_CODE,code);
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
        goActivity(mContext,null, NoticeListActivity.class);
    }

    public static final void goReplayList(Context mContext){
        goActivity(mContext,null, ReplayListActivity.class);
    }

    public static final void goChartsDetails(Context context,String detail_id){
        Bundle bundle=new Bundle();
        bundle.putString(DETAIL_ID,detail_id);
        goActivity(context,bundle,ChartsDetailActivity.class);
    }

    public static final void goAboutUs(Context context){
        goActivity(context,null,AboutUsActivity.class);
    }

    public static final void goPayList(Context context){
        goActivity(context,null,PayListActivity.class);
    }

    public static final void goMsgList(Context context){
        goActivity(context,null,MsgListActivity.class);
    }

    public static final void goWebActivity(Context context,String title,String url){
        Bundle bundle=new Bundle();
        bundle.putString(TITLE,title);
        bundle.putString(WEB_URL,url);
        goActivity(context,bundle, WebViewActivity.class);
    }

    /**
     *  跳转策略详情页面
     * */
    public static final void goStrategiesDetailActivity(Bundle bundle,Context mContext){
        goActivity(mContext,bundle, StrategiesDetailActivity.class);
    }

    /**
     *  跳转提醒
     * */
    public static final void goRemindActivity(Context mContext,Bundle bundle){
        goActivity(mContext,bundle, RemindActivity.class);
    }

    /**
     *  个股页面
     * */
    public static final void goStockShowActivity(Context mContext,Bundle bundle){
        goActivity(mContext,bundle, StockShowActivity.class);
    }

    /**
     *  购买vip
     * */
    public static final void goBuyVip(Context mContext,Bundle bundle){
        goActivity(mContext,bundle, BayVipActivity.class);
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
