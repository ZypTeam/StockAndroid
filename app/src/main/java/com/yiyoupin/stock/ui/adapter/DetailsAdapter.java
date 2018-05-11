package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MIngXIDataModel;
import com.yiyoupin.stock.model.MingXiModel;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3017:54
 * @Email zyp@jusfoun.com
 * @Description ${明细adapter}
 */
public class DetailsAdapter<T> extends BaseAdapter<BaseModel> {
    public static int TYPE_FIVE = 0; // 五档
    public static int TYPE_DETAILS = 1;//明细
    private int type;

    public DetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {

        if (viewType == TYPE_FIVE) {
            return R.layout.item_details_five;
        } else if (viewType == TYPE_DETAILS) {
            return R.layout.item_details_five;
        }

        return R.layout.item_details_five;

    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        if (viewType == TYPE_FIVE) {
            return new FiveViewHolder(view, context);
        } else if (viewType == TYPE_DETAILS) {
            return new DetailsViewHolder(view, context);
        }
        return new FiveViewHolder(view, context);
    }


    @Override
    public int getItemViewType(int position) {
        return type;
    }

    /**
     * 五档
     */
    public class FiveViewHolder extends BaseViewHolder {

        protected TextView textTitle;
        protected View rootView;
        protected TextView textNum1;
        protected TextView textNum2;

        public FiveViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {
            textTitle.setText(((MingXiModel.MingXiItemModel)model).name);
            textNum1.setText(((MingXiModel.MingXiItemModel)model).price);
            textNum2.setText(((MingXiModel.MingXiItemModel)model).count);

            if(TextUtils.isEmpty(((MingXiModel.MingXiItemModel) model).name)){
                textTitle.setText("--");
            }

            if(TextUtils.isEmpty(((MingXiModel.MingXiItemModel) model).price)){
                textNum1.setText("--");
            }

            if(TextUtils.isEmpty(((MingXiModel.MingXiItemModel) model).count)){
                textNum2.setText("--");
            }
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textNum1 = (TextView) rootView.findViewById(R.id.text_num1);
            textNum2 = (TextView) rootView.findViewById(R.id.text_num2);

        }
    }

    /**
     * 明细
     */
    public class DetailsViewHolder extends BaseViewHolder {


        protected TextView textTitle;
        protected View rootView;
        protected TextView textNum1;
        protected TextView textNum2;


        public DetailsViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            initView(itemView);
        }

        @Override
        public void update(Serializable model) {

            textTitle.setText(((StockDetailModel.TradeDetailModel)model).trade_time);
            textNum1.setText(((StockDetailModel.TradeDetailModel)model).stock_price);
            textNum2.setText(((StockDetailModel.TradeDetailModel)model).trade_volumn);
        }

        private void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textNum1 = (TextView) rootView.findViewById(R.id.text_num1);
            textNum2 = (TextView) rootView.findViewById(R.id.text_num2);

        }
    }

    public void setType(int type) {
        this.type = type;
    }

}
