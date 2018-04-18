package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class ChartsDetailHeaderView extends CoordinatorLayout {
    protected TextView time;
    protected TextView zhangfu;
    protected TextView price;
    protected TextView liyou;

    public ChartsDetailHeaderView(Context context) {
        this(context, null);
    }

    public ChartsDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartsDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_chart_header, this);
        time = (TextView) findViewById(R.id.time);
        zhangfu = (TextView) findViewById(R.id.zhangfu);
        price = (TextView) findViewById(R.id.price);
        liyou = (TextView) findViewById(R.id.liyou);

        zhangfu.setText(getText("涨幅:", "9.98%", "#ffffff", "#fe492f"));
        price.setText(getText("股价:", "9.98元", "#ffffff", "#fe492f"));
        liyou.setText(getText("上榜理由:", "理由理由理由理由", "#919191", "#ffffff"));
    }

    public SpannableStringBuilder getText(String txt1, String txt2, String color1, String color2) {
        SpannableStringBuilder builder = new SpannableStringBuilder(txt1 + txt2);

        builder.setSpan(new ForegroundColorSpan(Color.parseColor(color1)), 0, txt1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor(color2)), txt1.length(), txt1.length() + txt2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}




