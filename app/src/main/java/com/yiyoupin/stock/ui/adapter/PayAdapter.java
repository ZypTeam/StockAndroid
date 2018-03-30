package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.PayModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class PayAdapter extends BaseAdapter<PayModel> {
    public PayAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_pay;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ViewHolder(view,context);
    }

    class ViewHolder extends BaseViewHolder<PayModel> {
        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
        }

        @Override
        public void update(PayModel model) {

        }
    }
}
