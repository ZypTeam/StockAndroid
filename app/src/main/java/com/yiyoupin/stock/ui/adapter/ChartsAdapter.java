package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.expandablerecycleradapter.adapter.BaseExpandableAdapter;
import com.yiyoupin.stock.expandablerecycleradapter.viewholder.AbstractAdapterItem;
import com.yiyoupin.stock.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;
import com.yiyoupin.stock.model.ChartItemModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.CustomQuotesView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsAdapter extends BaseExpandableAdapter {
    private Context context;

    public ChartsAdapter(Context ctx) {
        super(new ArrayList());
        context=ctx;
    }


    @NonNull
    @Override
    public AbstractAdapterItem<Object> getItemView(Object type) {
        int itemType = (int) type;
        if (itemType == 0) {
            return new GroupViewHolder();
        } else {
            return new ChildViewHolder();
        }
    }

    @Override
    public Object getItemViewType(Object t) {
        if (t instanceof ChartsModel) {
            return 0;
        }
        if (t instanceof ChartItemModel) {
            return 1;
        }
        return super.getItemViewType(t);
    }

    class GroupViewHolder extends AbstractExpandableAdapterItem {

        private TextView name;
        @Override
        public void onExpansionToggled(boolean expanded) {

            //TODO 展开动画
            Log.e("adapter", expanded + "");
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_charts_group;
        }

        @Override
        public void onBindViews(View root) {
            name=root.findViewById(R.id.name);
            root.setOnClickListener(v -> {
                doExpandOrUnexpand();
            });
        }

        @Override
        public void onSetViews() {
            Log.e("adapter", "");
        }

        @Override
        public void onUpdateViews(Object model, int position) {
            super.onUpdateViews(model, position);
            onExpansionToggled(getExpandableListItem().isExpanded());
            name.setText(((ChartsModel)model).getName());
        }
    }

    class ChildViewHolder extends AbstractAdapterItem {

        private View title;
        private TextView name,code,cur_price;
        private CustomQuotesView  item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_charts_child;
        }

        @Override
        public void onBindViews(View root) {
            title = root.findViewById(R.id.title);
            name = root.findViewById(R.id.name);
            code = root.findViewById(R.id.code);
            cur_price = root.findViewById(R.id.cur_price);
            root.setOnClickListener(v ->{
                UiUtils.goChartsDetails(context);
            });
        }

        @Override
        public void onSetViews() {

        }

        @Override
        public void onUpdateViews(Object model, int position) {
           title.setVisibility(View.GONE);

            ChartItemModel itemModel= (ChartItemModel) model;
            name.setText(itemModel.getStock_name());
            code.setText(itemModel.getStock_code());
            cur_price.setText(itemModel.getOffset_size());
        }
    }
}
