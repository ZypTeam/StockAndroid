package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.RegularUtils;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

import rx.Subscription;

/**
 * @author wangcc
 * @date 2018/3/22
 * @describe 获取 验证码
 */

public class GetPhoneCodeActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView phone;
    protected EditText inputPhone;
    protected TextView getCode;

    protected String phoneNum;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_get_phone_code;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        phone = (TextView) findViewById(R.id.phone);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        getCode = (TextView) findViewById(R.id.get_code);

    }

    @Override
    public void initAction() {
        titleView.setTitle("注册");

        inputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("edit",s.toString());
                phoneNum=s.toString();
                if (phone.length()>0&& RegularUtils.checkPhone(phoneNum)){
                    getCode.setEnabled(true);
                }else {
                    getCode.setEnabled(false);
                }
            }
        });

        getCode.setOnClickListener(v -> {
            getCode();
        });

        inputPhone.setFocusable(true);
        inputPhone.requestFocus();
    }

    private void getCode(){
        UiUtils.goAuthPhone(phoneNum);
    }
}
