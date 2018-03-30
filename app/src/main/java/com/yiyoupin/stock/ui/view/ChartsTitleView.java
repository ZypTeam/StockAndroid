package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsTitleView extends ConstraintLayout {
    public ChartsTitleView(Context context) {
        this(context, null);
    }

    public ChartsTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartsTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_charts_title, this);
    }
}
