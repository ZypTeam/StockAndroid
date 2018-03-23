package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2310:23
 * @Email zyp@jusfoun.com
 * @Description ${技术形态列表}
 */
public class FromListActivity extends BaseStockActivity {
    protected XRecyclerView recyclerView;
    protected BackTitleView titlebar;
    private HomeListAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_from_list;
    }

    @Override
    public void initDatas() {
        adapter = new HomeListAdapter(mContext);
    }

    @Override
    public void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        titlebar = (BackTitleView) findViewById(R.id.titlebar);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("选股技术形态");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        List<BaseModel> list = new ArrayList<>();
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        adapter.setType(HomeListAdapter.TYPE_FORM_LIST);
        adapter.refreshList(list);
    }
}
