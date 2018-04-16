package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.NewsItemAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3014:44
 * @Email zyp@jusfoun.com
 * @Description ${新闻fragment}
 */
public class NewsFragment extends BaseStockFragment {

    protected RecyclerView recyclerView;
    private NewsItemAdapter adapter;

    public static NewsFragment getInstance() {
        return new NewsFragment();
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initDatas() {
        adapter = new NewsItemAdapter(mContext);
    }

    @Override
    public void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    @Override
    public void initAction() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        List<SearchModel> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            list.add(new SearchModel());
        }

        adapter.refreshList(list);

    }



}
