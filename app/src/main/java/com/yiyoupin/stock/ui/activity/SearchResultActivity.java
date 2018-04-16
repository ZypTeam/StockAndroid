package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.SearchListModel;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.adapter.SearchAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import rx.functions.Action1;

import static com.yiyoupin.stock.ui.activity.SearchActivity.HISTORY;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class SearchResultActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView search;
    protected XRecyclerView searchResult;
    private SearchAdapter adapter;
    private int page;

    private String searchKey;

    private HashSet<String> historySearch;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initDatas() {
        searchKey=getIntent().getExtras().getString(UiUtils.SEARCH_KEY);
        adapter = new SearchAdapter(mContext);
        historySearch= (HashSet<String>) SharePrefenceUtils.getInstance().getSet(HISTORY);
    }

    @Override
    public void initView() {

        searchResult = (XRecyclerView) findViewById(R.id.search_result);
        titleView = (BackTitleView) findViewById(R.id.title_view);
        search = (TextView) findViewById(R.id.search);
    }

    @Override
    public void initAction() {

        titleView.setTitle("股票查询");
        searchResult.setLayoutManager(new LinearLayoutManager(mContext));
        searchResult.setPullRefreshEnabled(false);
        searchResult.setLoadingMoreEnabled(true);

        search.setOnClickListener(v -> {
            onBackPressed();
        });

        searchResult.setAdapter(adapter);

        searchResult.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh(false,true);
            }

            @Override
            public void onLoadMore() {
                refresh(false,false);
            }
        });

        adapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack(SearchModel model) {
                addHotList(model);
                if (!historySearch.contains(model)) {
                    if (historySearch.size() < 20) {
                        historySearch.add(new Gson().toJson(model));
                    } else {
                        for (Iterator<String> iter = historySearch.iterator(); iter.hasNext(); ) {
                            //移除第一个
                            iter.remove();
                            break;
                        }
                        historySearch.add(new Gson().toJson(model));
                    }
                    SharePrefenceUtils.getInstance().setSet(HISTORY,historySearch);
                }
            }
        });

        refresh(true,true);
    }

    private void refresh(boolean showLoading, boolean refresh) {

        if (showLoading){
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "1" : (page + 1) + "");
        params.put("keywords",searchKey);
        addNetwork(Api.getInstance().getService(ApiService.class).searchList(params)
                , new Action1<SearchListModel>() {
                    @Override
                    public void call(SearchListModel model) {
                        complete();
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (refresh){
                                adapter.refreshList(model.getData().getRows());
                            }else {
                                adapter.addList(model.getData().getRows());
                            }
                            if (refresh){
                                page=1;
                            }else {
                                page+=1;
                            }

                            if (adapter.getItemCount()>=model.getData().getTotal_number()){
                                searchResult.setLoadingMoreEnabled(false);
                            }else {
                                searchResult.setLoadingMoreEnabled(true);
                            }


                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        complete();
                    }
                });
    }

    private void addHotList(SearchModel model){
        HashMap<String, String> params = new HashMap();
        params.put("stock_code", model.getStock_code());
        addNetwork(Api.getInstance().getService(ApiService.class).addHotSearch(params)
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel noDataModel) {
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    private void complete(){
        searchResult.loadMoreComplete();
        searchResult.refreshComplete();
    }

}
