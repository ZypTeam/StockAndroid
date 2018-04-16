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
import com.yiyoupin.stock.ui.util.UiUtils;
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
    public static final String HISTORY="history_list";
    protected BackTitleView titleView;
    protected EditText search;
    protected TextView history;
    protected RecyclerView hisList;
    protected TextView hot;
    protected RecyclerView hotList;
    private SearchAdapter hisAdapter,hotAdapter;

    private String searchKey;

    private HashSet<String> historySearch;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initDatas() {
        historySearch= (HashSet<String>) SharePrefenceUtils.getInstance().getSet(HISTORY);
        hotAdapter = new SearchAdapter(mContext);
        hisAdapter = new SearchAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        search = (EditText) findViewById(R.id.search);
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

        hisAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack(SearchModel model) {
                searchKey=model.getStock_name();
                search.setText(searchKey);
                search(searchKey);
            }
        });

        hotAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack(SearchModel model) {
                searchKey=model.getStock_name();
                search.setText(searchKey);
                search(searchKey);
            }
        });



        titleView.setTitle("股票查询");

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(search.getText().toString())) {
                        searchKey=search.getText().toString();
                        search(searchKey);
                    } else {
                        showToast("不能为空");
                    }
                    return true;
                }
                return false;
            }
        });

        getHotList();
        refreshHis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshHis();
//        getHotList();
    }

    private void refreshHis(){
        List<SearchModel> his=new ArrayList<>();
        for (Iterator<String> iter = historySearch.iterator(); iter.hasNext(); ) {
            SearchModel searchModel=new Gson().fromJson(iter.next(),SearchModel.class);
            his.add(searchModel);
        }
        hisAdapter.refreshList(his);
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

    private void search(String  searchKey){
        UiUtils.goSearchResult(mContext,searchKey);
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

}
