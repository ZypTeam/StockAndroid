package com.guoziwei.klinelib.chart.my;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoziwei.klinelib.R;

/**
 * @author zhaoyapeng
 * @version create time:18/5/2118:33
 * @Email zyp@jusfoun.com
 * @Description ${策略菜单 view}
 */
public class CelueMenuView extends BaseView {
    protected TextView textCelueNameMain;
    protected RelativeLayout layoutCelueMain;

    public CelueMenuView(Context context) {
        super(context);
    }

    public CelueMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CelueMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

        LayoutInflater.from(mContext).inflate(R.layout.view_celue_menu, this, true);
        initView(this);
    }

    @Override
    protected void initActions() {
        layoutCelueMain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textCelueNameMain.setText("无策略");
    }

    private void initView(View rootView) {
        textCelueNameMain = (TextView) rootView.findViewById(R.id.text_celue_name_main);
        layoutCelueMain = (RelativeLayout) rootView.findViewById(R.id.layout_celue_main);
    }

    public void setOnClick(OnClickListener onClick){
        layoutCelueMain.setOnClickListener(onClick);
    }

    public void setTitleText(String name){
        textCelueNameMain.setText(name);
    }


}
