package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.ChartItemModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.ui.adapter.ChartsDetailAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsDetailActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected RecyclerView list;
    private ChartsDetailAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chart_detail;
    }

    @Override
    public void initDatas() {
        adapter=new ChartsDetailAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (RecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {

        titleView.setTitle("龙虎榜详情");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

        List<ChartItemModel> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ChartItemModel());
        }
        adapter.refreshList(list);
    }
}
