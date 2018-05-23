package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MsgModel;
import com.jusfoun.baselibrary.base.BaseAdapter;
import com.jusfoun.baselibrary.base.BaseViewHolder;

/**
 * @author wangcc
 * @date 2018/3/31
 * @describe
 */

public class MsgAdapter extends BaseAdapter<MsgModel> {
    public MsgAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_msg;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ViewHolder(view,context);
    }

    class ViewHolder extends BaseViewHolder<MsgModel> {
        private TextView time;
        private TextView content;
        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
        }

        @Override
        public void update(MsgModel model) {

            time.setText(model.getMessage_time());
            content.setText(model.getMessage_content());
        }
    }
}
