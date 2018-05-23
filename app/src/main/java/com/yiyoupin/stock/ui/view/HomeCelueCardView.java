package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.ui.activity.StrategiesDetailActivity;
import com.yiyoupin.stock.ui.base.BaseView;
import com.yiyoupin.stock.ui.util.UiUtils;

/**
 * @author zhaoyapeng
 * @version create time:18/5/2310:26
 * @Email zyp@jusfoun.com
 * @Description ${首页策略 卡片}
 */
public class HomeCelueCardView extends BaseView {
    protected TextView textLv;
    protected ImageView imgTop;
    protected TextView textName;
    private HomeModel.StocktacticsItemModel model;

    public HomeCelueCardView(Context context) {
        super(context);
    }

    public HomeCelueCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeCelueCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.view_home_celue_card, this, true);
        initView(this);
    }

    @Override
    protected void initActions() {

    }

    private void initView(View rootView) {
        textLv = (TextView) rootView.findViewById(R.id.text_lv);
        imgTop = (ImageView) rootView.findViewById(R.id.img_top);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(StrategiesDetailActivity.CHOICENESS_ID, model.tactics_id);
                    UiUtils.goStrategiesDetailActivity(bundle, mContext);
                }
            }
        });
        textName = (TextView) rootView.findViewById(R.id.text_name);
    }


    public void setData(HomeModel.StocktacticsItemModel model, int position) {
        this.model = model;
        textLv.setText(model.yield_rate);
        textName.setText(model.tactics_name);
        if (position == 0) {
            imgTop.setImageResource(R.drawable.img_top_crad_1);
        } else if (position == 1) {
            imgTop.setImageResource(R.drawable.img_top_crad_2);
        } else if (position == 2) {
            imgTop.setImageResource(R.drawable.img_top_crad_3);
        }
    }
}
