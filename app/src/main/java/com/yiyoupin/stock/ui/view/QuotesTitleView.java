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

public class QuotesTitleView extends ConstraintLayout {
    public QuotesTitleView(Context context) {
        this(context, null);
    }

    public QuotesTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuotesTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_quotes_title, this);
    }
}
