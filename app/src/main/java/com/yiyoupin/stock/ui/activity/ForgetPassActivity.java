package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.RegularUtils;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

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
 * @date 2018/3/24
 * @describe 忘记密码
 */

public class ForgetPassActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView phone;
    protected EditText inputPhone;
    protected TextView code;
    protected EditText inputCode;
    protected TextView next;

    private Subscription timer;

    private boolean phoneOk,codeOk;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_forget_pass;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        phone = (TextView) findViewById(R.id.phone);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        code = (TextView) findViewById(R.id.code);
        inputCode = (EditText) findViewById(R.id.input_code);
        next = (TextView) findViewById(R.id.next);

    }

    @Override
    public void initAction() {

        titleView.setTitle("找回登录密码");

        inputCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==6){
                    codeOk=true;
                }else {
                    codeOk=false;
                }

                if (phoneOk&&codeOk){
                    next.setEnabled(true);
                }else {
                    next.setEnabled(false);
                }
            }
        });

        code.setOnClickListener(v -> {
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
                            code.setEnabled(true);
                            code.setText("重发");
                        }

                        @Override
                        public void onError(Throwable e) {
                            code.setEnabled(true);
                            code.setText("重发");
                        }

                        @Override
                        public void onNext(Long aLong) {
                            code.setEnabled(false);
                            code.setText(aLong+"s");
                        }
                    });
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
                if (s.length()==11&& RegularUtils.checkPhone(s.toString())){
                    phoneOk=true;
                }else {
                    phoneOk=false;
                }

                if (phoneOk&&codeOk){
                    next.setEnabled(true);
                }else {
                    next.setEnabled(false);
                }
            }
        });

        next.setOnClickListener(v -> {
            UiUtils.goUpdatePass(ForgetPassActivity.this,inputPhone.getText().toString());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && timer.isUnsubscribed()) {
            timer.unsubscribe();
        }
    }
}
