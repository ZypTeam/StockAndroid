package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
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
import com.yiyoupin.stock.model.NoticeListModel;
import com.yiyoupin.stock.model.SearchListModel;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.adapter.SearchAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 搜索页
 */

public class SearchActivity extends BaseStockActivity {
    private static final String HISTORY="history_list";
    protected BackTitleView titleView;
    protected EditText search;
    protected XRecyclerView searchResult;
    protected TextView history;
    protected RecyclerView hisList;
    protected TextView hot;
    protected RecyclerView hotList;
    private SearchAdapter adapter,hisAdapter,hotAdapter;

    private String searchKey;
    private int page;

    private HashSet<String> historySearch;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initDatas() {
        historySearch= (HashSet<String>) SharePrefenceUtils.getInstance().getSet(HISTORY);
        adapter = new SearchAdapter(mContext);
        hotAdapter = new SearchAdapter(mContext);
        hisAdapter = new SearchAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        search = (EditText) findViewById(R.id.search);
        searchResult = (XRecyclerView) findViewById(R.id.search_result);

        searchResult.setAdapter(adapter);
        history = (TextView) findViewById(R.id.history);
        hisList =  findViewById(R.id.his_list);
        hot = (TextView) findViewById(R.id.hot);
        hotList =  findViewById(R.id.hot_list);

    }

    @Override
    public void initAction() {

        hisList.setLayoutManager(new LinearLayoutManager(mContext));
        hisList.setAdapter(hisAdapter);
        hotList.setLayoutManager(new LinearLayoutManager(mContext));
        hotList.setAdapter(hotAdapter);

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

        hisAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack(SearchModel model) {
                searchKey=model.getStock_name();
            }
        });

        hotAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack(SearchModel model) {
                searchKey=model.getStock_name();
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

        searchResult.setVisibility(View.GONE);

        List<SearchModel> his=new ArrayList<>();
        for (Iterator<String> iter = historySearch.iterator(); iter.hasNext(); ) {
            SearchModel searchModel=new Gson().fromJson(iter.next(),SearchModel.class);
            his.add(searchModel);
        }
        hisAdapter.refreshList(his);

        searchResult.setLayoutManager(new LinearLayoutManager(mContext));
        searchResult.setPullRefreshEnabled(false);
        searchResult.setLoadingMoreEnabled(true);

        titleView.setTitle("股票查询");

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(search.getText().toString())) {
                        searchKey=search.getText().toString();
                        refresh(true,true);
                    } else {
                        showToast("不能为空");
                    }
                    return true;
                }
                return false;
            }
        });

        getHotList();
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


    private void getHotList(){
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, "1");
        addNetwork(Api.getInstance().getService(ApiService.class).hotSearchList(params)
                , new Action1<SearchListModel>() {
                    @Override
                    public void call(SearchListModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            hotAdapter.refreshList(model.getData().getRows());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }

    private void refresh(boolean showLoading, boolean refresh) {

        hotList.setVisibility(View.GONE);
        hisList.setVisibility(View.GONE);
        history.setVisibility(View.GONE);
        hot.setVisibility(View.GONE);
        searchResult.setVisibility(View.VISIBLE);
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

    private void complete(){
        searchResult.loadMoreComplete();
        searchResult.refreshComplete();
    }
}
