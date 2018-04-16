package com.yiyoupin.stock.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.RegularUtils;
import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.base.BaseFragment;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.FragmentCallback;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.ui.activity.GetPhoneCodeActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.util.UiUtils;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/4/15
 * @describe
 */
public class LoginFragment extends BaseStockFragment {

    private FragmentCallback callback;
    protected TextView register;
    protected TextView forgetPassword;
    protected TextView login;
    protected EditText password;
    protected EditText phone;

    public static LoginFragment getInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback){
            callback= (FragmentCallback) context;
        }
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView(View rootView) {
        register = (TextView) rootView.findViewById(R.id.register);
        forgetPassword = (TextView) rootView.findViewById(R.id.forget_password);
        login = (TextView) rootView.findViewById(R.id.login);
        password = (EditText) rootView.findViewById(R.id.password);
        phone = (EditText) rootView.findViewById(R.id.phone);
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
                            if (callback!=null){
                                callback.onCallback(LoginFragment.this);
                            }
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
}
