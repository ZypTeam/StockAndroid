package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;

import rx.Subscription;
import rx.functions.Action1;

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

        rxManage.on(Constant.REGISTER_SUC, new Action1<Object>() {
            @Override
            public void call(Object o) {
                onBackPressed();
            }
        });
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
                phoneNum=s.toString();
                if (phoneNum.length()==11 && RegularUtils.checkMobile(phoneNum)){
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
        showLoadDialog();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",phoneNum);
        params.put("type","2");
        addNetwork(Api.getInstance().getService(ApiService.class).getPhoneCode(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {
                        hideLoadDialog();
                        if (noDataModel.getCode()==0) {
                            UiUtils.goAuthPhone(GetPhoneCodeActivity.this, phoneNum);
//                        onBackPressed();
                            showToast(R.string.code_send);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        showToast("获取失败");
                    }
                });
    }
}
