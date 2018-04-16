package com.yiyoupin.stock;

import android.os.Build;

import com.jusfoun.baselibrary.BaseApplication;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.delegate.HeaderStockInterceptor;

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
        mApp=this;
        SharePrefenceUtils.getInstance().register(this, BuildConfig.APPLICATION_ID);
        Api.getInstance().register(this,getString(R.string.url))
                .addInterceptro(new HeaderStockInterceptor())
                .build();
    }
}
