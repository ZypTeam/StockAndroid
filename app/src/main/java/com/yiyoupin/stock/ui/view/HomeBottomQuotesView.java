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
 * @version create time:18/3/2210:42
 * @Email zyp@jusfoun.com
 * @Description ${首页底部 上证指数}
 */
public class HomeBottomQuotesView extends BaseView {
    protected View rootView;
    protected TextView textTitle;
    protected TextView textCount;
    protected TextView textAdd;
    protected TextView textBaifenbi;

    public HomeBottomQuotesView(Context context) {
        super(context);
    }

    public HomeBottomQuotesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeBottomQuotesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

        LayoutInflater.from(mContext).inflate(R.layout.view_home_bottom_quotes, this);
        initView(this);
    }

    @Override
    protected void initActions() {

    }

    private void initView(View rootView) {
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textCount = (TextView) rootView.findViewById(R.id.text_count);
        textAdd = (TextView) rootView.findViewById(R.id.text_add);
        textBaifenbi = (TextView) rootView.findViewById(R.id.text_baifenbi);
    }

    public void setData(){
        textTitle.setText("上证指数");
        textCount.setText("3422.18");
        textAdd.setText("+51.53");
        textBaifenbi.setText("1.53%");
    }
}
