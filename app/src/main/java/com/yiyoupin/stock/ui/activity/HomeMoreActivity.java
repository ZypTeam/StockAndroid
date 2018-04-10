package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.BuySelectionMoreModel;
import com.yiyoupin.stock.model.StrategiesMoreModel;
import com.yiyoupin.stock.model.TechnologyMoreModel;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2309:14
 * @Email zyp@jusfoun.com
 * @Description ${首页 更多页面}
 */
public class HomeMoreActivity extends BaseStockActivity {
    protected BackTitleView titlebar;
    protected XRecyclerView recyclerView;
    protected LinearLayout layoutTitle;
    private int type;

    public static String TYPE = "type";


    private HomeListAdapter adapter;

    private int page_index = 1;
    private int page_size = 20;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_home_more;
    }

    @Override
    public void initDatas() {
        type = getIntent().getIntExtra(TYPE, HomeListAdapter.TYPE_STRATEGIES);
        adapter = new HomeListAdapter(mContext);
    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        layoutTitle = (LinearLayout) findViewById(R.id.layout_title);

    }

    @Override
    public void initAction() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingMoreEnabled(false);

        if (type == HomeListAdapter.TYPE_STRATEGIES) {
            titlebar.setTitle("选股策略");
            adapter.setType(HomeListAdapter.TYPE_STRATEGIES_MORE);
        } else if (type == HomeListAdapter.TYPE_FEATURED) {

            titlebar.setTitle("买点精选");
            adapter.setType(HomeListAdapter.TYPE_FEATURED);
            layoutTitle.setVisibility(View.VISIBLE);
        } else if (type == HomeListAdapter.TYPE_FORM) {
            adapter.setType(type);
            titlebar.setTitle("技术形态");
        }


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

//        List<BaseModel> list = new ArrayList<>();
//        list.add(new HomeListModel());
//        list.add(new HomeListModel());
//        list.add(new HomeListModel());
//        list.add(new HomeListModel());
//        list.add(new HomeListModel());
//
//        adapter.refreshList(list);

        if (type == HomeListAdapter.TYPE_STRATEGIES) {
            getStrategiesNet();
        }else if (type == HomeListAdapter.TYPE_FEATURED) {
            getBuyselectionNet();
        } else if (type == HomeListAdapter.TYPE_FORM) {
           getTechnologyNet();
        }
    }

    /**
     *  选股策略
     * */
    private void getStrategiesNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("page_index", page_index + "");
        map.put("page_size", page_size + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getStrategiesNet(map)
                , new Action1<StrategiesMoreModel>() {
                    @Override
                    public void call(StrategiesMoreModel model) {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                if(page_index==1) {
                                    adapter.refreshList(model.data.rows);
                                }else{
                                    adapter.addList(model.data.rows);
                                }

                                if(adapter.getItemCount()<model.data.total_number){
                                    recyclerView.setLoadingMoreEnabled(true);
                                    page_index++;
                                }else{
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

    /**
     *  买点精选
     * */
    private void getBuyselectionNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("page_index", page_index + "");
        map.put("page_size", page_size + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getBuyselectionNet(map)
                , new Action1<BuySelectionMoreModel>() {
                    @Override
                    public void call(BuySelectionMoreModel model) {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                if(page_index==1) {
                                    adapter.refreshList(model.data.rows);
                                }else{
                                    adapter.addList(model.data.rows);
                                }

                                if(adapter.getItemCount()<model.data.total_number){
                                    recyclerView.setLoadingMoreEnabled(true);
                                    page_index++;
                                }else{
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

    /**
     *  技术形态
     * */
    private void getTechnologyNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("page_index", page_index + "");
        map.put("page_size", page_size + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getTechnologyNet(map)
                , new Action1<TechnologyMoreModel>() {
                    @Override
                    public void call(TechnologyMoreModel model) {
                        recyclerView.refreshComplete();
                        recyclerView.loadMoreComplete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                if(page_index==1) {
                                    adapter.refreshList(model.data.rows);
                                }else{
                                    adapter.addList(model.data.rows);
                                }

                                if(adapter.getItemCount()<model.data.total_number){
                                    recyclerView.setLoadingMoreEnabled(true);
                                    page_index++;
                                }else{
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
