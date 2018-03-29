package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseView;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2916:36
 * @Email zyp@jusfoun.com
 * @Description ${个股top view}
 */
public class StocksTopView extends BaseView {
    public StocksTopView(Context context) {
        super(context);
    }

    public StocksTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StocksTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.view_stocks_top, this, true);
    }

    @Override
    protected void initActions() {

    }
}
