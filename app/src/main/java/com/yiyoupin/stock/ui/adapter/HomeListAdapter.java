package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.activity.FromListActivity;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2215:11
 * @Email zyp@jusfoun.com
 * @Description ${首页 选股策略 买点精选 技术形态 adapter}
 */
public class HomeListAdapter extends BaseAdapter<BaseModel> {

    public static int TYPE_STRATEGIES = 1;// 选个策略
    public static int TYPE_FEATURED = 2;// 买点精选
    public static int TYPE_FORM = 3;// 技术形态
    public static int TYPE_FORM_LIST = 4;// 技术形态列表

    private int type = 1;

    public HomeListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        if (viewType == 1) {
            return R.layout.item_strategies;
        } else if (viewType == 2) {
            return R.layout.item_featured;
        } else if (viewType == 3) {
            return R.layout.item_from;
        } else if (viewType == 4) {
            return R.layout.item_from_list;
        }
        return 0;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        if (viewType == TYPE_STRATEGIES) {
            return new StrategiesViewHolder(view, context);
        } else if (viewType == TYPE_FEATURED) {
            return new FeaturedViewHolder(view, context);
        } else if (viewType == TYPE_FORM) {
            return new FromViewHolder(view, context);
        } else if (viewType == TYPE_FORM_LIST) {
            return new FromListViewHolder(view, context);
        }
        return new StrategiesViewHolder(view, context);
    }


    @Override
    public int getItemViewType(int position) {

        return type;
    }

    /**
     * 选股策略
     */
    public class StrategiesViewHolder extends BaseViewHolder {

        protected TextView textTitle;
        protected TextView textCount;

        public StrategiesViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            textTitle.setText("战神归来");
            textCount.setText("+3.93%");
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textCount = (TextView) rootView.findViewById(R.id.text_count);
        }
    }


    /**
     * 买点精选
     */
    public class FeaturedViewHolder extends BaseViewHolder {

        protected TextView textTitle;
        protected TextView textId;
        protected TextView textCount1;
        protected TextView textCount2;

        public FeaturedViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            textTitle.setText("智慧");
            textCount1.setText("+3.93%");
            textCount2.setText("+3.93%");
            textId.setText("0816");
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textId = (TextView) rootView.findViewById(R.id.text_id);
            textCount1 = (TextView) rootView.findViewById(R.id.text_count1);
            textCount2 = (TextView) rootView.findViewById(R.id.text_count2);
        }
    }


    /**
     * 技术形态
     */
    public class FromViewHolder extends BaseViewHolder {

        protected TextView textTitle;

        public FromViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            textTitle.setText("战神归来");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(mContext, FromListActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);

        }
    }

    /**
     * 技术形态
     */
    public class FromListViewHolder extends BaseViewHolder {

        protected TextView textTitle;
        protected TextView textId;
        protected TextView newText,gainsText,fallText;


        public FromListViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            textTitle.setText("只会农业");
            textId.setText("0816");
            newText.setText("3.9");
            gainsText.setText("+3.93%");
            fallText.setText("2.01");
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textId = (TextView) rootView.findViewById(R.id.text_id);
            newText = (TextView) rootView.findViewById(R.id.text_new);
            gainsText = (TextView) rootView.findViewById(R.id.text_gains);
            fallText = (TextView) rootView.findViewById(R.id.text_fall);


        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
