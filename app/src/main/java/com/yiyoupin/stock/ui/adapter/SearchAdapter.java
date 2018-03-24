package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.SearchModel;
import com.yiyoupin.stock.ui.base.BaseAdapter;
import com.yiyoupin.stock.ui.base.BaseViewHolder;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class SearchAdapter extends BaseAdapter<SearchModel> {
    public SearchAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_search;
    }

    @Override
    protected BaseViewHolder getViewHolder(int viewType, View view) {
        return new SearchViewHolder(view,context);
    }

    class SearchViewHolder extends BaseViewHolder<SearchModel>{

        public SearchViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
        }

        @Override
        public void update(SearchModel model) {

        }
    }
}
