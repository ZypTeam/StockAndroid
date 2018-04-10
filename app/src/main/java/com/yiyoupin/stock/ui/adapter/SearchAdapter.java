package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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
        return new SearchViewHolder(view, context);
    }

    class SearchViewHolder extends BaseViewHolder<SearchModel> {

        protected TextView code;
        protected TextView name;
        protected TextView shortName;

        public SearchViewHolder(View itemView, Context mContext) {
            super(itemView, mContext);
            code = (TextView) itemView.findViewById(R.id.code);
            name = (TextView) itemView.findViewById(R.id.name);
            shortName = (TextView) itemView.findViewById(R.id.short_name);
        }

        @Override
        public void update(SearchModel model) {

            itemView.setOnClickListener(v -> {
                if (callBack != null) {
                    callBack.callBack(model);
                }
            });

            code.setText(model.getStock_code());
            name.setText(model.getStock_name());
            shortName.setText(model.getStock_code());

        }
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void callBack(SearchModel model);
    }

}
