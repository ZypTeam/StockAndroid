package com.yiyoupin.stock.ui.activity;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class RegisterActivity extends BaseStockActivity {

    protected BackTitleView titleView;
    protected TextView password;
    protected EditText inputPassword;
    protected TextView comfir;
    protected EditText inputComfir;
    protected TextView submit;
    private String phone,code;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initDatas() {
        phone=getIntent().getExtras().getString(UiUtils.PHONE);
        code=getIntent().getExtras().getString(UiUtils.PHONE_CODE);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        password = (TextView) findViewById(R.id.password);
        inputPassword = (EditText) findViewById(R.id.input_password);
        comfir = (TextView) findViewById(R.id.comfir);
        inputComfir = (EditText) findViewById(R.id.input_comfir);
        submit = (TextView) findViewById(R.id.submit);
    }

    @Override
    public void initAction() {
        titleView.setTitle("注册");

        submit.setOnClickListener(v -> {
            register();
        });
    }

    private void register(){

        if (StringUtil.isEmpty(inputPassword.getText().toString())
                ||inputPassword.getText().toString().length()<6){
            Toast.makeText(mContext,"密码长度必须大于6",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StringUtil.equals(inputComfir.getText().toString(),inputPassword.getText().toString())){
            Toast.makeText(mContext,"两次输入的密码不匹配",Toast.LENGTH_SHORT).show();
            return;
        }

        showLoadDialog();
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",phone);
        params.put("phone_validate_code",code);
        params.put("password",inputPassword.getText().toString());
        addNetwork(Api.getInstance().getService(ApiService.class).registered(params)
                , new Action1<UserDataModel>() {
                    @Override
                    public void call(UserDataModel userDataModel) {
                        hideLoadDialog();
                        if (userDataModel.getCode()==0) {
                            showToast("注册成功");
                            UserInfoDelegate.getInstance().saveUserInfo(userDataModel.getData());
                            UiUtils.goRegisterName(mContext,userDataModel.getData());
                            onBackPressed();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        showToast("注册失败");
                    }
                });
    }
}
