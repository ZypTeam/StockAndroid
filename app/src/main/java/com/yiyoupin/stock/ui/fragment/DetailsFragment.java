package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseFragment;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.MIngXIDataModel;
import com.yiyoupin.stock.model.MingXiModel;
import com.yiyoupin.stock.model.StockDetailModel;
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

    protected RecyclerView recyclerView;
    private DetailsAdapter adapter;

    private int type = 0;
    private MingXiModel model;
    private String stockID;

    public static BaseFragment getInstance(int type, MingXiModel model, String stockID) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("model", model);
        bundle.putString("stockID", stockID);
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

        stockID = getArguments().getString("stockID");

//        if (getArguments().getSerializable("stockDetailDataModel") != null) {
//            stockDetailDataModel = (StockDetailModel.StockDetailDataModel) getArguments().getSerializable("stockDetailDataModel");
//        }
        adapter = new DetailsAdapter(mContext);
    }

    @Override
    public void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

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
        } else if (type == DetailsAdapter.TYPE_DETAILS) {
//            adapter.refreshList(stockDetailDataModel.trade_detail);
            getNewsList();
        }
    }

    private void getNewsList() {
//        showLoadDialog();

        Log.e("tag","stockIDstockID"+stockID);
        HashMap<String, String> params = new HashMap();
        params.put("stock_id", stockID);



        addNetwork(Api.getInstance().getService(ApiService.class).getMingXiNet(params)
                , new Action1<MIngXIDataModel>() {
                    @Override
                    public void call(MIngXIDataModel model) {
                        hideLoadDialog();

                        if (model.data != null) {
                            adapter.refreshList(model.data);
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
