package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.ChartDetailModel;
import com.yiyoupin.stock.model.ChartItemModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.NewsPaperListModel;
import com.yiyoupin.stock.ui.adapter.ChartsDetailAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
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

public class ChartsDetailActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected RecyclerView list;
    private ChartsDetailAdapter adapter;
    private int page;
    private String detailId;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chart_detail;
    }

    @Override
    public void initDatas() {
        adapter=new ChartsDetailAdapter(mContext);
        detailId=getIntent().getExtras().getString(UiUtils.DETAIL_ID);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        list = (RecyclerView) findViewById(R.id.list);

    }

    @Override
    public void initAction() {

        titleView.setTitle("龙虎榜详情");
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

        refresh(true,true);
    }

    private void refresh(boolean showLoading, boolean refresh) {

        if (showLoading){
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
      /*  params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "1" : (page + 1) + "");*/
        params.put("detail_id",detailId);
        addNetwork(Api.getInstance().getService(ApiService.class).chartsDetail(params)
                , new Action1<ChartDetailModel>() {
                    @Override
                    public void call(ChartDetailModel model) {
//                        complete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (refresh){
                                adapter.refreshList(model.getData().getDetail_list());
                            }else {
                                adapter.addList(model.getData().getDetail_list());
                            }
                            if (refresh){
                                page=1;
                            }else {
                                page+=1;
                            }

                            /*if (adapter.getItemCount()>=model.getData().getTotal_number()){
                                newspaperList.setLoadingMoreEnabled(false);
                            }else {
                                newspaperList.setLoadingMoreEnabled(true);
                            }*/


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
