package com.yiyoupin.stock.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.base.BaseActivity;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author wangcc
 * @date 2018/3/31
 * @describe
 */

public class ChangePassActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView lastPass;
    protected EditText inputLastPass;
    protected TextView password;
    protected EditText inputPassword;
    protected TextView comfir;
    protected EditText inputComfir;
    protected TextView submit;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_change_pass;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        lastPass = (TextView) findViewById(R.id.last_pass);
        inputLastPass = (EditText) findViewById(R.id.input_last_pass);
        password = (TextView) findViewById(R.id.password);
        inputPassword = (EditText) findViewById(R.id.input_password);
        comfir = (TextView) findViewById(R.id.comfir);
        inputComfir = (EditText) findViewById(R.id.input_comfir);
        submit = (TextView) findViewById(R.id.submit);

        submit.setEnabled(true);
    }

    @Override
    public void initAction() {
        titleView.setTitle("修改密码");

        inputLastPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.equals(inputComfir.getText().toString(),inputPassword.toString())
                        &&!StringUtil.isEmpty(lastPass.getText().toString())){
                    submit.setEnabled(true);
                }
            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.equals(inputComfir.getText().toString(),inputPassword.toString())
                        &&!StringUtil.isEmpty(lastPass.getText().toString())){
                    submit.setEnabled(true);
                }
            }
        });

        inputComfir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.equals(inputComfir.getText().toString(),inputPassword.toString())
                        &&!StringUtil.isEmpty(lastPass.getText().toString())){
                    submit.setEnabled(true);
                }
            }
        });

        submit.setOnClickListener(v -> {
            submit();
        });
    }

    private void submit(){
        if (StringUtil.isEmpty(inputPassword.getText().toString())
                ||inputPassword.getText().toString().length()<6){
            Toast.makeText(mContext,"密码长度必须大于6",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StringUtil.equals(inputComfir.getText().toString(),inputPassword.getText().toString())){
            Toast.makeText(mContext,"两次输入的密码不匹配",Toast.LENGTH_SHORT).show();
            return;
        }

        UiUtils.goAuthChangePass(inputPassword.getText().toString(),mContext);
    }
}
