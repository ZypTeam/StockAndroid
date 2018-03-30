package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MsgModel;
import com.yiyoupin.stock.ui.adapter.MsgAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class MsgListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView list;
    private MsgAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_msg_list;
    }

    @Override
    public void initDatas() {
        adapter = new MsgAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (XRecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {
        titleView.setTitle("消息列表");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

        List<MsgModel> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MsgModel());
        }
        adapter.refreshList(list);
    }
}
