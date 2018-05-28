package com.guoziwei.klinelib.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.my.DrawingData;
import com.github.mikephil.charting.utils.Transformer;
import com.guoziwei.klinelib.R;
import com.guoziwei.klinelib.chart.my.CelLueCallBack;
import com.guoziwei.klinelib.chart.my.CelueMenuView;
import com.guoziwei.klinelib.chart.my.Util;
import com.guoziwei.klinelib.chart.my.model.FuAllModel;
import com.guoziwei.klinelib.chart.my.model.LineModel;
import com.guoziwei.klinelib.chart.my.model.StrategiesFiveTimeMFModel;
import com.guoziwei.klinelib.chart.my.model.StrategiesTimeMFModel;
import com.guoziwei.klinelib.model.HisData;
import com.guoziwei.klinelib.util.DataUtils;
import com.guoziwei.klinelib.util.DateUtils;
import com.guoziwei.klinelib.util.DisplayUtils;
import com.guoziwei.klinelib.util.DoubleUtil;
import com.guoziwei.klinelib.view.CeluePopupWindow;
import com.guoziwei.klinelib.view.MyTacticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * kline
 * Created by guoziwei on 2017/10/26.
 */
public class TimeLineView extends BaseView implements CoupleChartGestureListener.OnAxisChangeListener {


    public static final int NORMAL_LINE = 0;

    public static final int NORMAL_LINE_5DAY = 5;
    /**
     * average line
     */
    public static final int AVE_LINE = 1;
    /**
     * hide line
     */
    public static final int INVISIABLE_LINE = 6;

    protected AppCombinedChart mChartPrice;
    protected AppCombinedChart mCulueChartPrice;
    protected AppCombinedChart mChartVolume;

    protected AppCombinedChart mChartFu;
    protected AppCombinedChart mChartMacd;

    protected ChartInfoView mChartInfoView;
    protected Context mContext;


    /**
     * last price
     */
    private double mLastPrice;

    /**
     * yesterday close price
     */
    private double mLastClose;

    /**
     * the digits of the symbol
     */
    private int mDigits = 2;

    private CeluePopupWindow fuCeluePopupWindow, mainCeluePopupWindow;

    private CelueMenuView mainMenuView, fuMenuView;


    public static final int MA5 = 5;
    public static final int MA10 = 10;
    public static final int MA20 = 20;
    public static final int MA30 = 30;
    public static final int DIF = 34;
    public static final int DEA = 35;

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_timeline, this);
        mChartPrice = (AppCombinedChart) findViewById(R.id.price_chart);
        mCulueChartPrice = (AppCombinedChart) findViewById(R.id.price_chart_celue);
        mChartFu = (AppCombinedChart) findViewById(R.id.fu_chart);
        mChartMacd = (AppCombinedChart) findViewById(R.id.macd_chart);

        mChartVolume = (AppCombinedChart) findViewById(R.id.vol_chart);
        mChartInfoView = (ChartInfoView) findViewById(R.id.line_info);

        mainMenuView = (CelueMenuView) findViewById(R.id.view_meun_main);
        fuMenuView = (CelueMenuView) findViewById(R.id.view_meun_fu);

//        mainMDrawingChartView = (MDrawingChartView)findViewById(R.id.view_drawing_chart);
//        fuMDrawingChartView = (MDrawingChartView)findViewById(R.id.view_drawing_chart_fu);

        mChartInfoView.setChart(mChartPrice, mChartVolume, mCulueChartPrice, mChartFu, mChartMacd);

        mChartPrice.setNoDataText(context.getString(R.string.loading));
        initChartPrice();
        initCelueMainChartPrice();

        initBottomChart(mChartVolume);
        initBottomChart(mChartMacd);
        initBottomChart(mChartFu);

        setOffset();
        initChartListener();

        fuCeluePopupWindow = new CeluePopupWindow(mContext);
        mainCeluePopupWindow = new CeluePopupWindow(mContext);


        fuMenuView.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuCeluePopupWindow.showAsDropDown(fuMenuView);


            }
        });

        fuCeluePopupWindow.setCallBack(new CeluePopupWindow.CallBack() {
            @Override
            public void onClick(MyTacticsModel model) {
                fuCeluePopupWindow.dismiss();
//                FuTuEvent event = new FuTuEvent();
//                event.currentIndex = viewPager.getCurrentItem();
//                event.tactics_id = model.tactics_id;

                if (celLueCallBack != null) {
                    celLueCallBack.fuTuCallback(model.tactics_id);
                }
                fuMenuView.setTitleText(model.tactics_name);
            }
        });


        mainMenuView.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("tag", "layoutCeluelayoutCelue");
                mainCeluePopupWindow.showAsDropDown(mainMenuView);
            }
        });

        mainCeluePopupWindow.setCallBack(new CeluePopupWindow.CallBack() {
            @Override
            public void onClick(MyTacticsModel model) {
                mainCeluePopupWindow.dismiss();
//                FuTuEvent event = new FuTuEvent();
//                event.currentIndex = viewPager.getCurrentItem();
//                event.tactics_id = model.tactics_id;

                Log.e("tag", "mainCeluePopupWindow1");
                if (celLueCallBack != null) {
                    Log.e("tag", "mainCeluePopupWindow2");
                    celLueCallBack.mainCallback(model.tactics_id);
                }
                mainMenuView.setTitleText(model.tactics_name);
            }
        });
    }


    protected void initChartPrice() {
        mChartPrice.setScaleEnabled(true);
        mChartPrice.setDrawBorders(false);
        mChartPrice.setBorderWidth(1);
        mChartPrice.setDragEnabled(true);
        mChartPrice.setScaleYEnabled(false);
        mChartPrice.getDescription().setEnabled(false);
        mChartPrice.setAutoScaleMinMaxEnabled(true);
        mChartPrice.setDragDecelerationEnabled(false);
        LineChartXMarkerView mvx = new LineChartXMarkerView(mContext, mData);
        mvx.setChartView(mChartPrice);
        mChartPrice.setXMarker(mvx);
        Legend lineChartLegend = mChartPrice.getLegend();
        lineChartLegend.setEnabled(false);

        XAxis xAxisPrice = mChartPrice.getXAxis();
        xAxisPrice.setDrawLabels(false);
        xAxisPrice.setDrawAxisLine(false);
        xAxisPrice.setDrawGridLines(false);
        xAxisPrice.setAxisMinimum(-0.5f);


        YAxis axisLeftPrice = mChartPrice.getAxisLeft();
        axisLeftPrice.setLabelCount(5, true);
        axisLeftPrice.setDrawLabels(true);
        axisLeftPrice.setDrawGridLines(false);

        axisLeftPrice.setDrawAxisLine(false);
        axisLeftPrice.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftPrice.setTextColor(mAxisColor);
        axisLeftPrice.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                Log.e("tag", "getFormattedValue=" + value + " " + DoubleUtil.getStringByDigits(value, mDigits));
                return DoubleUtil.getStringByDigits(value, mDigits);
            }
        });

        int[] colorArray = {mDecreasingColor, mDecreasingColor, mAxisColor, mIncreasingColor, mIncreasingColor};
        Transformer leftYTransformer = mChartPrice.getRendererLeftYAxis().getTransformer();
        ColorContentYAxisRenderer leftColorContentYAxisRenderer = new ColorContentYAxisRenderer(mChartPrice.getViewPortHandler(), mChartPrice.getAxisLeft(), leftYTransformer);
        leftColorContentYAxisRenderer.setLabelColor(colorArray);
        leftColorContentYAxisRenderer.setLabelInContent(true);
        leftColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        mChartPrice.setRendererLeftYAxis(leftColorContentYAxisRenderer);


        YAxis axisRightPrice = mChartPrice.getAxisRight();
        axisRightPrice.setLabelCount(5, true);
        axisRightPrice.setDrawLabels(true);
        axisRightPrice.setDrawGridLines(false);
        axisRightPrice.setDrawAxisLine(false);
        axisRightPrice.setTextColor(mAxisColor);
        axisRightPrice.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        axisRightPrice.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                double rate = (value - mLastClose) / mLastClose * 100;
                if (Double.isNaN(rate) || Double.isInfinite(rate)) {
                    return "";
                }
                String s = String.format(Locale.getDefault(), "%.2f%%",
                        rate);
                if (TextUtils.equals("-0.00%", s)) {
                    return "0.00%";
                }
                return s;
            }
        });

