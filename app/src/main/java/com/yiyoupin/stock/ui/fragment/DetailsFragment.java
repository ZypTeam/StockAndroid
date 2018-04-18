package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseFragment;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MingXiModel;
import com.yiyoupin.stock.ui.adapter.DetailsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

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

    public static BaseFragment getInstance(int type, MingXiModel model) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putSerializable("model", model);
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
        if (type == DetailsAdapter.TYPE_FIVE && model != null) {
            adapter.refreshList(model.list);
        }else{

        }
    }

    @Override
    protected void refreshData() {

    }

}
