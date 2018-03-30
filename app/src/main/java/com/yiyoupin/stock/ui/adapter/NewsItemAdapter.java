package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author zhaoyapeng
 * @version create time:17/12/2115:44
 * @Email zyp@jusfoun.com
 * @Description ${新闻}
 */

public class NewsItemAdapter extends BaseAdapter<SearchModel> {
    public NewsItemAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_news;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new NewsViewHolder(view, context);
    }

    class NewsViewHolder extends BaseViewHolder<SearchModel> {

        protected View rootView;
        protected TextView textTitle;
        protected TextView textTime;

        public NewsViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(SearchModel model) {
            textTitle.setText("整卷变动整卷变动整卷变动整卷变动整卷变动整卷变动整卷变动");
            textTime.setText("01-22 07:16");
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textTime = (TextView) rootView.findViewById(R.id.text_time);
        }
    }
}