//        设置标签Y渲染器
        Transformer rightYTransformer = mChartPrice.getRendererRightYAxis().getTransformer();
        ColorContentYAxisRenderer rightColorContentYAxisRenderer = new ColorContentYAxisRenderer(mChartPrice.getViewPortHandler(), mChartPrice.getAxisRight(), rightYTransformer);
        rightColorContentYAxisRenderer.setLabelInContent(true);
        rightColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        rightColorContentYAxisRenderer.setLabelColor(colorArray);
        mChartPrice.setRendererRightYAxis(rightColorContentYAxisRenderer);

    }


    private void initChartListener() {
        mChartPrice.setOnChartGestureListener(new CoupleChartGestureListener(this, mChartPrice, mChartVolume, mCulueChartPrice, mChartFu, mChartMacd));
        mCulueChartPrice.setOnChartGestureListener(new CoupleChartGestureListener(this, mCulueChartPrice, mChartPrice, mChartVolume, mChartFu, mChartMacd));
        mChartVolume.setOnChartGestureListener(new CoupleChartGestureListener(this, mChartVolume, mChartPrice, mCulueChartPrice, mChartFu, mChartMacd));
        mChartFu.setOnChartGestureListener(new CoupleChartGestureListener(this, mChartFu, mChartPrice, mChartVolume, mCulueChartPrice, mChartMacd));
        mChartMacd.setOnChartGestureListener(new CoupleChartGestureListener(this, mChartFu, mChartPrice, mChartVolume, mCulueChartPrice, mChartFu));


        mChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartVolume, mCulueChartPrice, mChartFu, mChartMacd));
        mCulueChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartVolume, mChartPrice, mChartFu, mChartMacd));
        mChartVolume.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartFu, mChartMacd));
        mChartFu.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartVolume, mChartMacd));
        mChartMacd.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartVolume, mChartFu));


        mChartPrice.setOnTouchListener(new ChartInfoViewHandler(mChartPrice));
        mChartVolume.setOnTouchListener(new ChartInfoViewHandler(mChartVolume));
        mCulueChartPrice.setOnTouchListener(new ChartInfoViewHandler(mCulueChartPrice));
        mChartFu.setOnTouchListener(new ChartInfoViewHandler(mChartFu));
        mChartMacd.setOnTouchListener(new ChartInfoViewHandler(mChartMacd));
    }


    public void initData(List<HisData> hisDatas) {
        initNormalData(hisDatas);
        initChartVolumeData(hisDatas);
    }


    public void initNormalData(List<HisData> hisDatas) {

        mData.clear();
        mData.addAll(DataUtils.calculateHisData(hisDatas));

        ArrayList<Entry> priceEntries = new ArrayList<>();
        ArrayList<Entry> aveEntries = new ArrayList<>();
        ArrayList<Entry> paddingEntries = new ArrayList<>();

        for (int i = 0; i < mData.size(); i++) {
            priceEntries.add(new Entry(i, (float) hisDatas.get(i).getClose()));
            aveEntries.add(new Entry(i, (float) hisDatas.get(i).getAvePrice()));
            Log.e("tag", "priceEntriespriceEntries=" + mData.get(i).getClose() + " " + mData.get(i).getAvePrice());
        }

//        if (!mData.isEmpty() && mData.size() < MAX_COUNT) {
//            for (int i = mData.size(); i < MAX_COUNT; i++) {
//                paddingEntries.add(new Entry(i, (float) mData.get(mData.size() - 1).getClose()));
//            }
//        }
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(setLine(NORMAL_LINE, priceEntries));
        sets.add(setLine(AVE_LINE, aveEntries));
//        sets.add(setLine(INVISIABLE_LINE, paddingEntries));
        LineData lineData = new LineData(sets);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        mChartPrice.setData(combinedData);

        mChartPrice.setVisibleXRange(MAX_COUNT, MIN_COUNT);

        mChartPrice.notifyDataSetChanged();
//        mChartPrice.moveViewToX(combinedData.getEntryCount());
        moveToLast(mChartPrice);

        mChartPrice.getXAxis().setAxisMaximum(combinedData.getXMax() + 0.5f);
//        mChartPrice.resetZoom();
        mChartPrice.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        setDescription(mChartVolume, "成交量 " + getLastData().getVol());
    }


    public void setDatas(StrategiesFiveTimeMFModel.StrategiesTimeMFDataModel model) {
        if (model.mainData != null) {
            if (model.mainData.type == 1) {
                List<List<HisData>> hisDatas = Util.get5Day(model.mainData.data1);
                int count = 0;
                for (int i = 0; i < hisDatas.size(); i++) {
                    count += hisDatas.get(i).size();
                }
                setCount(count, count, count);

                Log.e("tag", "setDatassetDatas111");
                initDatas(hisDatas);

            } else if (model.mainData.type == 2) {
                drawFus(model.mainData.data2, mCulueChartPrice, true);
            }
        }
        if (model.incidentalData != null) {
            if (model.incidentalData.type == 1) {
                List<LineModel> list = new ArrayList<>();
                if (model.incidentalData.data1 != null) {
                    for (int i = 0; i < model.incidentalData.data1.size(); i++) {
                        if (model.incidentalData.data1.get(i).dapandata != null)
                            list.addAll(model.incidentalData.data1.get(i).dapandata);
                    }
                }
//                List<HisData> hisData = Util.getK(model.incidentalData.data1);
                List<HisData> hisData = Util.get1DayVolume(list);

                initChartVolumeData(hisData);
            } else if (model.incidentalData.type == 2) {
                drawFus(model.incidentalData.data2, mChartFu, false);
            } else if (model.incidentalData.type == 3) {
                final List<HisData> hisData = Util.getK(model.incidentalData.data3);
                initChartMacdData(hisData);
            }
        }

    }

    public void initDatas(List<List<HisData>> hisDatas) {
//
//        int indexX = 0;
//        // 设置标签数量，并让标签居中显示
//        XAxis xAxis = mChartVolume.getXAxis();
//        xAxis.setLabelCount(hisDatas.size() + 1, true);
//        xAxis.setAvoidFirstLastClipping(false);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                value += 1; // 这里不设置+1会有bug
//                Log.e("tag", "DateUtil===s=" + value);
//                if (mData.isEmpty()) {
//                    return "";
//                }
//                if (value < 0) {
//                    value = 0;
//                }
//                if (value < mData.size()) {
//                    return DateUtils.formatDate(mData.get((int) value).getDate(), mDateFormat);
//                }
//                return "";
//            }
//        });
//        mData.clear();



        ArrayList<ILineDataSet> sets = new ArrayList<>();
        ArrayList<IBarDataSet> barSets = new ArrayList<>();

        int count = 0;
        for (List<HisData> hisData : hisDatas) {

            hisData = DataUtils.calculateHisData(hisData);
            ArrayList<Entry> priceEntries = new ArrayList<>(INIT_COUNT);
            ArrayList<Entry> aveEntries = new ArrayList<>(INIT_COUNT);
//            ArrayList<Entry> paddingEntries = new ArrayList<>(INIT_COUNT);
//            ArrayList<BarEntry> barPaddingEntries = new ArrayList<>(INIT_COUNT);
//            ArrayList<BarEntry> barEntries = new ArrayList<>(INIT_COUNT);

            for (int i = 0; i < hisData.size(); i++) {
                HisData t = hisData.get(i);
                priceEntries.add(new Entry(i + count, (float) t.getClose()));
                aveEntries.add(new Entry(i + count, (float) t.getAvePrice()));
//                barEntries.add(new BarEntry(i + mData.size(), (float) t.getVol(), t));
            }
            count += hisData.size();
//            if (!hisData.isEmpty() && hisData.size() < INIT_COUNT / hisDatas.size()) {
//                for (int i = hisData.size(); i < INIT_COUNT / hisDatas.size(); i++) {
//                    paddingEntries.add(new Entry(i, (float) hisData.get(hisData.size() - 1).getClose()));
//                    barPaddingEntries.add(new BarEntry(i, (float) hisData.get(hisData.size() - 1).getClose()));
//                }
//            }
            sets.add(setLine(NORMAL_LINE_5DAY, priceEntries));
            sets.add(setLine(AVE_LINE, aveEntries));
//            sets.add(setLine(INVISIABLE_LINE, paddingEntries));
//            barSets.add(setBar(barEntries, NORMAL_LINE));
//            barSets.add(setBar(barPaddingEntries, INVISIABLE_LINE));
//            barSets.add(setBar(barPaddingEntries, INVISIABLE_LINE));

            Log.e("tag", "hisDatahisData=" + hisData.get(0).getDate());
            mData.addAll(hisData);

            Log.e("tag", "hisDatas111=");
        }

        LineData lineData = new LineData(sets);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        mChartPrice.setData(combinedData);
        mChartPrice.setVisibleXRange(MAX_COUNT, MIN_COUNT);
        mChartPrice.notifyDataSetChanged();
//        mChartPrice.moveViewToX(combinedData.getEntryCount());
//        moveToLast(mChartVolume);


//        BarData barData = new BarData(barSets);
//        barData.setBarWidth(0.75f);
//        CombinedData combinedData2 = new CombinedData();
//        combinedData2.setData(barData);
//        mChartVolume.setData(combinedData2);
//        mChartVolume.setVisibleXRange(MAX_COUNT, MIN_COUNT);
//        mChartVolume.notifyDataSetChanged();
//        mChartVolume.moveViewToX(combinedData2.getEntryCount());


        mChartPrice.getXAxis().setAxisMaximum(combinedData.getXMax() + 0.5f);
//        mChartVolume.getXAxis().setAxisMaximum(mChartVolume.getData().getXMax() + 0.5f);

        mChartPrice.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
//        mChartVolume.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        setDescription(mChartVolume, "成交量 " + getLastData().getVol());
    }


    private BarDataSet setBar(ArrayList<BarEntry> barEntries, int type) {
        BarDataSet barDataSet = new BarDataSet(barEntries, "vol");
        barDataSet.setHighLightAlpha(120);
        barDataSet.setHighLightColor(getResources().getColor(R.color.highlight_color));
        barDataSet.setDrawValues(false);
        barDataSet.setVisible(type != INVISIABLE_LINE);
        barDataSet.setHighlightEnabled(type != INVISIABLE_LINE);
        barDataSet.setColors(getResources().getColor(R.color.increasing_color), getResources().getColor(R.color.decreasing_color));
        return barDataSet;
    }


    @android.support.annotation.NonNull
    private LineDataSet setLine(int type, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + type);
        lineDataSetMa.setDrawValues(false);
        if (type == NORMAL_LINE) {
            lineDataSetMa.setColor(getResources().getColor(R.color.normal_line_color));
            lineDataSetMa.setCircleColor(ContextCompat.getColor(mContext, R.color.normal_line_color));
        } else if (type == NORMAL_LINE_5DAY) {
            lineDataSetMa.setColor(getResources().getColor(R.color.normal_line_color));
            lineDataSetMa.setCircleColor(ContextCompat.getColor(mContext, R.color.normal_line_color));
        } else if (type == DIF) {
            lineDataSetMa.setColor(getResources().getColor(R.color.dif));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == DEA) {
            lineDataSetMa.setColor(getResources().getColor(R.color.dea));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == AVE_LINE) {
            lineDataSetMa.setColor(getResources().getColor(R.color.ave_color));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else {
            lineDataSetMa.setVisible(false);
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setCircleRadius(1f);

        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setDrawCircleHole(false);

        return lineDataSetMa;
    }


    private LineDataSet initLineAndColor(int type, ArrayList<Entry> lineEntries, String color) {
        if (TextUtils.isEmpty(color)) {
            color = "#ffffff";
        }
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + type);
        lineDataSetMa.setDrawValues(false);

        lineDataSetMa.setColor(Color.parseColor(color));
        lineDataSetMa.setCircleColor(ContextCompat.getColor(mContext, R.color.normal_line_color));


        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setCircleRadius(1f);

        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setDrawCircleHole(false);

        return lineDataSetMa;
    }


    private void initChartVolumeData(List<HisData> hisDatas) {
        mChartVolume.setVisibility(VISIBLE);
        mChartFu.setVisibility(GONE);
        mChartMacd.setVisibility(GONE);

        mData.clear();
        mData.addAll(DataUtils.calculateHisData(hisDatas));


        XAxis xAxis = mChartVolume.getXAxis();
        xAxis.setLabelCount(hisDatas.size() + 1, true);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value += 1; // 这里不设置+1会有bug
                Log.e("tag", "DateUtil===s=" + value);
                if (mData.isEmpty()) {
                    return "";
                }
                if (value < 0) {
                    value = 0;
                }
                if (value < mData.size()) {
                    return DateUtils.formatDate(mData.get((int) value).getDate(), mDateFormat);
                }
                return "";
            }
        });


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarEntry> paddingEntries = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            HisData t = mData.get(i);
            barEntries.add(new BarEntry(i, (float) t.getVol(), t));
        }
        int maxCount = MAX_COUNT;
        if (!mData.isEmpty() && mData.size() < maxCount) {
            for (int i = mData.size(); i < maxCount; i++) {
                paddingEntries.add(new BarEntry(i, 0));
            }
        }

        BarData barData = new BarData(setBar(barEntries, NORMAL_LINE), setBar(paddingEntries, INVISIABLE_LINE));
        barData.setBarWidth(0.75f);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);
        mChartVolume.setData(combinedData);

        mChartVolume.setVisibleXRange(MAX_COUNT, MIN_COUNT);

        mChartVolume.notifyDataSetChanged();
        mChartVolume.moveViewToX(combinedData.getEntryCount());

        mChartVolume.getXAxis().setAxisMaximum(mChartVolume.getData().getXMax() + 0.5f);
        mChartVolume.resetZoom();
        mChartVolume.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
        HisData hisData = getLastData();
