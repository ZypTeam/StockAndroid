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

import java.util.ArrayList;


/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class QuotesAdapter extends BaseExpandableAdapter {


    public QuotesAdapter(Context ctx) {
        super(new ArrayList());
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
            return R.layout.item_quotes_group;
        }

        @Override
        public void onBindViews(View root) {
            root.setOnClickListener(v -> {
                doExpandOrUnexpand();
            });
        }

        @Override
        public void onSetViews() {
            Log.e("adapter",   "");
        }

        @Override
        public void onUpdateViews(Object model, int position) {
            super.onUpdateViews(model, position);
            onExpansionToggled(getExpandableListItem().isExpanded());
        }
    }

    class ChildViewHolder extends AbstractAdapterItem {

        private View title;

        @Override
        public int getLayoutResId() {
            return R.layout.item_quotes_child;
        }

        @Override
        public void onBindViews(View root) {
            title = root.findViewById(R.id.title);
        }

        @Override
        public void onSetViews() {

        }

        @Override
        public void onUpdateViews(Object model, int position) {
            LogUtil.e("child","position=="+position);
            if (position == 1||position==7||position==13||position==19||position==25) {
                title.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
            }
        }
    }
}
