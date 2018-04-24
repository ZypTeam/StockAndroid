package com.yiyoupin.stock.ui.activity;

import android.Manifest;
import android.text.TextUtils;

import com.jusfoun.baselibrary.permissiongen.PermissionFail;
import com.jusfoun.baselibrary.permissiongen.PermissionGen;
import com.jusfoun.baselibrary.permissiongen.PermissionSuccess;
import com.jusfoun.baselibrary.task.WeakHandler;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.Constant;
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
        if (PermissionGen.checkPermissions(mContext,new String[]{Manifest.permission.READ_PHONE_STATE})){
            handler.postDelayed(task,2000);
        }else {
            PermissionGen.with(this)
                    .permissions(new String[]{Manifest.permission.READ_PHONE_STATE})
                    .addRequestCode(100)
                    .request();
        }

    }

    @PermissionFail(requestCode = 100)
    public void perFail(){
        showToast("获取权限失败");
    }

    @PermissionSuccess(requestCode = 100)
    public void perSuc(){
        handler.postDelayed(task,2000);
    }


    private void goNextActivity(){
        if (StockApplication.isFirstInstall()!= Constant.INSTALL_TYPE_NORMAL){
            UiUtils.goGuideActivity(this);
        }else {
            UiUtils.goHomeActivity(this);
        }
        onBackPressed();
    }
}
