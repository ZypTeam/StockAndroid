package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.SearchListModel;
import com.yiyoupin.stock.model.StockModel;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${自选}
 */
public class OptionalFrgament extends BaseStockFragment {

    protected BackTitleView titlebar;
    protected XRecyclerView recyclerView;
    private HomeListAdapter adapter;

    public static OptionalFrgament getInstance() {
        OptionalFrgament fragment = new OptionalFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {
        refreshList();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_optional;
    }

    @Override
    public void initDatas() {
        adapter = new HomeListAdapter(mContext);
        adapter.setType(HomeListAdapter.TYPE_FORM_LIST);

    }

    @Override
    public void initView(View rootView) {
        titlebar = (BackTitleView) rootView.findViewById(R.id.titlebar);
        recyclerView = (XRecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("自选");
        titlebar.setLeftGone();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    private void refreshList(){
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        addNetwork(Api.getInstance().getService(ApiService.class).optionalList(params)
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            List<BaseModel> list=new ArrayList<>();

                            Log.e("tag","getStock_listgetStock_list1="+model.getData().getStock_list().size());
                            for (StockModel stockModel : model.getData().getStock_list()) {
                                list.add(stockModel);
                            }
                            Log.e("tag","getStock_listgetStock_list2="+list.size());
                            adapter.refreshList(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                    }
                });
    }
}
