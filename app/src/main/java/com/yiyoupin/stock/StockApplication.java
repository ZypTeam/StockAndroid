package com.yiyoupin.stock;

import android.os.Build;

import com.jusfoun.baselibrary.BaseApplication;
import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;
import com.jusfoun.baselibrary.net.Api;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.delegate.HeaderStockInterceptor;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2015:06
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class StockApplication extends BaseApplication {

    /**
     *     0， 正常启动  1,全新安装 2, 覆盖安装
     */
    private static int firstInstall = Constant.INSTALL_TYPE_NORMAL;

    //各个平台的配置
    {
        PlatformConfig.setWeixin("wx6acb4c4141bd83a0", "5a540845a5a55a0740823ef4fe6616be");
        PlatformConfig.setSinaWeibo("1701976759", "c9f6b6d5015055964780e0c56c3e59a5", "http:www.sharesdk.cn");
        PlatformConfig.setQQZone("1106778129", "KEYufINAIzABZazJo6b");
    }

    private String MI_APP_ID ="2882303761517775739";
    private String MI_APP_KEY="5371777555739";


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

        //如果当前App版本号，大于存储的版号且>0，认为是覆盖安装的，等于-1是新安装的; 相等是正常启动
        int currBuild = SharePrefenceUtils.getInstance().getInt(Constant.PREFERENCE_LAST_APP_BUILD, -1);
        if (BuildConfig.VERSION_CODE == currBuild) {
            setFirstInstall(Constant.INSTALL_TYPE_NORMAL);
        } else if (BuildConfig.VERSION_CODE > currBuild && currBuild > 0) {
            setFirstInstall(Constant.INSTALL_TYPE_UPDATE);
            SharePrefenceUtils.getInstance().setInt(Constant.PREFERENCE_LAST_APP_BUILD, BuildConfig.VERSION_CODE);
        } else if (-1 == currBuild) {
            setFirstInstall(Constant.INSTALL_TYPE_NEW);
            SharePrefenceUtils.getInstance().setInt(Constant.PREFERENCE_LAST_APP_BUILD, BuildConfig.VERSION_CODE);
        }

        MiPushClient.registerPush(this, MI_APP_ID, MI_APP_KEY);
    }

    private void setFirstInstall(int value){
        firstInstall=value;
    }

    public static int isFirstInstall(){
        return firstInstall;
    }
}
