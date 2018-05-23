package com.yiyoupin.stock.ui.view.kline;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.chart.my.CelLueCallBack;
import com.guoziwei.klinelib.chart.my.Util;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.Util.PhoneUtil;
import com.jusfoun.baselibrary.event.IEvent;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.MingXiModel;
import com.yiyoupin.stock.model.StockDetailModel;
import com.guoziwei.klinelib.chart.my.model.StrategiesTimeMFModel;
import com.yiyoupin.stock.ui.adapter.DetailsFragmentAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.event.FuTuEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


public class TimeLineChartFragment extends BaseStockFragment {


    protected ViewPager viewpager;
    protected ImageView imgStatus;
    protected LinearLayout layoutMingxi;
    protected TextView textFive;
    protected TextView textMingxi;
    private TimeLineView mTimeLineView;

    private int currentIndex = 0;

    private String main_tactics_id = "", stock_code = "";
    private String Incidental_tactics_id;

    public static TimeLineChartFragment newInstance(String stock_code, String tactics_id, int currentIndex) {
        TimeLineChartFragment fragment = new TimeLineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tactics_id", tactics_id);
        bundle.putString("stock_code", stock_code);
        bundle.putString("Incidental_tactics_id", "0");
        bundle.putInt("currentIndex", currentIndex);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_time_sharing;
    }

    @Override
    public void initDatas() {
        main_tactics_id = getArguments().getString("tactics_id");
        stock_code = getArguments().getString("stock_code");
        currentIndex = getArguments().getInt("currentIndex");
    }

    @Override
    public void initView(View rootView) {
        mTimeLineView = (TimeLineView) rootView.findViewById(R.id.time_line_view);
        mTimeLineView.setDateFormat("HH:mm");

//        initData();
        viewpager = (ViewPager) rootView.findViewById(R.id.viewpager);
        imgStatus = (ImageView) rootView.findViewById(R.id.img_status);
        layoutMingxi = (LinearLayout) rootView.findViewById(R.id.layout_mingxi);
        textFive = (TextView) rootView.findViewById(R.id.text_five);
        textMingxi = (TextView) rootView.findViewById(R.id.text_mingxi);
    }

