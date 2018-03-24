package com.yiyoupin.stock.ui.activity;

import android.text.TextUtils;

import com.jusfoun.baselibrary.task.WeakHandler;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 启动页
 */

public class SplashActivity extends BaseStockActivity {

    private Runnable task=new Runnable() {
        @Override
        public void run() {
            goNextActivity();
        }
    };
    private WeakHandler handler=new WeakHandler();
    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initAction() {
        handler.postDelayed(task,2000);
    }

    private void goNextActivity(){
        if (UserInfoDelegate.getInstance().getUserInfo()!=null){
//            UiUtils.goHomeActivity();
            UiUtils.goLoginActivity();
        }else {
            UiUtils.goLoginActivity();
        }
        onBackPressed();
    }
}
