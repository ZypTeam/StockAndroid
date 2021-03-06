package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.PayListModel;
import com.yiyoupin.stock.model.PayModel;
import com.yiyoupin.stock.ui.adapter.ChartsAdapter;
import com.yiyoupin.stock.ui.adapter.PayAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class PayListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView list;
    private PayAdapter adapter;
    private int page;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_pay_list;
    }

    @Override
    public void initDatas() {
        adapter=new PayAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (XRecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {
        titleView.setTitle("缴费记录");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

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
        addNetwork(Api.getInstance().getService(ApiService.class).feeRecords(params)
                , new Action1<PayListModel>() {
                    @Override
                    public void call(PayListModel model) {
                        complete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (refresh){
                                adapter.refreshList(model.getData().getConsume_records());
                            }else {
                                adapter.addList(model.getData().getConsume_records());
                            }
                            if (refresh){
                                page=1;
                            }else {
                                page+=1;
                            }


                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        complete();
                    }
                });
    }

    private void complete(){
        list.loadMoreComplete();
        list.refreshComplete();
    }

}
