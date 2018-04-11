package com.yiyoupin.stock.ui.activity;

import android.graphics.Color;
import android.provider.Contacts;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/21
 * @describe 登录
 */

public class LoginActivity extends BaseStockActivity {
    protected TextView register;
    protected TextView forgetPassword;
    protected TextView login;
    protected EditText password;
    protected EditText phone;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        register = (TextView) findViewById(R.id.register);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        login = (TextView) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
    }

    @Override
    public void initAction() {
        register.setText(getText("还没有账号？", "立即注册"));
        register.setOnClickListener(v -> {
            goActivity(null, GetPhoneCodeActivity.class);
        });

        login.setOnClickListener(v -> {
            login();
        });

        forgetPassword.setOnClickListener(v -> {
            UiUtils.goForgetPass(LoginActivity.this);
        });
    }

    private void login() {
        if (!RegularUtils.checkPhone(phone.getText().toString())) {
            showToast("手机号不正确");
            return;
        }
        if (StringUtil.isEmpty(password.getText().toString())) {
            showToast("密码不能为空");
            return;
        }
        showLoadDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone.getText().toString());
        params.put("password", password.getText().toString());
        addNetwork(Api.getInstance().getService(ApiService.class).login(params)
                , new Action1<UserDataModel>() {
                    @Override
                    public void call(UserDataModel userDataModel) {
                        hideLoadDialog();
                        if (userDataModel.getCode() == 0) {
                            UserInfoDelegate.getInstance().saveUserInfo(userDataModel.getData());
                            UiUtils.goHomeActivity(LoginActivity.this);
                            onBackPressed();
                        } else {
                            showToast(userDataModel.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });


    }

    public SpannableStringBuilder getText(String txt1, String txt2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(txt1 + txt2);

        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, txt1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#e93030")), txt1.length(), txt1.length() + txt2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private long mLastTime;
    /**
     * 退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mLastTime > 0 && System.currentTimeMillis() - mLastTime <= 2000) {
                StockApplication.getBaseApplication().removeAll();
            } else {
                Toast.makeText(mContext, R.string.app_exit_string, Toast.LENGTH_SHORT).show();
                mLastTime = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }
}
