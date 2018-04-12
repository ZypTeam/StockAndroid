package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.StockModel;
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
 * @version create time:18/3/2310:23
 * @Email zyp@jusfoun.com
 * @Description ${技术形态列表}
 */
public class FromListActivity extends BaseStockActivity {
    protected XRecyclerView recyclerView;
    protected BackTitleView titlebar;
    private HomeListAdapter adapter;
    private int  page = 1;

    private String technology_id= "";

    public static String TECHNOLOGY_ID="technology_id";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_from_list;
    }

    @Override
    public void initDatas() {
        technology_id = getIntent().getStringExtra(TECHNOLOGY_ID);
        adapter = new HomeListAdapter(mContext);

    }

    @Override
    public void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        titlebar = (BackTitleView) findViewById(R.id.titlebar);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("选股技术形态");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        adapter.setType(HomeListAdapter.TYPE_FORM_LIST);


        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refreshList();
            }

            @Override
            public void onLoadMore() {
                refreshList();
            }
        });
        refreshList();
    }


    private void refreshList(){
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, page+"");
        params.put("technology_id", technology_id);


        addNetwork(Api.getInstance().getService(ApiService.class).getJiShuXingtAINet(params)
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        hideLoadDialog();
                        recyclerView.loadMoreComplete();
                        recyclerView.refreshComplete();
                        if (model.getCode() == 0) {
                            if(model.getData()!=null){
                                adapter.refreshList(model.getData().rows);
                                if(adapter.getItemCount()>=model.getData().total_number){
                                    page++;
                                    recyclerView.setLoadingMoreEnabled(false);
                                }else{
                                    recyclerView.setLoadingMoreEnabled(true);
                                }
                            }else{
                                recyclerView.setLoadingMoreEnabled(false);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        recyclerView.loadMoreComplete();
                        recyclerView.refreshComplete();
                    }
                });
    }
}
