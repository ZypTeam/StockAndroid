package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.ChartItemModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;
import com.yiyoupin.stock.ui.view.FormView;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsDetailAdapter extends BaseAdapter<ChartItemModel> {
    public ChartsDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return getView(viewType);
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return viewHolder(viewType,view);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    private int getView(int viewType){
        int resId=0;
        switch (viewType) {
            case 0:
                resId=R.layout.item_charts_detail;
                break;
            case 1:
                resId=R.layout.item_charts_detail_1;
                break;
            case 2:
                resId=R.layout.item_charts_detail_2;
                break;
            default:
                break;
        }
        return resId;
    }

    private BaseViewHolder viewHolder(int viewType, View view) {
        BaseViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder=new ViewHolder(view,context);
                break;
            case 1:
                holder=new ViewHolder1(view,context);
                break;
            case 2:
                holder=new ViewHolder2(view,context);
                break;
            default:
                break;
        }
        return holder;
    }

    ;

    class ViewHolder extends BaseViewHolder<ChartItemModel> {
        private TextView title,content;

        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }

        @Override
        public void update(ChartItemModel model) {
            title.setText(model.getStock_name());
            content.setText(model.getStock_code());
        }
    }

    class ViewHolder1 extends BaseViewHolder<ChartItemModel> {
        private TextView title,content;

        public ViewHolder1(View itemView, Context mContext) {
            super(itemView, mContext);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }

        @Override
        public void update(ChartItemModel model) {

            title.setText(model.getBuy_name());
            content.setText(model.getBuy_amount());
        }
    }

    class ViewHolder2 extends BaseViewHolder<ChartItemModel> {
        private TextView title,content;

        public ViewHolder2(View itemView, Context mContext) {
            super(itemView, mContext);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }

        @Override
        public void update(ChartItemModel model) {

            title.setText(model.getSale_name());
            content.setText(model.getSale_amount());
        }
    }
}
