package com.yiyoupin.stock.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.activity.EditPersonInfoActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${我的}
 */
public class MyFrgament extends BaseStockFragment {

    private ImageView iconHead;
    private TextView editPersion;
    public static MyFrgament getInstance() {
        MyFrgament fragment = new MyFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView(View rootView) {
        iconHead=rootView.findViewById(R.id.icon_head);
        editPersion=rootView.findViewById(R.id.edit_info);
    }

    @Override
    public void initAction() {
        iconHead.setOnClickListener(v -> {
            goActivity(null, LoginActivity.class);
        });

        editPersion.setOnClickListener(v->{
            goActivity(null, EditPersonInfoActivity.class);
        });
    }
}
