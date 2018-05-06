package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusfoun.baselibrary.Util.StringUtil;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.QuotesItemModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.model.StockModel;
import com.yiyoupin.stock.ui.activity.StockShowActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.CustomQuotesTitleView;

import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class QuotesAdapter extends ExpandableRecyclerViewAdapter<QuotesAdapter.QuotesGroupViewHolder, QuotesAdapter.QuotesChildViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public QuotesAdapter(Context ctx, List<QuotesModel> list) {
        super(list);
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public QuotesGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_quotes_group, parent, false);
        return new QuotesGroupViewHolder(v);
    }

    @Override
    public QuotesChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_quotes_child, parent, false);
        return new QuotesChildViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(QuotesChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.update(((QuotesModel) group).getStock_list().get(childIndex), group, childIndex);
    }

    @Override
    public void onBindGroupViewHolder(QuotesGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.update((QuotesModel) group, flatPosition);
    }

    class QuotesGroupViewHolder extends GroupViewHolder {

        private View line;
        private ImageView mArrow;
        private TextView name;

        public QuotesGroupViewHolder(View itemView) {
            super(itemView);
            mArrow = (ImageView) itemView.findViewById(R.id.arrow);
            name = itemView.findViewById(R.id.name);
            line = itemView.findViewById(R.id.line);

        }

        public void update(QuotesModel model, int position) {
            name.setText(model.getName());
            if (position == 0) {
                line.setVisibility(View.VISIBLE);
            } else {
                line.setVisibility(View.GONE);
            }
        }

        @Override
        public void expand() {
            animateExpand();
        }

        @Override
        public void collapse() {
            animateCollapse();
        }

        private void animateExpand() {
            RotateAnimation rotate =
                    new RotateAnimation(0, 90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            mArrow.setAnimation(rotate);
        }

        private void animateCollapse() {
            RotateAnimation rotate =
                    new RotateAnimation(90, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            mArrow.setAnimation(rotate);
        }
    }

    class QuotesChildViewHolder extends ChildViewHolder {

        private CustomQuotesTitleView title;
        private View bottomLine;
        private TextView name, code, cur_price,up_down;

        public QuotesChildViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
            code = itemView.findViewById(R.id.code);
            cur_price = itemView.findViewById(R.id.cur_price);
            bottomLine = itemView.findViewById(R.id.bottom_line);
            up_down = itemView.findViewById(R.id.up_down);
        }

        public void update(QuotesItemModel model, ExpandableGroup group, int position) {
            if (position == 0) {
                title.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
            }

            String groupName = group.getTitle();
            title.setData(groupName);
            if ("涨幅榜".equals(groupName)) {
                cur_price.setText(model.getTrade_volumn() + "");
                up_down.setText(model.getOffset_size()+"");
            } else if (StringUtil.equals("跌幅榜", groupName)) {
                up_down.setText(model.getOffset_size()+"");
                cur_price.setText(model.getTrade_volumn() + "");
            } else if (StringUtil.equals("成交额榜", groupName)) {
                up_down.setText(model.getOffset_size()+"");
                cur_price.setText(model.getTrade_amount() + "");
            } else if (StringUtil.equals("换手率榜", groupName)) {
                up_down.setText(model.getOffset_size()+"");
                cur_price.setText(model.getChange_rate() + "");
            } else if (StringUtil.equals("量比榜", groupName)) {
                up_down.setText(model.getOffset_size()+"");
                cur_price.setText(model.getTrade_volumn() + "");
            } else if (StringUtil.equals("新股行情", groupName)) {
                up_down.setText(model.getTrade_volumn()+"");
                cur_price.setText(model.getTrade_amount() + "");
            }

            if (position == group.getItemCount() - 1) {
                bottomLine.setVisibility(View.VISIBLE);
            } else {
                bottomLine.setVisibility(View.GONE);
            }
            name.setText(model.getStock_name());
            code.setText(model.getStock_code());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString(StockShowActivity.ID, model.getStock_id());
                    bundle.putString(StockShowActivity.CODE, model.getStock_code());
                    bundle.putString(StockShowActivity.CHOICENESS_ID, "");
                    UiUtils.goStockShowActivity(context, bundle);
                }
            });
        }
    }
}
