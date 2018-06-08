package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.BuySelectionMoreModel;
import com.yiyoupin.stock.model.StrategiesDetailModel;
import com.yiyoupin.stock.model.StrategiesMoreModel;
import com.yiyoupin.stock.model.TechnologyMoreModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2309:14
 * @Email zyp@jusfoun.com
 * @Description ${首页 更多页面}
 */
public class HisListActivity extends BaseStockActivity {
    protected BackTitleView titlebar;
    protected XRecyclerView recyclerView;
    protected LinearLayout layoutTitle;

    private HomeListAdapter adapter;

    private int page_index = 1;
    private int page_size = 20;
    private String tactics_id="";



    @Override
    public int getLayoutResId() {
        return R.layout.activity_home_more;
    }

    @Override
    public void initDatas() {
        tactics_id = getIntent().getStringExtra("tactics_id");
        adapter = new HomeListAdapter(mContext);
        adapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);
    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        layoutTitle = (LinearLayout) findViewById(R.id.layout_title);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("历史股票");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingMoreEnabled(false);


        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_index = 1;
                getStrategiesNet();
            }

            @Override
            public void onLoadMore() {
                getStrategiesNet();
            }
        });
        getStrategiesNet();
    }

    /**
     * 选股策略 历史
     */
    private void getStrategiesNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("page_index", page_index + "");
        map.put("page_size", page_size + "");
        map.put("tactics_id", tactics_id + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getStrategieHisNet(map)
                , new Action1<StrategiesDetailModel>() {
                    @Override
                    public void call(StrategiesDetailModel model) {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                if (page_index == 1) {
                                    adapter.refreshList(model.data.rows);
                                } else {
                                    adapter.addList(model.data.rows);
                                }

                                if (adapter.getItemCount() < model.data.total_number) {
                                    recyclerView.setLoadingMoreEnabled(true);
                                    page_index++;
                                } else {
                                    recyclerView.setLoadingMoreEnabled(false);
                                }

                            }
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