//        setDescription(mChartVolume, "成交量 " + hisData.getVol());

    }


    /**
     * according to the price to refresh the last data of the chart
     */
    public void refreshData(float price) {
        if (price <= 0 || price == mLastPrice) {
            return;
        }
        mLastPrice = price;
        CombinedData data = mChartPrice.getData();
        if (data == null) return;
        LineData lineData = data.getLineData();
        if (lineData != null) {
            ILineDataSet set = lineData.getDataSetByIndex(0);
            if (set.removeLast()) {
                set.addEntry(new Entry(set.getEntryCount(), price));
            }
        }

        mChartPrice.notifyDataSetChanged();
        mChartPrice.invalidate();
    }


    public void addData(HisData hisData) {
        hisData = DataUtils.calculateHisData(hisData, mData);
        CombinedData combinedData = mChartPrice.getData();
        LineData priceData = combinedData.getLineData();
        ILineDataSet priceSet = priceData.getDataSetByIndex(0);
        ILineDataSet aveSet = priceData.getDataSetByIndex(1);
        IBarDataSet volSet = mChartVolume.getData().getBarData().getDataSetByIndex(0);
        if (mData.contains(hisData)) {
            int index = mData.indexOf(hisData);
            priceSet.removeEntry(index);
            aveSet.removeEntry(index);
            volSet.removeEntry(index);
            mData.remove(index);
        }
        mData.add(hisData);
        priceSet.addEntry(new Entry(priceSet.getEntryCount(), (float) hisData.getClose()));
        aveSet.addEntry(new Entry(aveSet.getEntryCount(), (float) hisData.getAvePrice()));
        volSet.addEntry(new BarEntry(volSet.getEntryCount(), hisData.getVol(), hisData));

        mChartPrice.setVisibleXRange(MAX_COUNT, MIN_COUNT);
        mChartVolume.setVisibleXRange(MAX_COUNT, MIN_COUNT);

        mChartPrice.getXAxis().setAxisMaximum(combinedData.getXMax() + 1.5f);
        mChartVolume.getXAxis().setAxisMaximum(mChartVolume.getData().getXMax() + 1.5f);

        mChartPrice.notifyDataSetChanged();
        mChartPrice.invalidate();
        mChartVolume.notifyDataSetChanged();
        mChartVolume.invalidate();

//        setDescription(mChartVolume, "成交量 " + hisData.getVol());
    }


    /**
     * align two chart
     */
    private void setOffset() {
        int chartHeight = getResources().getDimensionPixelSize(R.dimen.bottom_chart_height_celue);
        mChartPrice.setViewPortOffsets(0, 0, 0, chartHeight);
        mCulueChartPrice.setViewPortOffsets(0, 0, 0, chartHeight);
        mChartVolume.setViewPortOffsets(0, 0, 0, DisplayUtils.dip2px(mContext, 20));
        mChartFu.setViewPortOffsets(0, 0, 0, DisplayUtils.dip2px(mContext, 20));
        mChartMacd.setViewPortOffsets(0, 0, 0, DisplayUtils.dip2px(mContext, 20));


        /*float lineLeft = mChartPrice.getViewPortHandler().offsetLeft();
        float barLeft = mChartVolume.getViewPortHandler().offsetLeft();
        float lineRight = mChartPrice.getViewPortHandler().offsetRight();
        float barRight = mChartVolume.getViewPortHandler().offsetRight();
        float offsetLeft, offsetRight;
        if (barLeft < lineLeft) {
            offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            mChartVolume.setExtraLeftOffset(offsetLeft);
        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            mChartPrice.setExtraLeftOffset(offsetLeft);
        }
        if (barRight < lineRight) {
            offsetRight = Utils.convertPixelsToDp(lineRight);
            mChartVolume.setExtraRightOffset(offsetRight);
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            mChartPrice.setExtraRightOffset(offsetRight);
        }*/

    }


    /**
     * add limit line to chart
     */
    public void setLimitLine(double lastClose) {
        LimitLine limitLine = new LimitLine((float) lastClose);
        limitLine.enableDashedLine(5, 10, 0);
        limitLine.setLineColor(getResources().getColor(R.color.limit_color));
        mChartPrice.getAxisLeft().addLimitLine(limitLine);
        mCulueChartPrice.getAxisLeft().addLimitLine(limitLine);

    }

    public void setLimitLine() {
        setLimitLine(mLastClose);
    }

    public void setLastClose(double lastClose) {
        mLastClose = lastClose;
        mChartPrice.setYCenter((float) lastClose);
        mCulueChartPrice.setYCenter((float) lastClose);
        mChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartVolume, mCulueChartPrice, mChartFu, mChartMacd));
        mCulueChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartVolume, mChartPrice, mChartFu, mChartMacd));
        mChartVolume.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartFu, mChartMacd));
        mChartFu.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartVolume, mChartMacd));
        mChartMacd.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mCulueChartPrice, mChartVolume, mChartFu));
    }


    public HisData getLastData() {
        if (mData != null && !mData.isEmpty()) {
            return mData.get(mData.size() - 1);
        }
        return null;
    }


    @Override
    public void onAxisChange(BarLineChartBase chart) {
        float lowestVisibleX = chart.getLowestVisibleX();
        if (lowestVisibleX <= chart.getXAxis().getAxisMinimum()) return;
        int maxX = (int) chart.getHighestVisibleX();
        int x = Math.min(maxX, mData.size() - 1);
        HisData hisData = mData.get(x < 0 ? 0 : x);
//        setDescription(mChartVolume, "成交量 " + hisData.getVol());
    }


    protected void initCelueMainChartPrice() {
        mCulueChartPrice.setScaleEnabled(true);
        mCulueChartPrice.setScaleXEnabled(true);
        mCulueChartPrice.setDrawBorders(false);
        mCulueChartPrice.setBorderWidth(1);
        mCulueChartPrice.setDragEnabled(true);
        mCulueChartPrice.setScaleYEnabled(false);
        mCulueChartPrice.setAutoScaleMinMaxEnabled(true);
        mCulueChartPrice.setDragDecelerationEnabled(false);
        LineChartXMarkerView mvx = new LineChartXMarkerView(mContext, mData);
        mvx.setChartView(mCulueChartPrice);
        mCulueChartPrice.setXMarker(mvx);
        Legend lineChartLegend = mCulueChartPrice.getLegend();
        lineChartLegend.setEnabled(false);

        XAxis xAxisPrice = mCulueChartPrice.getXAxis();
        xAxisPrice.setDrawLabels(false);
        xAxisPrice.setDrawAxisLine(false);
        xAxisPrice.setDrawGridLines(false);
        xAxisPrice.setAxisMinimum(-0.5f);
        xAxisPrice.setGranularity(5f);
        xAxisPrice.setGranularityEnabled(true);


        YAxis axisLeftPrice = mCulueChartPrice.getAxisLeft();
        axisLeftPrice.setLabelCount(5, true);
        axisLeftPrice.setDrawLabels(true);
        axisLeftPrice.setDrawGridLines(false);

        axisLeftPrice.setDrawAxisLine(false);
        axisLeftPrice.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftPrice.setTextColor(mAxisColor);
        axisLeftPrice.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return DoubleUtil.getStringByDigits(value, mDigits);
            }
        });

        int[] colorArray = {mDecreasingColor, mDecreasingColor, mAxisColor, mIncreasingColor, mIncreasingColor};
        Transformer leftYTransformer = mCulueChartPrice.getRendererLeftYAxis().getTransformer();
        ColorContentYAxisRenderer leftColorContentYAxisRenderer = new ColorContentYAxisRenderer(mCulueChartPrice.getViewPortHandler(), mCulueChartPrice.getAxisLeft(), leftYTransformer);
        leftColorContentYAxisRenderer.setLabelColor(colorArray);
        leftColorContentYAxisRenderer.setLabelInContent(true);
        leftColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        mCulueChartPrice.setRendererLeftYAxis(leftColorContentYAxisRenderer);


        YAxis axisRightPrice = mCulueChartPrice.getAxisRight();
        axisRightPrice.setLabelCount(5, true);
        axisRightPrice.setDrawLabels(true);

        axisRightPrice.setDrawGridLines(false);
        axisRightPrice.setDrawAxisLine(false);
        axisRightPrice.setTextColor(mAxisColor);
        axisRightPrice.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        axisRightPrice.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                double rate = (value - mLastClose) / mLastClose * 100;
                if (Double.isNaN(rate) || Double.isInfinite(rate)) {
                    return "";
                }
                String s = String.format(Locale.getDefault(), "%.2f%%",
                        rate);
                if (TextUtils.equals("-0.00%", s)) {
                    return "0.00%";
                }
                return s;
            }
        });