    @Override
    public void initAction() {
        imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgStatus.getTag() == null || imgStatus.getTag().equals("open")) {
                    // close
                    imgStatus.setTag("close");
                    statrClose();
                    imgStatus.setImageResource(R.drawable.img_open);
                } else {
                    imgStatus.setTag("open");
                    statrOpen();
                    imgStatus.setImageResource(R.drawable.img_close);
                }
            }
        });

        textFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(0);
            }
        });
        textMingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(1);
            }
        });



        mTimeLineView.setCelLueCallBack(new CelLueCallBack() {
            @Override
            public void mainCallback(String tactics_id) {
              main_tactics_id = tactics_id;
              getDetailsNet();
            }

            @Override
            public void fuTuCallback(String tactics_id) {
                Incidental_tactics_id = tactics_id;
                getDetailsNet();
            }
        });
    }


    private void statrClose() {

        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(layoutMingxi, "translationX", 0, PhoneUtil.dip2px(mContext, 126));
        translationYAnimator.setDuration(500);
        translationYAnimator.setInterpolator(new DecelerateInterpolator());
        translationYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

        translationYAnimator.start();
    }

    private void statrOpen() {

        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(layoutMingxi, "translationX", PhoneUtil.dip2px(mContext, 126), 0);
        translationYAnimator.setDuration(500);
        translationYAnimator.setInterpolator(new DecelerateInterpolator());
        translationYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

        translationYAnimator.start();
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

//    @Override
//    protected void refreshData() {
////        final List<HisData> hisData = Util.get1Day(getContext());
////        mTimeLineView.setLastClose(hisData.get(0).getClose());
////        mTimeLineView.initData(hisData);
//    }

    public void setData(StockDetailModel.StockDetailDataModel model, String stockID) {

        if (model != null && model.dapandata != null && model.dapandata.size() > 0) {
            final List<HisData> hisData = Util.get1Day(model.dapandata);
            if (hisData.size() > 0)
                mTimeLineView.setLastClose(hisData.get(0).getClose());


            int count = 241;
//            if (hisData.size() < count) {
//                mTimeLineView.setCount(count, count, hisData.size());
//            } else {
//                mTimeLineView.setCount(count, count, count);
//            }
            mTimeLineView.initData(hisData);
        }

        if (model != null && model.stock_detail != null) {
            MingXiModel mingXiModel = new MingXiModel();
            List<MingXiModel.MingXiItemModel> list = new ArrayList<>();
            MingXiModel.MingXiItemModel oneModel = new MingXiModel.MingXiItemModel();
            oneModel.name = "卖1";
            oneModel.price = model.stock_detail.sellOnePri;
            oneModel.count = model.stock_detail.sellOne;
            list.add(oneModel);

            MingXiModel.MingXiItemModel twoModel = new MingXiModel.MingXiItemModel();
            twoModel.name = "卖2";
            twoModel.price = model.stock_detail.sellTwoPri;
            twoModel.count = model.stock_detail.sellTwo;
            list.add(twoModel);

            MingXiModel.MingXiItemModel threeModel = new MingXiModel.MingXiItemModel();
            threeModel.name = "卖3";
            threeModel.price = model.stock_detail.sellThreePri;
            threeModel.count = model.stock_detail.sellThree;
            list.add(threeModel);

            MingXiModel.MingXiItemModel fourModel = new MingXiModel.MingXiItemModel();
            fourModel.name = "卖4";
            fourModel.price = model.stock_detail.sellFourPri;
            fourModel.count = model.stock_detail.sellFour;
            list.add(fourModel);


            MingXiModel.MingXiItemModel fiveModel = new MingXiModel.MingXiItemModel();
            fiveModel.name = "卖5";
            fiveModel.price = model.stock_detail.sellFivePri;
            fiveModel.count = model.stock_detail.sellFive;
            list.add(fiveModel);


            MingXiModel.MingXiItemModel buyoneModel = new MingXiModel.MingXiItemModel();
            buyoneModel.name = "买1";
            buyoneModel.price = model.stock_detail.buyOnePri;
            buyoneModel.count = model.stock_detail.buyOne;
            list.add(buyoneModel);

            MingXiModel.MingXiItemModel buytwoModel = new MingXiModel.MingXiItemModel();
            buytwoModel.name = "买2";
            buytwoModel.price = model.stock_detail.buyTwoPri;
            buytwoModel.count = model.stock_detail.buyTwo;
            list.add(buytwoModel);


            MingXiModel.MingXiItemModel buythreeModel = new MingXiModel.MingXiItemModel();
            buythreeModel.name = "买3";
            buythreeModel.price = model.stock_detail.buyThreePri;
            buythreeModel.count = model.stock_detail.buyThree;
            list.add(buythreeModel);

            MingXiModel.MingXiItemModel buyfourModel = new MingXiModel.MingXiItemModel();
            buyfourModel.name = "买4";
            buyfourModel.price = model.stock_detail.buyFourPri;
            buyfourModel.count = model.stock_detail.buyFour;
            list.add(buyfourModel);

            MingXiModel.MingXiItemModel buyfiveModel = new MingXiModel.MingXiItemModel();
            buyfiveModel.name = "买5";
            buyfiveModel.price = model.stock_detail.buyFivePri;
            buyfiveModel.count = model.stock_detail.buyFive;
            list.add(buyfiveModel);

            mingXiModel.list = list;

            viewpager.setAdapter(new DetailsFragmentAdapter(getChildFragmentManager(), mingXiModel, stockID));


            mTimeLineView.setCelueListData(model.my_tactics,model.my_tactics_fu);
        }

    }

    @Override
    protected void refreshData() {
//        getNetData(main_tactics_id);
    }

    @Override
    public void onEvent(IEvent event) {
        super.onEvent(event);

        if (event instanceof FuTuEvent) {
            this.main_tactics_id = ((FuTuEvent) event).tactics_id;
            if (currentIndex == ((FuTuEvent) event).currentIndex) {
//                getNetData(((FuTuEvent) event).tactics_id);
                getDetailsNet();
            } else {
                isInit = true;
            }
        }
    }

//    private void getNetData(String tactics_id) {
//        if (StockShowActivity.NO_TACTICS_ID.equals(tactics_id)) {
//            viewDrawingChart.setVisibility(View.GONE);
//        } else {
//            viewDrawingChart.setVisibility(View.VISIBLE);
//            viewDrawingChart.setData(stock_code, tactics_id, 4);
//        }
//    }


    private void getDetailsNet() {

        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("main_tactics_id", main_tactics_id);
        params.put("Incidental_tactics_id", Incidental_tactics_id);
        params.put("stock_code", stock_code);


        addNetwork(Api.getInstance().getService(ApiService.class).getStockTimeSharingFX(params)
                , new Action1<StrategiesTimeMFModel>() {
                    @Override
                    public void call(StrategiesTimeMFModel model) {
//                        netError.setVisibility(View.GONE);
                        hideLoadDialog();


                        Log.e("tag","getDetailsNetgetDetailsNet1");
                        if (model != null && model.data != null ) {
//                            StrategiesTimeMFModel.StrategiesTimeMFMainMainDataModel mainDataModel = model.data.mainData;
//                            if (mainDataModel.type==1&&mainDataModel.data1!=null&&mainDataModel.data1.size()>0) {
//                                final List<HisData> hisData = Util.get1Day(mainDataModel.data1);
//                                if (hisData.size() > 0)
//                                    mTimeLineView.setLastClose(hisData.get(0).getClose());
//                                int count = 241;
//                                if (hisData.size() < count) {
//                                    mTimeLineView.setCount(count, count, hisData.size());
//                                } else {
//                                    mTimeLineView.setCount(count, count, count);
//                                }
//                                mTimeLineView.initData(hisData);
//                            }else if(mainDataModel.type==2&&mainDataModel.data2!=null){
//
//                            }
                            Log.e("tag","getDetailsNetgetDetailsNet2");
                            mTimeLineView.setData(model.data);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
//                        netError.setVisibility(View.VISIBLE);
                    }
                });
    }



}
