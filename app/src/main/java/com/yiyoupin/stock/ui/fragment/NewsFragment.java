package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.NewModel;
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


    private NewModel newModel;
    private int position = 0;
    public static NewsFragment getInstance( NewModel model,int position) {

        Log.e("tag","NewsFragmentNewsFragment="+position);
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("newModel",model);
        bundle.putInt("position",position);
        newsFragment.setArguments(bundle);
        return newsFragment;
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
        newModel =(NewModel)getArguments().getSerializable("newModel");
        position = getArguments().getInt("position");
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


        if(newModel!=null&&newModel.data!=null) {
            if (position == 0) {
                Log.e("tag","newModelnewModel1");
                adapter.refreshList(newModel.data.daily_list);
            }else if(position==1){
                Log.e("tag","newModelnewModel2");
                adapter.refreshList(newModel.data.stop_list);
            }else if(position==2){
                Log.e("tag","newModelnewModel3");
                adapter.refreshList(newModel.data.notice_list);
            }
        }


    }



}
