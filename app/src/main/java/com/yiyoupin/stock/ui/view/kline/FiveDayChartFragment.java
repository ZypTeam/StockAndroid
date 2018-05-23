package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.chart.my.CelLueCallBack;
import com.guoziwei.klinelib.chart.my.Util;
import com.guoziwei.klinelib.chart.my.model.FiveDayModel;
import com.guoziwei.klinelib.chart.my.model.StrategiesFiveTimeMFModel;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.event.IEvent;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class FiveDayChartFragment extends BaseStockFragment {


    private TimeLineView mTimeLineView;
    private String main_tactics_id = "", Incidental_tactics_id = "", stock_code = "";

    private int currentIndex = 0;


    public FiveDayChartFragment() {
        // Required empty public constructor
    }

    public static FiveDayChartFragment newInstance(String main_tactics_id, String Incidental_tactics_id, String stock_code, int currentIndex) {
        FiveDayChartFragment fragment = new FiveDayChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("main_tactics_id", main_tactics_id);
        bundle.putString("Incidental_tactics_id", Incidental_tactics_id);
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
        main_tactics_id = getArguments().getString("main_tactics_id");
        Incidental_tactics_id = getArguments().getString("Incidental_tactics_id");
        stock_code = getArguments().getString("stock_code");
        currentIndex = getArguments().getInt("currentIndex");
    }

    @Override
    public void initView(View rootView) {

        mTimeLineView = (TimeLineView) rootView.findViewById(R.id.view_time_line);

        mTimeLineView.setDateFormat("yyyy-MM-dd");

    }

    @Override
    public void initAction() {
        mTimeLineView.setCelLueCallBack(new CelLueCallBack() {
            @Override
            public void mainCallback(String tactics_id) {
                main_tactics_id = tactics_id;
                getFiveDayDetialNet();
            }

            @Override
            public void fuTuCallback(String tactics_id) {
                Incidental_tactics_id = tactics_id;
                getFiveDayDetialNet();
            }
        });
    }


    private void getFiveDayDetialNet() {
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("Incidental_tactics_id", Incidental_tactics_id);
        params.put("main_tactics_id", main_tactics_id);
        params.put("stock_code", stock_code);

        addNetwork(Api.getInstance().getService(ApiService.class).getFiveDayDetialNet(params)
                , new Action1<StrategiesFiveTimeMFModel>() {
                    @Override
                    public void call(StrategiesFiveTimeMFModel model) {
                        hideLoadDialog();
                        if (model.data != null) {
//                            int count = 0;
//                            for (int i = 0; i < model.data.size(); i++) {
//                                if (model.data.get(i) != null && model.data.get(i).dapandata != null) {
////                                    count += model.data.get(i).dapandata.size();
//                                    if (count < model.data.get(i).dapandata.size()) {
//                                        count = model.data.get(i).dapandata.size();
//                                    }
//                                }
//                            }
//                            count = count * 5;
//                            mTimeLineView.setCount(count, count, count);
//                            final List<List<HisData>> hisData = Util.get5Day(model.data);
//                            Log.e("tag", "hisData=" + hisData.size());
//                            if (hisData.get(0) != null && hisData.get(0).get(0) != null)
//                                mTimeLineView.setLastClose(hisData.get(0).get(0).getClose());
//                            mTimeLineView.initDatas(hisData);

                            mTimeLineView.setDatas(model.data);
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
//        getNetData(tactics_id);
        getFiveDayDetialNet();
    }

    @Override
    public void onEvent(IEvent event) {
        super.onEvent(event);

//        if (event instanceof FuTuEvent) {
//            this.tactics_id = ((FuTuEvent) event).tactics_id;
//            if (currentIndex == ((FuTuEvent) event).currentIndex) {
//                getNetData(((FuTuEvent) event).tactics_id);
//            } else {
//                isInit = true;
//            }
//        }
    }

//    private void getNetData(String tactics_id) {
//        if (StockShowActivity.NO_TACTICS_ID.equals(tactics_id)) {
//            viewDrawingChart.setVisibility(View.GONE);
//            getFiveDayDetialNet();
//        } else {
//            viewDrawingChart.setVisibility(View.VISIBLE);
//            viewDrawingChart.setData(stock_code, tactics_id, 5);
//        }
//    }

    public void setCelueListData(StockDetailModel.StockDetailDataModel model) {
        mTimeLineView.setCelueListData(model.my_tactics, model.my_tactics_fu);
    }

}
