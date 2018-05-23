package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.NewModel;
import com.jusfoun.baselibrary.base.BaseAdapter;
import com.jusfoun.baselibrary.base.BaseViewHolder;
import com.yiyoupin.stock.ui.util.TimeUtil;

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

                if(!TextUtils.isEmpty(((NewModel.NewsItemModel) model).daily_date)){
                    textTime.setText(TimeUtil.getDateToString(((NewModel.NewsItemModel) model).daily_date,"yyyy-MM-dd"));
                }
            }else if(model instanceof NewModel.NewsItemStopModel){
                textTitle.setText(((NewModel.NewsItemStopModel) model).stock_name);
                textTime.setText(((NewModel.NewsItemStopModel) model).stop_time);


                if(!TextUtils.isEmpty(((NewModel.NewsItemStopModel) model).stop_time)){
                    textTime.setText(TimeUtil.getDateToString(((NewModel.NewsItemStopModel) model).stop_time,"yyyy-MM-dd"));
                }
            }else if(model instanceof NewModel.NewsItemNoticeModel){
                textTitle.setText(((NewModel.NewsItemNoticeModel) model).title);
                if(!TextUtils.isEmpty(((NewModel.NewsItemNoticeModel) model).notice_time)){
                    textTime.setText(TimeUtil.getDateToString(((NewModel.NewsItemNoticeModel) model).notice_time,"yyyy-MM-dd"));
                }

            }
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textTime = (TextView) rootView.findViewById(R.id.text_time);
        }
    }
}
