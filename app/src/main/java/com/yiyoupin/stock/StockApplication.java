package com.yiyoupin.stock;

import android.os.Build;

import com.jusfoun.baselibrary.BaseApplication;
import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;
import com.jusfoun.baselibrary.net.Api;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yiyoupin.stock.delegate.HeaderStockInterceptor;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2015:06
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class StockApplication extends BaseApplication {

    //各个平台的配置
    {
        PlatformConfig.setWeixin("wx6acb4c4141bd83a0", "5a540845a5a55a0740823ef4fe6616be");
        PlatformConfig.setSinaWeibo("1701976759", "c9f6b6d5015055964780e0c56c3e59a5", "http:www.sharesdk.cn");
        PlatformConfig.setQQZone("1106778129", "KEYufINAIzABZazJo6b");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
        UMShareAPI.get(this);
        LogUtil.setDebugable(BuildConfig.LOG_MODE);

        Config.DEBUG = BuildConfig.LOG_MODE;
        SharePrefenceUtils.getInstance().register(this, BuildConfig.APPLICATION_ID);
        Api.getInstance().register(this,getString(R.string.url))
                .addInterceptro(new HeaderStockInterceptor())
                .build();
    }
}
