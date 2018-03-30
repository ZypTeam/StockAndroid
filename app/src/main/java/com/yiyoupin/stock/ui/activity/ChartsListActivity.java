package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.ui.adapter.ChartsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 龙虎榜 列表
 */

public class ChartsListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected RecyclerView list;
    private ChartsAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chart_list;
    }

    @Override
    public void initDatas() {
        adapter = new ChartsAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (RecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);
        titleView.setTitle("龙虎榜");
        titleView.setRightIcon(R.mipmap.icon_search);
        refresh(true,true);
    }

    private void refresh(boolean showLoading,boolean refresh){
        List list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuotesModel model=new QuotesModel();
            if (i==0){
                model.setExpanded(true);
            }else {
                model.setExpanded(false);
            }
            list.add(model);
        }
        adapter.updateData(list);
    }
}
