package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.FlipListener;
import com.yiyoupin.stock.comment.ViewLoadingListener;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.ui.activity.GetPhoneCodeActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.util.UiUtils;

import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author wangcc
 * @date 2018/4/15
 * @describe
 */
public class LoginView extends ConstraintLayout {

    private CompositeSubscription mCompositeSubscription=new CompositeSubscription();

    protected TextView register;
    protected TextView forgetPassword;
    protected TextView login;
    protected EditText password;
    protected EditText phone;
    private Context mContext;

    public LoginView(@NonNull Context context) {
        this(context, null);
    }

    public LoginView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAction();
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.activity_login, this);
        register = (TextView) findViewById(R.id.register);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        login = (TextView) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);

    }

    private void initAction() {
        register.setText(getText("还没有账号？", "立即注册"));
        register.setOnClickListener(v -> {
            UiUtils.goPhoneCodeActivity(mContext);
        });

        login.setOnClickListener(v -> {
            login();
        });

        forgetPassword.setOnClickListener(v -> {
            UiUtils.goForgetPass(mContext);
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
        if (loadingListener!=null){
            loadingListener.showLoading();
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone.getText().toString());
        params.put("password", password.getText().toString());
        addNetwork(Api.getInstance().getService(ApiService.class).login(params)
                , new Action1<UserDataModel>() {
                    @Override
                    public void call(UserDataModel userDataModel) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        if (userDataModel.getCode() == 0) {
                            UserInfoDelegate.getInstance().saveUserInfo(userDataModel.getData());
                            Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
                            if (flipListener!=null){
                                flipListener.flip(true);
                            }

                        }else {
                            Toast.makeText(mContext,userDataModel.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        Toast.makeText(mContext,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void showToast(String text){
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(mContext,text, Toast.LENGTH_SHORT).show();
    }
    public void showToast(int stringId){
        Toast.makeText(mContext,mContext.getString(stringId), Toast.LENGTH_SHORT).show();
    }

    public SpannableStringBuilder getText(String txt1, String txt2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(txt1 + txt2);

        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, txt1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#e93030")), txt1.length(), txt1.length() + txt2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public <T extends BaseModel> void addNetwork(rx.Observable<T> observable, Action1<T> next, Action1<Throwable> error){
        mCompositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(next, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                LogUtil.e("throwable",throwable.getMessage());
                                if (error!=null) {
                                    error.call(throwable);
                                }
                            }
                        })
        );
    }

    @Override
    protected void onDetachedFromWindow() {
        mCompositeSubscription.clear();
        super.onDetachedFromWindow();
    }

    private ViewLoadingListener loadingListener;

    public void setLoadingListener(ViewLoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    private FlipListener flipListener;

    public void setFlipListener(FlipListener flipListener) {
        this.flipListener = flipListener;
    }
}
