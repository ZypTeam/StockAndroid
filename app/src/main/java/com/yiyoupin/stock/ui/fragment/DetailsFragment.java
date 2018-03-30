package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseFragment;
import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.DetailsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3017:53
 * @Email zyp@jusfoun.com
 * @Description $明细fragment
 */
public class DetailsFragment extends BaseStockFragment {

    protected XRecyclerView recyclerView;
    private DetailsAdapter adapter;

    private int type=0;

    public static BaseFragment getInstance(int type ){
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_details;
    }

    @Override
    public void initDatas() {
        type = (int)getArguments().get("type");
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

        List<BaseModel> list = new ArrayList<>();

        for(int i=0;i<100;i++){
            list.add(new HomeListModel());
        }
        adapter.refreshList(list);
    }

    @Override
    protected void refreshData() {

    }

}
