package com.yiyoupin.stock.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.StrategiesDetailModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.HashMap;

import rx.functions.Action1;

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

    public static String CHOICENESS_ID = "choiceness_id";
    private String choiceness_id = "";


    @Override
    public int getLayoutResId() {
        return R.layout.activity_strategies_detail;
    }

    @Override
    public void initDatas() {
        todayAdapter = new HomeListAdapter(mContext);
        phoneAdapter = new HomeListAdapter(mContext);
        hisAdapter = new HomeListAdapter(mContext);

        choiceness_id = getIntent().getStringExtra(CHOICENESS_ID);
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

        todayAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        phoneAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        hisAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        todayAdapter.setChoicenessId(choiceness_id);
        phoneAdapter.setChoicenessId(choiceness_id);
        hisAdapter.setChoicenessId(choiceness_id);

        getTechnologyNet();
    }


    /**
     * 策略详情
     */
    private void getTechnologyNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("security_selection_id", choiceness_id);
        addNetwork(Api.getInstance().getService(ApiService.class).getStrategiesDetailNet(map)
                , new Action1<StrategiesDetailModel>() {
                    @Override
                    public void call(StrategiesDetailModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                todayAdapter.refreshList(model.data.today_stock);
                                phoneAdapter.refreshList(model.data.to_phone_stock);
                                hisAdapter.refreshList(model.data.history_stock);
                                if (model.data.tactic != null) {
                                    if (!TextUtils.isEmpty(model.data.tactic.tactics_name)) {
                                        textTitle.setText(model.data.tactic.tactics_name);
                                    }

                                    if (!TextUtils.isEmpty(model.data.tactic.description)) {
                                        textDes.setText(model.data.tactic.description);
                                    }
                                }
                            }
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
