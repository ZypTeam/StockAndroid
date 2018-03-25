package com.yiyoupin.stock.ui.activity;

import android.graphics.Color;
import android.provider.Contacts;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;

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

        forgetPassword.setOnClickListener(v ->{
            UiUtils.goForgetPass(LoginActivity.this);
        });
    }

    private void login(){
//        UserInfoDelegate.getInstance().saveUserInfo(new UserModel());
        UiUtils.goHomeActivity(LoginActivity.this);
        finish();
    }

    public SpannableStringBuilder getText(String txt1, String txt2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(txt1 + txt2);

        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, txt1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#e93030")), txt1.length(), txt1.length() + txt2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
