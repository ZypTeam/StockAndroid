package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.StringUtil;
import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class CustomQuotesTitleView extends ConstraintLayout {
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
            textTitle2.setText("成交量（万股）");
        }else if (StringUtil.equals("跌幅榜",title)){
            textTitle2.setText("成交量（万股）");
        }else if (StringUtil.equals("成交额榜",title)){
            textTitle2.setText("成交金额（万股）");
        }else if (StringUtil.equals("换手率榜",title)){
            textTitle2.setText("换手率");
        }else if (StringUtil.equals("量比榜",title)){
            textTitle2.setText("成交量（万股）");
        }else if (StringUtil.equals("新股行情",title)){
            textTitle2.setText("成交额(万元)");
        }
    }
}
