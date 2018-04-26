package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.NewsPaperListModel;
import com.yiyoupin.stock.ui.adapter.NewspaperAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.NetErrorView;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 早晚报
 */

public class NewspaperListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView newspaperList;
    protected NetErrorView netError;
    private NewspaperAdapter adapter;
    private int page;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_newspaper_list;
    }

    @Override
    public void initDatas() {
        adapter = new NewspaperAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        newspaperList = (XRecyclerView) findViewById(R.id.newspaper_list);
        netError = (NetErrorView) findViewById(R.id.net_error);

    }

    @Override
    public void initAction() {
        netError.setNetClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(true,true);
            }
        });
        newspaperList.setLayoutManager(new LinearLayoutManager(mContext));
        newspaperList.setAdapter(adapter);
        titleView.setTitle("早晚报");
        newspaperList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh(false, true);
            }

            @Override
            public void onLoadMore() {
                refresh(false, false);
            }
        });

        refresh(true, true);
    }

    private void refresh(boolean showLoading, boolean refresh) {

        if (showLoading) {
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "1" : (page + 1) + "");
        addNetwork(Api.getInstance().getService(ApiService.class).dailyList(params)
                , new Action1<NewsPaperListModel>() {
                    @Override
                    public void call(NewsPaperListModel model) {
                        complete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            netError.setVisibility(View.GONE);
                            if (refresh) {
                                adapter.refreshList(model.getData().getRows());
                            } else {
                                adapter.addList(model.getData().getRows());
                            }
                            if (refresh) {
                                page = 1;
                            } else {
                                page += 1;
                            }

                            if (adapter.getItemCount() >= model.getData().getTotal_number()) {
                                newspaperList.setLoadingMoreEnabled(false);
                            } else {
                                newspaperList.setLoadingMoreEnabled(true);
                            }


                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        complete();
                        netError.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void complete() {
        newspaperList.loadMoreComplete();
        newspaperList.refreshComplete();
    }

}
