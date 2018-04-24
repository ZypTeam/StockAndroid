package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.guoziwei.klinelib.chart.TimeLineView;
import com.guoziwei.klinelib.model.HisData;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseView;
import com.yiyoupin.stock.ui.view.kline.Util;

import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/2310:21
 * @Email zyp@jusfoun.com
 * @Description ${个股详情底部}
 */
public class ShowBottomView extends BaseView {
    protected View rootView;
    protected TimeLineView timeLineView;
    protected ShowBottomTitleView viewBottomTitleHu;
    protected ShowBottomTitleView viewBottomTitleShen;
    protected ShowBottomTitleView viewBottomTitleChuang;
    protected TextView textCount;
    protected TextView textHeight;
    protected TextView textLow;
    protected TextView textTody;
    protected TextView textYes;

    public ShowBottomView(Context context) {
        super(context);
    }

    public ShowBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

        LayoutInflater.from(mContext).inflate(R.layout.view_show_bottom, this, true);
        initView(this);
    }

    @Override
    protected void initActions() {

        viewBottomTitleHu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBottomTitleHu.setLineState(View.VISIBLE);
                viewBottomTitleShen.setLineState(View.INVISIBLE);
                viewBottomTitleChuang.setLineState(View.INVISIBLE);
            }
        });

        viewBottomTitleShen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBottomTitleHu.setLineState(View.INVISIBLE);
                viewBottomTitleShen.setLineState(View.VISIBLE);
                viewBottomTitleChuang.setLineState(View.INVISIBLE);
            }
        });

        viewBottomTitleChuang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBottomTitleHu.setLineState(View.INVISIBLE);
                viewBottomTitleShen.setLineState(View.INVISIBLE);
                viewBottomTitleChuang.setLineState(View.VISIBLE);
            }
        });
    }

    private void initView(View rootView) {
        timeLineView = (TimeLineView) rootView.findViewById(R.id.time_line_view);
        viewBottomTitleHu = (ShowBottomTitleView) rootView.findViewById(R.id.view_bottom_title_hu);
        viewBottomTitleShen = (ShowBottomTitleView) rootView.findViewById(R.id.view_bottom_title_shen);
        viewBottomTitleChuang = (ShowBottomTitleView) rootView.findViewById(R.id.view_bottom_title_chuang);
        textCount = (TextView) rootView.findViewById(R.id.text_count);
        textHeight = (TextView) rootView.findViewById(R.id.text_height);
        textLow = (TextView) rootView.findViewById(R.id.text_low);
        textTody = (TextView) rootView.findViewById(R.id.text_tody);
        textYes = (TextView) rootView.findViewById(R.id.text_yes);
    }

    public void setData(){
        viewBottomTitleHu.setData();
        viewBottomTitleShen.setData();
        viewBottomTitleChuang.setData();

//        final List<HisData> hisData = Util.get1Day();
//        timeLineView.setLastClose(hisData.get(0).getClose());
//        timeLineView.initData(hisData);
    }
}
