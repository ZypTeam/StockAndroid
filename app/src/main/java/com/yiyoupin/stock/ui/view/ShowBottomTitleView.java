package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseView;

/**
 * @author zhaoyapeng
 * @version create time:18/4/2313:51
 * @Email zyp@jusfoun.com
 * @Description ${股票底部 view title}
 */
public class ShowBottomTitleView extends BaseView {
    protected View rootView;
    protected TextView textTitle;
    protected TextView textPrice;
    protected TextView textDiefu;
    protected View viewLine;

    public ShowBottomTitleView(Context context) {
        super(context);
    }

    public ShowBottomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowBottomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.view_show_bottom_title, this, true);
        initView(this);
    }

    @Override
    protected void initActions() {

    }

    private void initView(View rootView) {
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textPrice = (TextView) rootView.findViewById(R.id.text_price);
        textDiefu = (TextView) rootView.findViewById(R.id.text_diefu);
        viewLine = (View) rootView.findViewById(R.id.view_line);
    }

    public void setLineState(int state){
        viewLine.setVisibility(state);
    }

    public void setData(){
        textTitle.setText("沪");
        textPrice.setText("3122.29");
        textDiefu.setText("-1.4%");
    }


}
