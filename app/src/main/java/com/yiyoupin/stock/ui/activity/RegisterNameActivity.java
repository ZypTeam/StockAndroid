package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 注册 设置昵称
 */

public class RegisterNameActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected EditText editName;
    protected TextView saveName;

    private String nickName;

    private UserModel userModel;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register_name;
    }

    @Override
    public void initDatas() {
        userModel= (UserModel) getIntent().getExtras().getSerializable(UiUtils.USER_INFO);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        editName = (EditText) findViewById(R.id.edit_name);
        saveName = (TextView) findViewById(R.id.save_name);

    }

    @Override
    public void initAction() {
        titleView.setTitle("注册成功");
        titleView.setRightText("跳过",v -> {
            UserInfoDelegate.getInstance().saveUserInfo(userModel);
            UiUtils.goHomeActivity(RegisterNameActivity.this);
            onBackPressed();
        });
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length()>0){
                    nickName=s.toString();
                    saveName.setEnabled(true);
                }else {
                    saveName.setEnabled(false);
                }
            }
        });

        saveName.setOnClickListener( v -> {
            updateName(nickName);
        });

        editName.setFocusable(true);
        editName.requestFocus();
    }

    private void updateName(String name){
        UserInfoDelegate.getInstance().saveUserInfo(userModel);
        UiUtils.goHomeActivity(RegisterNameActivity.this);
        onBackPressed();
    }
}
