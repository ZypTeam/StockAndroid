package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.base.BaseView;
import com.yiyoupin.stock.ui.util.UiUtils;

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
    private HomeModel.PlateindexItemModel model;

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

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(StockShowActivity.ID,model.plate_id+"");
                bundle.putString(StockShowActivity.CODE, model.plate_name);
                bundle.putString(StockShowActivity.CHOICENESS_ID, "");
                UiUtils.goStockShowActivity(mContext, bundle);
            }
        });
    }

    private void initView(View rootView) {
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textCount = (TextView) rootView.findViewById(R.id.text_count);
        textAdd = (TextView) rootView.findViewById(R.id.text_add);
        textBaifenbi = (TextView) rootView.findViewById(R.id.text_baifenbi);
    }

    public void setData(HomeModel.PlateindexItemModel model){
        this.model = model;
        textTitle.setText(model.plate_name);
        textCount.setText(model.plate_index);
        textAdd.setText(model.plate_growth);
        textBaifenbi.setText(model.plate_growth_rate+"%");
    }
}
