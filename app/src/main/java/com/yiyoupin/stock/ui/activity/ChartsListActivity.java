package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.ui.adapter.ChartsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 龙虎榜 列表
 */

public class ChartsListActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected XRecyclerView list;
    private ChartsAdapter adapter;

    private int page = 0;


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
        list = (XRecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);
        titleView.setTitle("龙虎榜");
        titleView.setRightIcon(R.mipmap.icon_search);

        list.setLoadingListener(new XRecyclerView.LoadingListener() {
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

        if (showLoading){
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "0" : (page + 1) + "");
        addNetwork(Api.getInstance().getService(ApiService.class).chartsList(params)
                , new Action1<ChartsListModel>() {
                    @Override
                    public void call(ChartsListModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            List list = new ArrayList<>();
                            for (ChartsModel quotesModel : model.getData().getTopcharts()) {
                                list.add(quotesModel);
                            }
                            adapter.updateData(list);

                            if (refresh){
                                page=0;
                            }else {
                                page+=1;
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });

    }
}
