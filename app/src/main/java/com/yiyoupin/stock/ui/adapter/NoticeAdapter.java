package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NoticeModel;
import com.jusfoun.baselibrary.base.BaseAdapter;
import com.jusfoun.baselibrary.base.BaseViewHolder;
import com.yiyoupin.stock.ui.util.UiUtils;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class NoticeAdapter extends BaseAdapter<NoticeModel> {

    public NoticeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_notic_list;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ViewHolder(view, context);
    }

    class ViewHolder extends BaseViewHolder<NoticeModel> {
        protected TextView title;
        protected TextView content;
        protected TextView time;
        protected TextView count;
        public ViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
            count = (TextView) itemView.findViewById(R.id.count);

        }

        @Override
        public void update(NoticeModel model) {

            itemView.setOnClickListener(v -> {
                UiUtils.goWebActivity(context, "公告详情", model.getUrl());
            });

            title.setText(model.getStock_name());
            count.setText(String.valueOf(model.getView_count()));
            content.setText(model.getTitle());
            time.setText(model.getNews_time());
        }
    }
}
