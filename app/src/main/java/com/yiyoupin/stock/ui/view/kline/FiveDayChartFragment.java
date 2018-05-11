package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.event.IEvent;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.FiveDayModel;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.event.FuTuEvent;

import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class FiveDayChartFragment extends BaseStockFragment {


    private TimeLineView mTimeLineView;
    private String stock_id = "", tactics_id = "", stock_code = "";


    protected MDrawingChartView viewDrawingChart;
    private int currentIndex = 0;


    public FiveDayChartFragment() {
        // Required empty public constructor
    }

    public static FiveDayChartFragment newInstance(String stock_id, String tactics_id, String stock_code,int currentIndex) {
        FiveDayChartFragment fragment = new FiveDayChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("stock_id", stock_id);
        bundle.putString("tactics_id", tactics_id);
        bundle.putString("stock_code", stock_code);
        bundle.putInt("currentIndex", currentIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_five_day;
    }

    @Override
    public void initDatas() {
        stock_id = getArguments().getString("stock_id");
        tactics_id = getArguments().getString("tactics_id");
        stock_code = getArguments().getString("stock_code");
        currentIndex = getArguments().getInt("currentIndex");
    }

    @Override
    public void initView(View rootView) {

        mTimeLineView = (TimeLineView) rootView.findViewById(R.id.view_time_line);

        mTimeLineView.setDateFormat("yyyy-MM-dd");
        viewDrawingChart = (MDrawingChartView) rootView.findViewById(R.id.view_drawing_chart);

    }

    @Override
    public void initAction() {

    }


    private void getFiveDayDetialNet() {
        HashMap<String, String> params = new HashMap();
        params.put("stock_id", stock_id);
        params.put("tactics_id", tactics_id);

        addNetwork(Api.getInstance().getService(ApiService.class).getFiveDayDetialNet(params)
                , new Action1<FiveDayModel>() {
                    @Override
                    public void call(FiveDayModel model) {
                        hideLoadDialog();
                        if (model.data != null) {

                            int count = 0;
                            for (int i = 0; i < model.data.size(); i++) {
                                if (model.data.get(i) != null && model.data.get(i).dapandata != null)
                                    count += model.data.get(i).dapandata.size();
                            }
                            count =250*5;
                            mTimeLineView.setCount(count, count, count);
                            final List<List<HisData>> hisData = Util.get5Day(model.data);
                            if (hisData.get(0) != null && hisData.get(0).get(0) != null)
                                mTimeLineView.setLastClose(hisData.get(0).get(0).getClose());
                            mTimeLineView.initDatas(hisData);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }


    protected void refreshData() {
        getNetData(tactics_id);
    }

    @Override
    public void onEvent(IEvent event) {
        super.onEvent(event);

        if (event instanceof FuTuEvent) {
            this.tactics_id = ((FuTuEvent) event).tactics_id;
            if (currentIndex == ((FuTuEvent) event).currentIndex) {
                getNetData(((FuTuEvent) event).tactics_id);
            } else {
                isInit = true;
            }
        }
    }

    private void getNetData(String tactics_id) {
        if (StockShowActivity.NO_TACTICS_ID.equals(tactics_id)) {
            viewDrawingChart.setVisibility(View.GONE);
            getFiveDayDetialNet();
        } else {
            viewDrawingChart.setVisibility(View.VISIBLE);
            viewDrawingChart.setData(stock_code, tactics_id, 5);
        }
    }
}
