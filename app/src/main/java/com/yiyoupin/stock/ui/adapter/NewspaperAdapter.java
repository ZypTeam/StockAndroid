package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NewspaperModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class NewspaperAdapter extends BaseAdapter<NewspaperModel> {
    public NewspaperAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_newspaper_list;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ViewHolder(view,context);
    }

    class ViewHolder extends BaseViewHolder<NewspaperModel>{

        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
        }

        @Override
        public void update(NewspaperModel model) {

        }
    }
}
