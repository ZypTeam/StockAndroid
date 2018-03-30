package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;
import com.yiyoupin.stock.ui.view.FormView;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsDetailAdapter extends BaseAdapter<ChartsModel> {
    public ChartsDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_charts_detail;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ViewHolder(view,context);
    }

    class ViewHolder extends BaseViewHolder<ChartsModel> {
        private FormView formView;
        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            formView=itemView.findViewById(R.id.form_view);
        }

        @Override
        public void update(ChartsModel model) {
            if (getAdapterPosition()==getItemCount()-1){
                formView.setBottomLine(true);
            }else {
                formView.setBottomLine(false);
            }
        }
    }
}
