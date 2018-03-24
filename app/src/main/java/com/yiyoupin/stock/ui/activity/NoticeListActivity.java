package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jusfoun.baselibrary.widget.xRecyclerView.XRecyclerView;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NewspaperModel;
import com.yiyoupin.stock.model.NoticeModel;
import com.yiyoupin.stock.ui.adapter.NoticeAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 公告列表
 */

public class NoticeListActivity extends BaseStockActivity {

    protected BackTitleView titleView;
    protected XRecyclerView noticeList;
    private NoticeAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_notice_list;
    }

    @Override
    public void initDatas() {
        adapter=new NoticeAdapter(mContext);
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        noticeList = (XRecyclerView) findViewById(R.id.notice_list);

    }

    @Override
    public void initAction() {
        noticeList.setLayoutManager(new LinearLayoutManager(mContext));
        noticeList.setAdapter(adapter);
        titleView.setTitle("公告");
        refreshList();
    }

    private void refreshList(){
        List<NoticeModel> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NoticeModel model=new NoticeModel();
            list.add(model);
        }
        adapter.refreshList(list);
    }
}
