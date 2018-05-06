package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class CustomQuotesTitleView extends ConstraintLayout {
    protected View rootView;
    protected TextView textTitle1;
    protected TextView textTitle2;
    protected TextView textTitle3;

    public CustomQuotesTitleView(Context context) {
        this(context, null);
    }

    public CustomQuotesTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomQuotesTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_quotes_title, this);
        textTitle1 = (TextView) findViewById(R.id.text_title_1);
        textTitle2 = (TextView) findViewById(R.id.text_title_2);
        textTitle3 = (TextView) findViewById(R.id.text_title_3);
    }

    public void setData(String title) {
        if("涨幅榜".equals(title) ){
        }
    }
}
