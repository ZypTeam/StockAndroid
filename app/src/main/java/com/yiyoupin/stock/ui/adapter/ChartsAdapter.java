package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.jusfoun.baselibrary.Util.LogUtil;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.expandablerecycleradapter.adapter.BaseExpandableAdapter;
import com.yiyoupin.stock.expandablerecycleradapter.viewholder.AbstractAdapterItem;
import com.yiyoupin.stock.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;
import com.yiyoupin.stock.model.QuotesItemModel;
import com.yiyoupin.stock.model.QuotesModel;
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
        if (t instanceof QuotesModel) {
            return 0;
        }
        if (t instanceof QuotesItemModel) {
            return 1;
        }
        return super.getItemViewType(t);
    }

    class GroupViewHolder extends AbstractExpandableAdapterItem {

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
        }
    }

    class ChildViewHolder extends AbstractAdapterItem {

        private View title;
        private CustomQuotesView  item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_charts_child;
        }

        @Override
        public void onBindViews(View root) {
            title = root.findViewById(R.id.title);
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


        }
    }
}
