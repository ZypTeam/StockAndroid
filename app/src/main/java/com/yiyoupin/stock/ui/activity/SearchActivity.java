package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.adapter.SearchAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 搜索页
 */

public class SearchActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected EditText search;
    protected XRecyclerView searchResult;
    protected TextView history;
    protected RecyclerView hisList;
    protected TextView hot;
    protected RecyclerView hotList;
    private SearchAdapter adapter,hisAdapter,hotAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initDatas() {
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

        hisAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack() {
                search("");
            }
        });

        hotAdapter.setCallBack(new SearchAdapter.CallBack() {
            @Override
            public void callBack() {
                search("");
            }
        });

        List<SearchModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new SearchModel());
        }
        searchResult.setVisibility(View.GONE);

        hisAdapter.refreshList(list);
        hotAdapter.refreshList(list);

        searchResult.setLayoutManager(new LinearLayoutManager(mContext));
        searchResult.setPullRefreshEnabled(false);
        searchResult.setLoadingMoreEnabled(true);

        titleView.setTitle("股票查询");

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(search.getText().toString())) {
                        search(search.getText().toString());
                    } else {
                        showToast("不能为空");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void search(String key) {

        hotList.setVisibility(View.GONE);
        hisList.setVisibility(View.GONE);
        history.setVisibility(View.GONE);
        hot.setVisibility(View.GONE);
        searchResult.setVisibility(View.VISIBLE);
        List<SearchModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new SearchModel());
        }

        adapter.refreshList(list);
    }
}
