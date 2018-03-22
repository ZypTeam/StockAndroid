package com.yiyoupin.stock.ui.activity;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;

/**
 * @author wangcc
 * @date 2018/3/21
 * @describe 登录
 */

public class LoginActivity extends BaseStockActivity {
    protected TextView register;

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

    }

    @Override
    public void initAction() {
        register.setText(getText("还没有账号？","立即注册"));
        register.setOnClickListener(v -> {
            goActivity(null,GetPhoneCodeActivity.class);
        });
    }

    public SpannableStringBuilder getText(String txt1, String txt2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(txt1 + txt2);

        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, txt1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#e93030")), txt1.length(), txt1.length() + txt2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
