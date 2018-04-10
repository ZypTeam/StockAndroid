package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.model.StrategiesMoreModel;
import com.yiyoupin.stock.ui.activity.FromListActivity;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;
import com.yiyoupin.stock.ui.util.UiUtils;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2215:11
 * @Email zyp@jusfoun.com
 * @Description ${首页 选股策略 买点精选 技术形态 adapter}
 */
public class HomeListAdapter<T> extends BaseAdapter<BaseModel> {

    public static int TYPE_STRATEGIES = 1;// 选个策略
    public static int TYPE_FEATURED = 2;// 买点精选
    public static int TYPE_FORM = 3;// 技术形态
    public static int TYPE_FORM_LIST = 4;// 技术形态列表

    public static int TYPE_STRATEGIES_MORE = 5;// 选个策略更多

    public static int TYPE_FEATURED_MORE = 6;// 买点精选

    public static int TYPE_STRATEGIES_DETAIL = 7;// 策略详情 列表


    private int type = 1;

    public HomeListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {

        if (viewType == TYPE_STRATEGIES) {
            return R.layout.item_strategies;
        } else if (viewType == TYPE_FEATURED) {
            return R.layout.item_featured;
        } else if (viewType == TYPE_FORM) {
            return R.layout.item_from;
        } else if (viewType == TYPE_FORM_LIST) {
            return R.layout.item_from_list;
        } else if (viewType == TYPE_STRATEGIES_MORE) {
            return R.layout.item_featured_more;
        } else if (viewType == TYPE_FEATURED_MORE) {
            return R.layout.item_featured_more;
        } else if (viewType == TYPE_STRATEGIES_DETAIL) {
            return R.layout.item_strategies_detail;
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
        } else if (viewType == TYPE_STRATEGIES_MORE) {
            return new FeaturedMoreViewHolder(view, context);
        } else if (viewType == TYPE_FEATURED_MORE) {
            return new FeaturedMoreViewHolder(view, context);
        } else if (viewType == TYPE_STRATEGIES_DETAIL) {
            return new FeaturedViewHolder(view, context);
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
            if (model instanceof HomeModel.StocktacticsItemModel) {
                textTitle.setText(((HomeModel.StocktacticsItemModel) model).tactics_name);
                textCount.setText(((HomeModel.StocktacticsItemModel) model).yield_rate);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UiUtils.goStrategiesDetailActivity(mContext);
                    }
                });
            }
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
            if (model instanceof HomeModel.BuyselectionItemModel) {
                textTitle.setText(((HomeModel.BuyselectionItemModel) model).choiceness_name);
                textCount1.setText(((HomeModel.BuyselectionItemModel) model).yield_rate);
                textCount2.setText(((HomeModel.BuyselectionItemModel) model).yield_rate);
                textId.setText(((HomeModel.BuyselectionItemModel) model).choiceness_id);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (type == TYPE_STRATEGIES_DETAIL) {
                            UiUtils.goStockShowActivity(mContext);
                        } else {
                            UiUtils.goStrategiesDetailActivity(mContext);
                        }
                    }
                });
            }
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
            if (model instanceof HomeModel.TechnologyItemModel)
                textTitle.setText(((HomeModel.TechnologyItemModel) model).technology_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, FromListActivity.class);
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
        protected TextView newText, gainsText, fallText;


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, StockShowActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textId = (TextView) rootView.findViewById(R.id.text_id);
            newText = (TextView) rootView.findViewById(R.id.text_new);
            gainsText = (TextView) rootView.findViewById(R.id.text_gains);
            fallText = (TextView) rootView.findViewById(R.id.text_fall);


        }
    }


    /**
     * 买点精选 更多
     */
    public class FeaturedMoreViewHolder extends BaseViewHolder {


        protected TextView textCount;
        protected TextView textId;
        protected TextView textName;
        protected TextView textFrom;
        protected TextView textDes;
        protected TextView textType;

        public FeaturedMoreViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            if(model instanceof StrategiesMoreModel.StrategiesItemModel) {
                textCount.setText("+"+((StrategiesMoreModel.StrategiesItemModel) model).yield_rate+"%");
                textName.setText(((StrategiesMoreModel.StrategiesItemModel) model).tactics_name);
                textFrom.setText("VIP 服务器推送");
                textDes.setText(((StrategiesMoreModel.StrategiesItemModel) model).description);
                textType.setText("买入");
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UiUtils.goStrategiesDetailActivity(mContext);
                    }
                });
            }
        }

        private void initView(View rootView) {
            textCount = (TextView) rootView.findViewById(R.id.text_count);
            textId = (TextView) rootView.findViewById(R.id.text_id);
            textName = (TextView) rootView.findViewById(R.id.text_name);
            textFrom = (TextView) rootView.findViewById(R.id.text_from);
            textDes = (TextView) rootView.findViewById(R.id.text_des);
            textType = (TextView) rootView.findViewById(R.id.text_type);
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
