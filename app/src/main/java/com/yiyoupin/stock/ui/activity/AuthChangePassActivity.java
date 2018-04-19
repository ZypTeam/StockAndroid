package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
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
    private String password;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_auth_change_pass;
    }

    @Override
    public void initDatas() {

        password=getIntent().getExtras().getString(UiUtils.PASSWORD);
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
            getCode();
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
                if (RegularUtils.checkMobile(s.toString())&&inputCode.getText().length()==6){
                    submit.setEnabled(true);
                }else {
                    submit.setEnabled(false);
                }
            }
        });

        inputCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==6&&RegularUtils.checkMobile(inputPhone.getText().toString())){
                    submit.setEnabled(true);
                }else {
                    submit.setEnabled(false);
                }
            }
        });

        submit.setOnClickListener(v -> {
            auth();
        });
    }

    private void getCode(){
        if (!RegularUtils.checkMobile(inputPhone.getText().toString())){
            showToast("手机号不正确");
            return;
        }
        showLoadDialog();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",inputPhone.getText().toString());
        params.put("type","3");
        addNetwork(Api.getInstance().getService(ApiService.class).getPhoneCode(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {
                        hideLoadDialog();
                        if (noDataModel.getCode()==0) {
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

    private void auth(){
        showLoadDialog();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",inputPhone.getText().toString());
        params.put("",inputCode.getText().toString());
        params.put("password",password);
        addNetwork(Api.getInstance().getService(ApiService.class).bindPhone(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {

                        hideLoadDialog();
                        if (noDataModel.getCode()==0){
                            showToast("绑定手机号成功");
                            UiUtils.goHomeActivity(mContext);
                            return;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        hideLoadDialog();
                        showToast("绑定手机号失败");
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
