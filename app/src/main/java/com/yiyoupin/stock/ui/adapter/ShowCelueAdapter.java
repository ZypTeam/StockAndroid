package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MyTacticsModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/4/2810:46
 * @Email zyp@jusfoun.com
 * @Description ${股票详情页面 策略列表 adapter}
 */
public class ShowCelueAdapter extends BaseAdapter<MyTacticsModel> {
    private Context mContext;
    public ShowCelueAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_stock_celue;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new ShowCelueViewHolder(view,mContext);
    }

    private class  ShowCelueViewHolder extends BaseViewHolder<MyTacticsModel>{

        private TextView nameText;
        public ShowCelueViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            nameText= itemView.findViewById(R.id.text_name);
        }

        @Override
        public void update(MyTacticsModel model) {
            nameText.setText(model.tactics_name);
        }
    }

}
