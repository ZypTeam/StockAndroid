package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.ui.base.BaseView;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2916:36
 * @Email zyp@jusfoun.com
 * @Description ${个股top view}
 */
public class StocksTopView extends BaseView {
    protected View rootView;
    protected TextView textGao;
    protected TextView textDi;
    protected TextView textKai;
    protected TextView textHuan;
    protected TextView textLiang;
    protected TextView textE;
    protected TextView textPrice;
    protected TextView textCount1;
    protected TextView textCount2;

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
        initView(this);
    }

    @Override
    protected void initActions() {

    }

    private void initView(View rootView) {
        textGao = (TextView) rootView.findViewById(R.id.text_gao);
        textDi = (TextView) rootView.findViewById(R.id.text_di);
        textKai = (TextView) rootView.findViewById(R.id.text_kai);
        textHuan = (TextView) rootView.findViewById(R.id.text_huan);
        textLiang = (TextView) rootView.findViewById(R.id.text_liang);
        textE = (TextView) rootView.findViewById(R.id.text_e);
        textPrice = (TextView) rootView.findViewById(R.id.text_price);
        textCount1 = (TextView) rootView.findViewById(R.id.text_count1);
        textCount2 = (TextView) rootView.findViewById(R.id.text_count2);
    }

    public void setData(StockDetailModel.StockDetailDataModel model) {
        if(model.stock_detail!=null) {
            textGao.setText(model.stock_detail.todayMax);
            textDi.setText(model.stock_detail.todayMin);
            textKai.setText(model.stock_detail.todayStartPri);
            textHuan.setText(model.stock_detail.change_rate+"%");
            textLiang.setText(model.stock_detail.quantity_ratio);
            textE.setText(model.stock_detail.traAmount);

            textPrice.setText(model.stock_detail.stock_price);
            textCount1.setText(model.stock_detail.offset_size);
            textCount2.setText(model.stock_detail.increase);
        }
//        if(model.trade_detail!=null) {
//
//        }
    }
}
