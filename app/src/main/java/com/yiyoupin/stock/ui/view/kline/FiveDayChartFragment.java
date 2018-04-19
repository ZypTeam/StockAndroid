package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.FiveDayModel;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class FiveDayChartFragment extends BaseStockFragment {


    private TimeLineView mTimeLineView;
    private String stock_id = "", tactics_id = "";


    public FiveDayChartFragment() {
        // Required empty public constructor
    }

    public static FiveDayChartFragment newInstance(String stock_id, String tactics_id) {
        FiveDayChartFragment fragment = new FiveDayChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("stock_id", stock_id);
        bundle.putString("tactics_id", tactics_id);
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
    }

    @Override
    public void initView(View rootView) {

        mTimeLineView = (TimeLineView) rootView.findViewById(R.id.view_time_line);

        mTimeLineView.setDateFormat("yyyy-MM-dd");
        int count = 241 * 5;
        mTimeLineView.setCount(count, count, count);


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
                            final List<List<HisData>> hisData = Util.get5Day(model.data);
                            if (hisData.get(0) != null && hisData.get(0).get(0) != null)
                                mTimeLineView.setLastClose(hisData.get(0).get(0).getClose());
                            if (hisData.size()>=5)
                                mTimeLineView.initDatas(hisData.get(0), hisData.get(1), hisData.get(2), hisData.get(3), hisData.get(4));
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
        getFiveDayDetialNet();
    }
}
