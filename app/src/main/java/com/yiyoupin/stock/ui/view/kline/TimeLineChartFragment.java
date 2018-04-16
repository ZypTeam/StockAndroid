package com.yiyoupin.stock.ui.view.kline;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.model.HisData;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.adapter.DetailsFragmentAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

import java.util.List;


public class TimeLineChartFragment extends BaseStockFragment {


    protected ViewPager viewpager;
    private TimeLineView mTimeLineView;
    private int mType;


    public static TimeLineChartFragment newInstance(int type) {
        TimeLineChartFragment fragment = new TimeLineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_time_sharing;
    }

    @Override
    public void initDatas() {
        mType = getArguments().getInt("type");
    }

    @Override
    public void initView(View rootView) {
        mTimeLineView = (TimeLineView) rootView.findViewById(R.id.time_line_view);
        mTimeLineView.setDateFormat("HH:mm");
        int count = 241;
        mTimeLineView.setCount(count, count, count);
//        initData();
        viewpager = (ViewPager) rootView.findViewById(R.id.viewpager);
    }

    @Override
    public void initAction() {
        viewpager.setAdapter(new DetailsFragmentAdapter(getChildFragmentManager()));
    }

    protected void initData() {


        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mTimeLineView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = (int) (Math.random() * 100);
                        HisData data = hisData.get(index);
                        HisData lastData = hisData.get(hisData.size() - 1);
                        HisData newData = new HisData();
                        newData.setVol(data.getVol());
                        newData.setClose(data.getClose());
                        newData.setHigh(Math.max(data.getHigh(), lastData.getClose()));
                        newData.setLow(Math.min(data.getLow(), lastData.getClose()));
                        newData.setOpen(lastData.getClose());
                        newData.setDate(System.currentTimeMillis());
                        hisData.add(newData);
                        mTimeLineView.addData(newData);
                    }
                });
            }
        }, 1000, 500);*/

       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mTimeLineView.post(new Runnable() {
                    @Override
                    public void run() {
                        mTimeLineView.refreshData((float) (hisData.get(0).getClose() + 10 * Math.random()));
                    }
                });
            }
        }, 1000, 1000);*/
    }

    @Override
    protected void refreshData() {
        final List<HisData> hisData = Util.get1Day(getContext());
        mTimeLineView.setLastClose(hisData.get(0).getClose());
        mTimeLineView.initData(hisData);
    }

    public void setData(List<HisData> hisData){
        mTimeLineView.setLastClose(hisData.get(0).getClose());
        mTimeLineView.initData(hisData);
    }
}
