package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.ReplayModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class ReplayAdapter extends BaseAdapter<ReplayModel> {
    public ReplayAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_replay_list;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ReplayViewHolder(view,context);
    }

    class ReplayViewHolder extends BaseViewHolder<ReplayModel>{

        private TextView name;
        public ReplayViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            name=itemView.findViewById(R.id.name);
        }

        @Override
        public void update(ReplayModel model) {
            name.setText(model.getTitle());
        }
    }
}
