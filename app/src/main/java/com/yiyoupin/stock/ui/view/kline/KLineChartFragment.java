package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guoziwei.klinelib.chart.KLineView;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.event.IEvent;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.KLineModel;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.event.FuTuEvent;

import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class KLineChartFragment extends BaseStockFragment {


    protected MDrawingChartView viewDrawingChart;
    private KLineView mKLineView;
    private int mDay;
    private String stock_id = "", tactics_id = "", stock_code = "";
    private int currentIndex=0;

    public KLineChartFragment() {
        // Required empty public constructor
    }

    public static KLineChartFragment newInstance(int day, String stock_id, String tactics_id, String stock_code,int currentIndex) {
        KLineChartFragment fragment = new KLineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        bundle.putString("stock_id", stock_id);
        bundle.putString("tactics_id", tactics_id);
        bundle.putString("stock_code", stock_code);
        bundle.putInt("currentIndex", currentIndex);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_kline_chart;
    }

    @Override
    public void initDatas() {
        mDay = getArguments().getInt("day");
        stock_id = getArguments().getString("stock_id");
        tactics_id = getArguments().getString("tactics_id");
        stock_code = getArguments().getString("stock_code");
        currentIndex = getArguments().getInt("currentIndex");
    }

    @Override
    public void initView(View rootView) {
        mKLineView = rootView.findViewById(R.id.kline);
        RadioGroup rgIndex = rootView.findViewById(R.id.rg_index);
        rgIndex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cb_vol) {
                    showVolume();
                } else if (checkedId == R.id.cb_macd) {
                    showMacd();
                } else if (checkedId == R.id.cb_kdj) {
                    showKdj();
                }
            }
        });
        ((RadioButton) rgIndex.getChildAt(0)).setChecked(true);
        viewDrawingChart = (MDrawingChartView) rootView.findViewById(R.id.view_drawing_chart);
    }

    @Override
    public void initAction() {


        mKLineView.setDateFormat("yyyy-MM-dd");


    }

    public void showVolume() {

        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showVolume();
            }
        });
    }

    public void showMacd() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showMacd();
            }
        });
    }

    public void showKdj() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showKdj();
            }
        });
    }


    private void getFiveDayDetialNet() {
        HashMap<String, String> params = new HashMap();
        if (mDay == 1) {
            params.put("type", "1");
        } else if (mDay == 7) {
            params.put("type", "2");
        } else if (mDay == 30) {
            params.put("type", "3");
        }
        params.put("stock_id", stock_id);
        params.put("tactics_id", tactics_id);

        addNetwork(Api.getInstance().getService(ApiService.class).getKLineDetialNet(params)
                , new Action1<KLineModel>() {
                    @Override
                    public void call(KLineModel model) {
                        hideLoadDialog();
                        if (model.data != null) {

                            final List<HisData> hisData = Util.getK(model.data);
                            int maxCount = hisData.size();
                            int initCount = 80;
                            int minCount = 80;

                            if (hisData.size() < 80) {
                                initCount = hisData.size();
                                minCount = hisData.size();
                            }
                            mKLineView.setCount(initCount, maxCount, minCount);
                            mKLineView.initData(hisData);
                            mKLineView.setLimitLine();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }

    @Override
    protected void refreshData() {

        Log.e("tag","refreshDatarefreshData="+tactics_id);
        getNetData(tactics_id);
    }

    @Override
    public void onEvent(IEvent event) {
        super.onEvent(event);

        Log.e("tag","onEventonEvent1");
        if (event instanceof FuTuEvent) {
            this.tactics_id = ((FuTuEvent) event).tactics_id;
            if(currentIndex==((FuTuEvent) event).currentIndex) {
                getNetData(((FuTuEvent) event).tactics_id);
            }else{       Log.e("tag","onEventonEvent2");
                isInit=true;
            }
        }
    }

    private void getNetData(String tactics_id){
        if (StockShowActivity.NO_TACTICS_ID.equals(tactics_id)) {
            viewDrawingChart.setVisibility(View.GONE);
            getFiveDayDetialNet();
        } else {
            viewDrawingChart.setVisibility(View.VISIBLE);
            if (mDay == 1) {
                viewDrawingChart.setData(stock_code, tactics_id, 1);
            } else if (mDay == 7) {
                viewDrawingChart.setData(stock_code, tactics_id, 2);
            } else if (mDay == 30) {
                viewDrawingChart.setData(stock_code, tactics_id, 3);
            }
        }
    }
}