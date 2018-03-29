package com.yiyoupin.stock.ui.activity;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.StringUtil;
import com.jusfoun.baselibrary.Util.TouchUtil;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class UpdatePassActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView password;
    protected EditText inputPassword;
    protected TextView comfir;
    protected EditText inputComfir;
    protected TextView submit;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_update_pass;
    }

    @Override
    public void initDatas() {

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
        titleView.setTitle("找回登录密码");

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

        UiUtils.goHomeActivity(UpdatePassActivity.this);
    }
}
