package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class FormView extends ConstraintLayout {
    protected View bottomLine;

    public FormView(Context context) {
        this(context, null);
    }

    public FormView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_from_view, this);
        bottomLine = findViewById(R.id.bottom_line);
    }

    public void setBottomLine(boolean show){
        if (show){
            bottomLine.setVisibility(VISIBLE);
        }else {
            bottomLine.setVisibility(GONE);
        }
    }
}
