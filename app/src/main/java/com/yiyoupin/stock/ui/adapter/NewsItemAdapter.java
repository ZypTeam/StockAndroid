package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NewModel;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author zhaoyapeng
 * @version create time:17/12/2115:44
 * @Email zyp@jusfoun.com
 * @Description ${新闻}
 */

public class NewsItemAdapter<T> extends BaseAdapter<BaseModel> {
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

    class NewsViewHolder extends BaseViewHolder<BaseModel> {

        protected View rootView;
        protected TextView textTitle;
        protected TextView textTime;

        public NewsViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(BaseModel model) {
            if(model instanceof NewModel.NewsItemModel) {
                textTitle.setText(((NewModel.NewsItemModel) model).title);
                textTime.setText(((NewModel.NewsItemModel) model).daily_date);
            }else if(model instanceof NewModel.NewsItemStopModel){
                textTitle.setText(((NewModel.NewsItemStopModel) model).stock_name);
                textTime.setText(((NewModel.NewsItemStopModel) model).stop_time);
            }else if(model instanceof NewModel.NewsItemNoticeModel){
                textTitle.setText(((NewModel.NewsItemNoticeModel) model).title);
                textTime.setText(((NewModel.NewsItemNoticeModel) model).notice_time);
            }
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textTime = (TextView) rootView.findViewById(R.id.text_time);
        }
    }
}
