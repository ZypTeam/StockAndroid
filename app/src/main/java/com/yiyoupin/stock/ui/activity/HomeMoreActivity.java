package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

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
 * @version create time:18/3/2309:14
 * @Email zyp@jusfoun.com
 * @Description ${首页 更多页面}
 */
public class HomeMoreActivity extends BaseStockActivity {
    protected BackTitleView titlebar;
    protected XRecyclerView recyclerView;
    protected LinearLayout layoutTitle;
    private int type;

    public static String TYPE = "type";


    private HomeListAdapter adapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_home_more;
    }

    @Override
    public void initDatas() {
        type = getIntent().getIntExtra(TYPE, HomeListAdapter.TYPE_STRATEGIES);
        adapter = new HomeListAdapter(mContext);
    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        layoutTitle = (LinearLayout) findViewById(R.id.layout_title);

    }

    @Override
    public void initAction() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        if (type == HomeListAdapter.TYPE_STRATEGIES) {
            titlebar.setTitle("选股策略");
            adapter.setType(HomeListAdapter.TYPE_STRATEGIES_MORE);
        } else if (type == HomeListAdapter.TYPE_FEATURED) {

            titlebar.setTitle("买点精选");
            adapter.setType(HomeListAdapter.TYPE_FEATURED);
            layoutTitle.setVisibility(View.VISIBLE);
        } else if (type == HomeListAdapter.TYPE_FORM) {

            adapter.setType(type);
            titlebar.setTitle("技术形态");
        }


        List<BaseModel> list = new ArrayList<>();
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());

        adapter.refreshList(list);

    }
}
