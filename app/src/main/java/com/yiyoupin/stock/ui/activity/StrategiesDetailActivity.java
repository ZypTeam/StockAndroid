package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2814:32
 * @Email zyp@jusfoun.com
 * @Description ${策略详情activity}
 */
public class StrategiesDetailActivity extends BaseStockActivity {
    protected BackTitleView titlebar;
    protected TextView textTitle;
    protected TextView textDes;
    protected TextView textHisToday;
    protected RecyclerView recyclerToday;
    protected TextView textHisPhone;
    protected RecyclerView recyclerPhone;
    protected RecyclerView recyclerHis;


    private HomeListAdapter todayAdapter, phoneAdapter, hisAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_strategies_detail;
    }

    @Override
    public void initDatas() {
        todayAdapter = new HomeListAdapter(mContext);
        phoneAdapter = new HomeListAdapter(mContext);
        hisAdapter = new HomeListAdapter(mContext);
    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        textTitle = (TextView) findViewById(R.id.text_title);
        textDes = (TextView) findViewById(R.id.text_des);
        textHisToday = (TextView) findViewById(R.id.text_his_today);
        recyclerToday = (RecyclerView) findViewById(R.id.recycler_today);
        textHisPhone = (TextView) findViewById(R.id.text_his_phone);
        recyclerPhone = (RecyclerView) findViewById(R.id.recycler_phone);
        recyclerHis = (RecyclerView) findViewById(R.id.recycler_his);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("策略详情");
        recyclerToday.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerToday.setAdapter(todayAdapter);

        recyclerPhone.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerPhone.setAdapter(phoneAdapter);

        recyclerHis.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerHis.setAdapter(hisAdapter);





        recyclerToday.setHasFixedSize(true);
        recyclerToday.setNestedScrollingEnabled(false);

        recyclerPhone.setHasFixedSize(true);
        recyclerPhone.setNestedScrollingEnabled(false);

        recyclerHis.setHasFixedSize(true);
        recyclerHis.setNestedScrollingEnabled(false);

        List<BaseModel> list = new ArrayList<>();
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        todayAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);
        todayAdapter.refreshList(list);

        phoneAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);
        phoneAdapter.refreshList(list);

        hisAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);
        hisAdapter.refreshList(list);


        textTitle.setText("新牛奔腾");
        textDes.setText("新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾新牛奔腾");

    }
}
