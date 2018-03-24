package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NewspaperModel;
import com.yiyoupin.stock.ui.adapter.NewspaperAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 早晚报
 */

public class NewspaperListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView newspaperList;
    private NewspaperAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_newspaper_list;
    }

    @Override
    public void initDatas() {
        adapter=new NewspaperAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        newspaperList = (XRecyclerView) findViewById(R.id.newspaper_list);

    }

    @Override
    public void initAction() {
        newspaperList.setLayoutManager(new LinearLayoutManager(mContext));
        newspaperList.setAdapter(adapter);
        titleView.setTitle("早晚报");
        refreshList();
    }

    private void refreshList(){
        List<NewspaperModel> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewspaperModel model=new NewspaperModel();
            list.add(model);
        }
        adapter.refreshList(list);
    }
}
