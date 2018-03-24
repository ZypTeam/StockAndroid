package com.yiyoupin.stock;

import android.os.Build;

import com.jusfoun.baselibrary.BaseApplication;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2015:06
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class StockApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SharePrefenceUtils.getInstance().register(this, BuildConfig.APPLICATION_ID);
    }
}
