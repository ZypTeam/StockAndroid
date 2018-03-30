package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${自选}
 */
public class OptionalFrgament extends BaseStockFragment {

    protected BackTitleView titlebar;
    protected XRecyclerView recyclerView;
    private HomeListAdapter adapter;

    public static OptionalFrgament getInstance() {
        OptionalFrgament fragment = new OptionalFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_optional;
    }

    @Override
    public void initDatas() {
        adapter = new HomeListAdapter(mContext);
    }

    @Override
    public void initView(View rootView) {
        titlebar = (BackTitleView) rootView.findViewById(R.id.titlebar);
        recyclerView = (XRecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("自选");
        titlebar.setLeftGone();
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