//        设置标签Y渲染器
        Transformer rightYTransformer = mCulueChartPrice.getRendererRightYAxis().getTransformer();
        ColorContentYAxisRenderer rightColorContentYAxisRenderer = new ColorContentYAxisRenderer(mCulueChartPrice.getViewPortHandler(), mCulueChartPrice.getAxisRight(), rightYTransformer);
        rightColorContentYAxisRenderer.setLabelInContent(true);
        rightColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        rightColorContentYAxisRenderer.setLabelColor(colorArray);
        mCulueChartPrice.setRendererRightYAxis(rightColorContentYAxisRenderer);
    }

    public void setData(StrategiesTimeMFModel.StrategiesTimeMFDataModel model) {

        if (model.mainData != null) {
            if (model.mainData.type == 1) {
                final List<HisData> hisData = Util.get1Day(model.mainData.data1);
                initNormalData(hisData);
            } else if (model.mainData.type == 2) {
                drawFus(model.mainData.data2, mCulueChartPrice, true);
            }
        }
        if (model.incidentalData != null) {
            if (model.incidentalData.type == 1) {
                List<HisData> hisData = Util.get1Day(model.incidentalData.data1);
                initChartVolumeData(hisData);
            } else if (model.incidentalData.type == 2) {
                drawFus(model.incidentalData.data2, mChartFu, false);
            } else if (model.incidentalData.type == 3) {
                final List<HisData> hisData = Util.getK(model.incidentalData.data3);
                initChartMacdData(hisData);
            }
        }

    }

    public void drawFu(FuTuModel.FutuData futuData, AppCombinedChart appCombinedChart, boolean isMain) {
        appCombinedChart.setVisibility(VISIBLE);
        if (isMain) {
            mChartPrice.setVisibility(GONE);
        } else {
            mChartVolume.setVisibility(GONE);
            mChartMacd.setVisibility(GONE);
        }

        FuTuModel model = new FuTuModel();
        model.data = futuData;
        model = DataUtils.detailData(model);

        List<List<HisData>> hisDatas = model.lists;

        Log.e("tag", "hisDatas===" + hisDatas.size());
//        mData.clear();
//        mData.addAll();

//        ArrayList<CandleEntry> lineCJEntries = new ArrayList<>(INIT_COUNT);


//        ArrayList<Entry> ma5Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma10Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma20Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma30Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> paddingEntries = new ArrayList<>(INIT_COUNT);

        ArrayList<Entry> trendLine = new ArrayList<>();
        ArrayList<Entry> longTrendLine = new ArrayList<>();
        ArrayList<Entry> paddingrendLine = new ArrayList<>();
        ArrayList<ICandleDataSet> sets = new ArrayList<>();
        try {
            for (int j = 0; j < hisDatas.size(); j++) {
                ArrayList<CandleEntry> lineCJEntries = new ArrayList<>();
                List<HisData> hdList = DataUtils.calculateHisData(hisDatas.get(j));
                for (int i = 0; i < hdList.size(); i++) {
                    HisData hisData = hdList.get(i);
                    if (j == 0) {
                        paddingrendLine.add(new Entry(i, (float) hisData.getOpen()));
                    }
                    lineCJEntries.add(new CandleEntry(i, (float) hisData.getHigh(), (float) hisData.getLow(), (float) hisData.getOpen(), (float) hisData.getClose(), hisData.width, hisData.color));
                }
                sets.add(setKLine(NORMAL_LINE, lineCJEntries));
//                mData.addAll(hdList);
            }
        } catch (Exception e) {
            Log.e("tag", "trendlinetrendline5=" + e);
        }


        if (model.data != null) {
            if (model.data.trendline != null && model.data.trendline.line_data != null && model.data.trendline.line_data.size() > 0) {
                for (int i = 0; i < model.data.trendline.line_data.size(); i++) {
                    trendLine.add(new Entry(i, (float) model.data.trendline.line_data.get(i).trend_data));
                }
            }
            if (model.data.longtrendline != null && model.data.longtrendline.line_data != null && model.data.longtrendline.line_data.size() > 0) {
                for (int i = 0; i < model.data.longtrendline.line_data.size(); i++) {
                    longTrendLine.add(new Entry(i, (float) model.data.longtrendline.line_data.get(i).trend_data));
                }
            }
        }


        LineData lineData = new LineData(
                setLine(INVISIABLE_LINE, paddingrendLine));

        DrawingData candleData = new DrawingData(sets);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        combinedData.setData(candleData);
        appCombinedChart.setData(combinedData);

        appCombinedChart.setVisibleXRange(MAX_COUNT, MIN_COUNT);
        appCombinedChart.notifyDataSetChanged();
//        mChartPrice.moveViewToX(combinedData.getEntryCount());
        moveToLast(appCombinedChart);

//        initChartMacdData();
//        initChartKdjData();
        appCombinedChart.getXAxis().setAxisMaximum(combinedData.getXMax() + 0.5f);

//        mChartFu.getXAxis().setAxisMaximum(mChartFu.getData().getXMax() + 0.5f);

//        mChartMacd.getXAxis().setAxisMaximum(mChartMacd.getData().getXMax() + 0.5f);
//        mChartKdj.getXAxis().setAxisMaximum(mChartKdj.getData().getXMax() + 0.5f);
        appCombinedChart.resetZoom();
        Log.e("tag", "MAX_COUNT * 1f / INIT_COUNTMAX_COUNT * 1f / INIT_COUNT=" + MAX_COUNT * 1f / (INIT_COUNT * 1f));
        appCombinedChart.zoom(MAX_COUNT * 1f / (INIT_COUNT * 1f), 0, 0, 0);

//        mChartFu.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        mChartMacd.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
//        mChartKdj.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        lineData.removeDataSet(0);

//        setDescription(mChartPrice, String.format(Locale.getDefault(), "MA5:%.2f  MA10:%.2f  MA20:%.2f  MA30:%.2f",
//                hisData.getMa5(), hisData.getMa10(), hisData.getMa20(), hisData.getMa30()));
//        setDescription(mChartMacd, String.format(Locale.getDefault(), "MACD:%.2f  DEA:%.2f  DIF:%.2f",
//                hisData.getMacd(), hisData.getDea(), hisData.getDif()));
//        setDescription(mChartKdj, String.format(Locale.getDefault(), "K:%.2f  D:%.2f  J:%.2f",
//                hisData.getK(), hisData.getD(), hisData.getJ()));
    }


    public void drawFus(FuAllModel futuData, AppCombinedChart appCombinedChart, boolean isMain) {
        appCombinedChart.setVisibility(VISIBLE);
        if (isMain) {
            mChartPrice.setVisibility(GONE);
        } else {
            mChartVolume.setVisibility(GONE);
            mChartMacd.setVisibility(GONE);
        }


        List<List<HisData>> hisDatas = DataUtils.detailFuData(futuData);

//        mData.clear();
//        mData.addAll();

//        ArrayList<CandleEntry> lineCJEntries = new ArrayList<>(INIT_COUNT);


//        ArrayList<Entry> ma5Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma10Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma20Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma30Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> paddingEntries = new ArrayList<>(INIT_COUNT);

        ArrayList<Entry> paddingrendLine = new ArrayList<>();
        ArrayList<ICandleDataSet> sets = new ArrayList<>();
        List<ILineDataSet> lineList = new ArrayList<>();

        try {
            // 所有柱状图
            for (int j = 0; j < hisDatas.size(); j++) {
                ArrayList<CandleEntry> lineCJEntries = new ArrayList<>();
                List<HisData> hdList = DataUtils.calculateHisData(hisDatas.get(j));
                for (int i = 0; i < hdList.size(); i++) {
                    HisData hisData = hdList.get(i);
                    if (j == 0) {
                        paddingrendLine.add(new Entry(i, (float) hisData.getOpen()));
                    }
                    lineCJEntries.add(new CandleEntry(i, (float) hisData.getHigh(), (float) hisData.getLow(), (float) hisData.getOpen(), (float) hisData.getClose(), hisData.width, hisData.color));
                }

                if (j == 0) {
                    lineList.add(setLine(INVISIABLE_LINE, paddingrendLine));
                }
                sets.add(setKLine(NORMAL_LINE, lineCJEntries));
//                mData.addAll(hdList);
            }
            Log.e("tag", "trend2");
        } catch (Exception e) {

        }


        if (isMain && futuData != null && futuData.five_basic != null) {
            // 五日分时图
            int count = 0;
            List<List<HisData>> fiveBaseList = Util.get5Day(futuData.five_basic);
            Log.e("tag", "fiveBaseList=" + fiveBaseList.size());
            for (List<HisData> hisData : fiveBaseList) {
                hisData = DataUtils.calculateHisData(hisData);
                ArrayList<Entry> priceEntries = new ArrayList<>(INIT_COUNT);
                ArrayList<Entry> aveEntries = new ArrayList<>(INIT_COUNT);
//                ArrayList<Entry> paddingEntries = new ArrayList<>(INIT_COUNT);


                for (int i = 0; i < hisData.size(); i++) {
                    HisData t = hisData.get(i);
                    priceEntries.add(new Entry(i + count, (float) t.getClose()));
                    aveEntries.add(new Entry(i + count, (float) t.getAvePrice()));
                }
                count += hisData.size();
//                if (!hisData.isEmpty() && hisData.size() < INIT_COUNT / hisDatas.size()) {
//                    for (int i = hisData.size(); i < INIT_COUNT / hisDatas.size(); i++) {
//                        paddingEntries.add(new Entry(i, (float) hisData.get(hisData.size() - 1).getClose()));
//                    }
//                }

                Log.e("tag", "futuDatafutuData1");
                lineList.add(setLine(NORMAL_LINE_5DAY, priceEntries));
                lineList.add(setLine(AVE_LINE, aveEntries));
            }
        }


        /**
         *  分时图
         * */
        if (futuData != null && futuData.time_basic != null && futuData.time_basic.size() > 0) {

            ArrayList<Entry> priceEntries = new ArrayList<>(INIT_COUNT);
            ArrayList<Entry> aveEntries = new ArrayList<>(INIT_COUNT);
            for (int i = 0; i < mData.size(); i++) {
                priceEntries.add(new Entry(i, (float) futuData.time_basic.get(i).getPrice()));
                aveEntries.add(new Entry(i, (float) futuData.time_basic.get(i).getAverage()));
            }
            lineList.add(setLine(NORMAL_LINE, priceEntries));
            lineList.add(setLine(AVE_LINE, aveEntries));
        }


        if (futuData != null && futuData.line != null && futuData.line.size() > 0) {
            for (int i = 0; i < futuData.line.size(); i++) {
                ArrayList<Entry> longTrendLine = new ArrayList<>();

                for (int j = 0; j < futuData.line.get(i).line_data.size(); j++) {
                    longTrendLine.add(new Entry(j, (float) futuData.line.get(i).line_data.get(j).trend_data));
                }

                lineList.add(initLineAndColor(NORMAL_LINE, longTrendLine, futuData.line.get(i).line_color));
            }

        }


        LineData lineData = new LineData(
                lineList);

        DrawingData candleData = new DrawingData(sets);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        combinedData.setData(candleData);
        appCombinedChart.setData(combinedData);

        appCombinedChart.setVisibleXRange(MAX_COUNT, MIN_COUNT);
        appCombinedChart.notifyDataSetChanged();
//        mChartPrice.moveViewToX(combinedData.getEntryCount());
        moveToLast(appCombinedChart);

//        initChartMacdData();
//        initChartKdjData();
        appCombinedChart.getXAxis().setAxisMaximum(combinedData.getXMax() + 0.5f);

//        mChartFu.getXAxis().setAxisMaximum(mChartFu.getData().getXMax() + 0.5f);

//        mChartMacd.getXAxis().setAxisMaximum(mChartMacd.getData().getXMax() + 0.5f);
//        mChartKdj.getXAxis().setAxisMaximum(mChartKdj.getData().getXMax() + 0.5f);
        appCombinedChart.resetZoom();
        appCombinedChart.zoom(MAX_COUNT * 1f / (INIT_COUNT * 1f), 0, 0, 0);

//        mChartFu.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        mChartMacd.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
//        mChartKdj.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);

//        lineData.removeDataSet(0);

//        setDescription(mChartPrice, String.format(Locale.getDefault(), "MA5:%.2f  MA10:%.2f  MA20:%.2f  MA30:%.2f",
//                hisData.getMa5(), hisData.getMa10(), hisData.getMa20(), hisData.getMa30()));
//        setDescription(mChartMacd, String.format(Locale.getDefault(), "MACD:%.2f  DEA:%.2f  DIF:%.2f",
//                hisData.getMacd(), hisData.getDea(), hisData.getDif()));
//        setDescription(mChartKdj, String.format(Locale.getDefault(), "K:%.2f  D:%.2f  J:%.2f",
//                hisData.getK(), hisData.getD(), hisData.getJ()));
    }


    @android.support.annotation.NonNull
    public CandleDataSet setKLine(int type, ArrayList<CandleEntry> lineEntries) {
        CandleDataSet set = new CandleDataSet(lineEntries, "KLine" + type);
        set.setDrawIcons(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowColor(Color.DKGRAY);
        set.setShadowWidth(0.75f);
        set.setDecreasingColor(mDecreasingColor);
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setShadowColorSameAsCandle(true);
        set.setIncreasingColor(mIncreasingColor);
        set.setIncreasingPaintStyle(Paint.Style.FILL);
        set.setNeutralColor(ContextCompat.getColor(getContext(), R.color.increasing_color));
        set.setDrawValues(true);
        set.setValueTextSize(10);
        set.setHighlightEnabled(true);
        if (type != NORMAL_LINE) {
            set.setVisible(false);
        }
        return set;
    }


    private void initChartMacdData(List<HisData> hisDatas) {

        mData.clear();
        mData.addAll(DataUtils.calculateHisData(hisDatas));

        mChartFu.setVisibility(GONE);
        mChartVolume.setVisibility(GONE);
        mChartMacd.setVisibility(VISIBLE);

        XAxis xAxis = mChartMacd.getXAxis();
        xAxis.setLabelCount(hisDatas.size() + 1, true);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                value += 1; // 这里不设置+1会有bug
                Log.e("tag", "DateUtil===s=" + value);
                if (mData.isEmpty()) {
                    return "";
                }
                if (value < 0) {
                    value = 0;
                }
                if (value < mData.size()) {
                    Log.e("tag", "DateUtils=" + DateUtils.formatDate(mData.get((int) value).getDate(), mDateFormat));
                    Log.e("tag", "DateUtilsDateUtilsDateUtils=" + mData.get((int) value).getDate());
                    return DateUtils.formatDate(mData.get((int) value).getDate(), mDateFormat);
                }
                return "";
            }
        });

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarEntry> paddingEntries = new ArrayList<>();
        ArrayList<Entry> difEntries = new ArrayList<>();
        ArrayList<Entry> deaEntries = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            HisData t = mData.get(i);
            barEntries.add(new BarEntry(i, (float) t.getMacd()));
            difEntries.add(new Entry(i, (float) t.getDif()));
            deaEntries.add(new Entry(i, (float) t.getDea()));
        }
        int maxCount = MAX_COUNT;
        if (!mData.isEmpty() && mData.size() < maxCount) {
            for (int i = mData.size(); i < maxCount; i++) {
                paddingEntries.add(new BarEntry(i, 0));
            }
        }

        BarData barData = new BarData(setBar(barEntries, NORMAL_LINE), setBar(paddingEntries, INVISIABLE_LINE));
        barData.setBarWidth(0.75f);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);
        LineData lineData = new LineData(setLine(DIF, difEntries), setLine(DEA, deaEntries));
        combinedData.setData(lineData);
        mChartMacd.setData(combinedData);

        mChartMacd.setVisibleXRange(MAX_COUNT, MIN_COUNT);

        mChartMacd.notifyDataSetChanged();
//        mChartMacd.moveViewToX(combinedData.getEntryCount());
        moveToLast(mChartMacd);

        mChartMacd.getXAxis().setAxisMaximum(mChartMacd.getData().getXMax() + 0.5f);
        mChartMacd.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
        HisData hisData = getLastData();
        setDescription(mChartMacd, String.format(Locale.getDefault(), "MACD:%.2f  DEA:%.2f  DIF:%.2f",
                hisData.getMacd(), hisData.getDea(), hisData.getDif()));

    }


    public void setCelueListData(List<MyTacticsModel> my_tactics, List<MyTacticsModel> my_tactics_fu) {
        fuMenuView.setTitleText("无策略");
        mainMenuView.setTitleText("无策略");


        if (my_tactics != null) {
            mainCeluePopupWindow.setData(my_tactics);
        }

        if (my_tactics_fu != null) {
            fuCeluePopupWindow.setData(my_tactics_fu);
        }
    }


    private CelLueCallBack celLueCallBack;

    public void setCelLueCallBack(CelLueCallBack celLueCallBack) {
        this.celLueCallBack = celLueCallBack;
    }

}
