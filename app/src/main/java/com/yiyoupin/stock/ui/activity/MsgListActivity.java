package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.MsgListModel;
import com.yiyoupin.stock.model.MsgModel;
import com.yiyoupin.stock.model.NewsPaperListModel;
import com.yiyoupin.stock.ui.adapter.MsgAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.NetErrorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class MsgListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView list;
    private MsgAdapter adapter;
    private NetErrorView netErrorView;
    private int page;

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
        netErrorView=findViewById(R.id.net_error);
    }

    @Override
    public void initAction() {
        titleView.setTitle("消息列表");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

        netErrorView.setNetClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(true,true);
            }
        });

        list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh(false,true);
            }

            @Override
            public void onLoadMore() {
                refresh(false,false);
            }
        });

        refresh(true,true);
    }

    private void refresh(boolean showLoading, boolean refresh) {

        if (showLoading){
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "1" : (page + 1) + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getMsgList(params)
                , new Action1<MsgListModel>() {
                    @Override
                    public void call(MsgListModel model) {
                        complete();
                        hideLoadDialog();
                        netErrorView.setVisibility(View.GONE);
                        if (model.getCode() == 0) {
                            if (refresh){
                                adapter.refreshList(model.getData().getRows());
                            }else {
                                adapter.addList(model.getData().getRows());
                            }
                            if (refresh){
                                page=1;
                            }else {
                                page+=1;
                            }

                            if (adapter.getItemCount()>=model.getData().getTotal_number()){
                                list.setLoadingMoreEnabled(false);
                            }else {
                                list.setLoadingMoreEnabled(true);
                            }

                            if (adapter.getItemCount()==0){
                                netErrorView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        complete();
                        netErrorView.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void complete(){
        list.loadMoreComplete();
        list.refreshComplete();
    }
}
