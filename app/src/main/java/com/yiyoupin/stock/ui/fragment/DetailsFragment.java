package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseFragment;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.MingXiModel;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.DetailsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3017:53
 * @Email zyp@jusfoun.com
 * @Description $明细fragment
 */
public class DetailsFragment extends BaseStockFragment {

    protected XRecyclerView recyclerView;
    private DetailsAdapter adapter;

    private int type = 0;
    private MingXiModel model;
    private StockDetailModel.StockDetailDataModel stockDetailDataModel;
    public static BaseFragment getInstance(int type, MingXiModel model,StockDetailModel.StockDetailDataModel stockDetailDataModel) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("model", model);
        bundle.putSerializable("stockDetailDataModel", stockDetailDataModel);

        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_details;
    }

    @Override
    public void initDatas() {
        type = (int) getArguments().get("type");
        if (getArguments().getSerializable("model") != null) {
            model = (MingXiModel) getArguments().getSerializable("model");
        }

        if (getArguments().getSerializable("stockDetailDataModel") != null) {
            stockDetailDataModel = (StockDetailModel.StockDetailDataModel) getArguments().getSerializable("stockDetailDataModel");
        }
        adapter = new DetailsAdapter(mContext);
    }

    @Override
    public void initView(View rootView) {
        recyclerView = (XRecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    @Override
    public void initAction() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        adapter.setType(type);

    }

    @Override
    protected void refreshData() {
        if (type == DetailsAdapter.TYPE_FIVE && model != null) {
            adapter.refreshList(model.list);
        }else if(stockDetailDataModel!=null){
            adapter.refreshList(stockDetailDataModel.trade_detail);
        }
    }

//    private void getNewsList() {
//        if (TextUtils.isEmpty(stockID)) {
//            return;
//        }
//        showLoadDialog();
//        HashMap<String, String> params = new HashMap();
//        params.put("stock_id", stockID);
//
//
//        addNetwork(Api.getInstance().getService(ApiService.class).getNewsList(params)
//                , new Action1<HomeListModel>() {
//                    @Override
//                    public void call(HomeListModel model) {
//                        hideLoadDialog();
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        hideLoadDialog();
//                    }
//                });
//    }

}
