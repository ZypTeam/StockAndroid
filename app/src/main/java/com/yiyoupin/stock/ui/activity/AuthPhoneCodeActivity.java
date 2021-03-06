package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangcc
 * @date 2018/3/22
 * @describe
 */

public class AuthPhoneCodeActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView phone,timerTxt;
    protected TextView code;
    protected View line;
    protected EditText inputPhone;
    protected TextView next;
    private Subscription timer;
    private String phoneNum;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_auth_phone_code;
    }

    @Override
    public void initDatas() {
        phoneNum = getIntent().getExtras().getString(UiUtils.PHONE);
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
        code = (TextView) findViewById(R.id.code);
        line = (View) findViewById(R.id.line);
        timerTxt = (TextView) findViewById(R.id.timer);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        next = (TextView) findViewById(R.id.next);

    }

    @Override
    public void initAction() {
        titleView.setTitle("注册");
        phone.setText(phoneNum);
        timer = Observable.interval(1, TimeUnit.SECONDS)
                .take(120)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return 120 - aLong;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onCompleted() {
                        timerTxt.setEnabled(true);
                        timerTxt.setText("重发");
                    }

                    @Override
                    public void onError(Throwable e) {
                        timerTxt.setEnabled(true);
                        timerTxt.setText("重发");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        timerTxt.setEnabled(false);
                        timerTxt.setText("("+aLong+")重发");
                    }
                });

        inputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==6){
                    next.setEnabled(true);
                }else {
                    next.setEnabled(false);
                }
            }
        });

        next.setOnClickListener(v -> {
            UiUtils.goRegister(phoneNum,inputPhone.getText().toString(),mContext);
        });

        inputPhone.setFocusable(true);
        inputPhone.requestFocus();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && timer.isUnsubscribed()) {
            timer.unsubscribe();
        }
    }
}
