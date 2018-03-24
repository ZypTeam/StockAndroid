package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.adapter.SearchAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
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
    private SearchAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initDatas() {
        adapter=new SearchAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        search = (EditText) findViewById(R.id.search);
        searchResult = (XRecyclerView) findViewById(R.id.search_result);

        searchResult.setAdapter(adapter);

    }

    @Override
    public void initAction() {

        searchResult.setLayoutManager(new LinearLayoutManager(mContext));
        searchResult.setPullRefreshEnabled(false);
        searchResult.setLoadingMoreEnabled(true);

        titleView.setTitle("股票查询");

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
                    if (!TextUtils.isEmpty(search.getText().toString())){
                        search(search.getText().toString());
                    }else {
                        showToast("不能为空");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void search(String key){
        List<SearchModel> list=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new SearchModel());
        }

        adapter.refreshList(list);
    }
}
