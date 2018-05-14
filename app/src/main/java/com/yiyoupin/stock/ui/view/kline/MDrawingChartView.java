package com.yiyoupin.stock.ui.view.kline;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.guoziwei.klinelib.chart.FuTuModel;
import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.base.RxManage;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.ui.base.BaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;


public class MDrawingChartView extends BaseView {


    private com.guoziwei.klinelib.chart.DrawingChartView mKLineView;
    private int type;
    private String tactics_id = "", stock_code = "";
    protected RxManage rxManage;


    public MDrawingChartView(Context context) {
        super(context);
    }

    public MDrawingChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MDrawingChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {
        rxManage = new RxManage();
    }

    @Override
    protected void initViews() {

        LayoutInflater.from(mContext).inflate(R.layout.fragment_drawing_chart, this, true);
        mKLineView = findViewById(R.id.kline);
        showVolume();
    }

    @Override
    protected void initActions() {
        mKLineView.setDateFormat("yyyy-MM-dd");
    }

    public void setData(String stock_code, String tactics_id, int type) {
//        FuTuModel model = Util.getDrawing(mContext);
        this.tactics_id = tactics_id;
        this.stock_code = stock_code;
        this.type = type;
        getFiveDayDetialNet();
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
        params.put("tactics_id", tactics_id);
        params.put("stock_code", stock_code);
        params.put("type", type + "");

        addNetwork(Api.getInstance().getService(ApiService.class).getFtNet(params)
                , new Action1<FuTuModel>() {
                    @Override
                    public void call(FuTuModel model) {
//                        hideLoadDialog();


                        List<FuTuModel.LineItemData> mListbb= model.data.stickline_bb.line_data;
                        List<FuTuModel.LineItemData> mListema = model.data.stickline_ema.line_data;

                        List<FuTuModel.LineItemData> minList = new ArrayList<>();

                        if (mListbb != null && mListema != null) {
                            Map<String, FuTuModel.LineItemData> minMap = new HashMap<>();
                            if (mListbb.size() > mListema.size()) {

                                for (int i = 0; i < mListema.size(); i++) {
                                    minMap.put(mListema.get(i).stickline_date, mListema.get(i));
                                }


                                for (int i = 0; i < mListbb.size(); i++) {
                                    if (minMap.containsKey(mListbb.get(i).stickline_date)) {
                                        minList.add(minMap.get(mListbb.get(i).stickline_date));
                                    }else{
                                        FuTuModel.LineItemData data = new FuTuModel.LineItemData();
                                        data.high=-1;
                                        data.low=-1;
                                        data.stickline_date  = mListbb.get(i).stickline_date;
                                        minList.add(data);
                                    }
                                }
                                model.data.stickline_ema.line_data = minList;

                            } else {


                                for (int i = 0; i < mListbb.size(); i++) {
                                    minMap.put(mListbb.get(i).stickline_date, mListbb.get(i));
                                }
                                for (int i = 0; i < mListema.size(); i++) {
                                    if (minMap.containsKey(mListema.get(i).stickline_date)) {
                                        minList.add(minMap.get(mListema.get(i).stickline_date));
                                    }else{
                                        FuTuModel.LineItemData data = new FuTuModel.LineItemData();
                                        data.high=-1;
                                        data.low=-1;
                                        data.stickline_date=mListema.get(i).stickline_date;
                                        minList.add(data);
                                    }
                                }
                                model.data.stickline_bb.line_data = minList;
                            }


                        }

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
                            hisData.add(0, d);
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
                            hisData1.add(0, d);
                        }

                        lists.add(hisData1);
                        model.lists = lists;

//                        int maxCount = hisData.size();
//                        if (hisData1.size() > hisData.size()) {
//                            maxCount = hisData1.size();
//                        }
//                        int initCount = 80;
//                        int minCount = 80;

//                        maxCount = 50;
//                        if (hisData.size() < 50) {
////            initCount = hisData.size();
////            minCount = hisData.size();
//                            maxCount = 50;
//                        }
//                        mKLineView.setCount(initCount, maxCount, minCount);
                        mKLineView.initData(model);
                        mKLineView.setLimitLine();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        hideLoadDialog();
                    }
                });
    }

    /**
     * 添加网络请求
     *
     * @param observable
     * @param action1
     */
    protected <T extends BaseModel> void addNetwork(rx.Observable<T> observable, Action1<T> action1, Action1<Throwable> error) {
        rxManage.add(observable, action1, error);
    }

    public void destroy() {
        rxManage.clear();//fragment销毁清除rxbus事件及网络请求，防止内存泄漏
    }
}
