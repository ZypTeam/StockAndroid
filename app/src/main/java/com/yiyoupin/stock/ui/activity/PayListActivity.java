package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.PayModel;
import com.yiyoupin.stock.ui.adapter.PayAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class PayListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected RecyclerView list;
    private PayAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_pay_list;
    }

    @Override
    public void initDatas() {
        adapter=new PayAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (RecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {
        titleView.setTitle("缴费记录");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

        List<PayModel> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new PayModel());
        }
        adapter.refreshList(list);
    }
}
