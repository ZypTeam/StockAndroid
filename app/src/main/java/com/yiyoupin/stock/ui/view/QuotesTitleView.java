package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class QuotesTitleView extends ConstraintLayout {
    private TextView more;
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
        more=findViewById(R.id.more);
    }

    public void setMoreIcon(Drawable drawable){

    }
}
