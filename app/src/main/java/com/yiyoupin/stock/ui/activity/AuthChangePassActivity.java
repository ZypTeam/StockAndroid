package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangcc
 * @date 2018/3/31
 * @describe
 */

public class AuthChangePassActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView code;
    protected ImageView image;
    protected EditText inputCode;
    protected TextView phone;
    protected EditText inputPhone;
    protected TextView phoneCode;
    protected TextView timerTxt;
    protected EditText inputPhoneCode;
    protected TextView submit;
    private Subscription timer;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_auth_change_pass;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        code = (TextView) findViewById(R.id.code);
        image = (ImageView) findViewById(R.id.image);
        inputCode = (EditText) findViewById(R.id.input_code);
        phone = (TextView) findViewById(R.id.phone);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        phoneCode = (TextView) findViewById(R.id.phone_code);
        timerTxt = (TextView) findViewById(R.id.timer);
        inputPhoneCode = (EditText) findViewById(R.id.input_phone_code);
        submit = (TextView) findViewById(R.id.submit);

    }

    @Override
    public void initAction() {
        titleView.setTitle("修改密码验证");
        submit.setOnClickListener(v -> {
            onBackPressed();
        });

        timerTxt.setOnClickListener(v ->{
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
                            timerTxt.setText(aLong+"s");
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
                if (s.length()>0){
                    submit.setEnabled(true);
                }
            }
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