package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.ReplayModel;
import com.jusfoun.baselibrary.base.BaseAdapter;
import com.jusfoun.baselibrary.base.BaseViewHolder;
import com.yiyoupin.stock.ui.util.UiUtils;

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

        protected TextView title;
        protected TextView content;
        protected TextView time;
        protected TextView count;
        public ReplayViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
            count = (TextView) itemView.findViewById(R.id.count);
        }

        @Override
        public void update(ReplayModel model) {
            itemView.setOnClickListener(v -> {
                UiUtils.goWebActivity(context, "公告详情", model.getUrl());
            });
            title.setText(model.getTitle());
            count.setText(String.valueOf(model.getView_count()));
            content.setText(model.getTitle());
            time.setText(model.getChecking_date());
        }
    }
}
