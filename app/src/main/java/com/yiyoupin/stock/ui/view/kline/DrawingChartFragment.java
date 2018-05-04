package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guoziwei.klinelib.chart.DrawingChartView;
import com.guoziwei.klinelib.chart.FuTuModel;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.KLineModel;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class DrawingChartFragment extends BaseStockFragment {


    private DrawingChartView mKLineView;
    private int mDay;
    private String stock_id = "", tactics_id = "";

    public DrawingChartFragment() {
        // Required empty public constructor
    }

    public static DrawingChartFragment newInstance(int day, String stock_id, String tactics_id) {
        DrawingChartFragment fragment = new DrawingChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        bundle.putString("stock_id", stock_id);
        bundle.putString("tactics_id", tactics_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_drawing_chart;
    }

    @Override
    public void initDatas() {
        mDay = getArguments().getInt("day");
        stock_id = getArguments().getString("stock_id");
        tactics_id = getArguments().getString("tactics_id");
    }

    @Override
    public void initView(View rootView) {
        mKLineView = rootView.findViewById(R.id.kline);
        RadioGroup rgIndex = rootView.findViewById(R.id.rg_index);
//        rgIndex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.cb_vol) {
//                    showVolume();
//                } else if (checkedId == R.id.cb_macd) {
//                    showMacd();
//                } else if (checkedId == R.id.cb_kdj) {
//                    showKdj();
//                }
//            }
//        });
        showVolume();
//        ((RadioButton) rgIndex.getChildAt(0)).setChecked(true);
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
//                            mKLineView.initData(hisData);
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
//        getFiveDayDetialNet();


        FuTuModel model = Util.getDrawing(mContext);

        List<List<HisData>> lists = new ArrayList<>();
        List<HisData> hisData = new ArrayList<>();

        for (int i = 0; i < model.data.stickline_bb.line_data.size(); i++) {
            FuTuModel.LineItemData lineData = model.data.stickline_bb.line_data.get(i);

            HisData d = new HisData();
            d.setOpen(lineData.high);
            d.setClose(lineData.low);
            d.width = lineData.width;
            d.color = lineData.color;
            d.groupId = i;
            d.setDate(Util.getStringToDate(lineData.stickline_date));
            hisData.add(d);
        }


        lists.add(hisData);

        List<HisData> hisData1 = new ArrayList<>();
        for (int i = 0; i < model.data.stickline_ema.line_data.size(); i++) {
            FuTuModel.LineItemData lineData = model.data.stickline_ema.line_data.get(i);

            HisData d = new HisData();
            d.setOpen(lineData.high);
            d.setClose(lineData.low);
            d.width = lineData.width;
            d.color = lineData.color;
            d.groupId = i;
            d.setDate(Util.getStringToDate(lineData.stickline_date));
            hisData1.add(d);
        }

        lists.add(hisData1);
        model.lists = lists;

        int maxCount = hisData.size();
        int initCount = 50;
        int minCount = 50;

        maxCount=50;
        if (hisData.size() <50) {
//            initCount = hisData.size();
//            minCount = hisData.size();
            maxCount=50;
        }
        mKLineView.setCount(initCount, maxCount, minCount);
        mKLineView.initData(model);
        mKLineView.setLimitLine();
    }
}
