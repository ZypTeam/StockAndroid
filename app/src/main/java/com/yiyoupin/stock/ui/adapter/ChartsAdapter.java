package com.yiyoupin.stock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.ChartItemModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.CustomQuotesView;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsAdapter extends ExpandableRecyclerViewAdapter<ChartsAdapter.ChartGroupViewHolder,ChartsAdapter.ChartChildViewHolder> {
    private Context context;
    private LayoutInflater inflater;

    public ChartsAdapter(Context context,List<ChartsModel> list) {
        super(list);
        this.context=context;
        inflater=LayoutInflater.from(context);
//        setHasStableIds(true);
    }

    @Override
    public ChartGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_charts_group, parent, false);
        return new ChartGroupViewHolder(v);
    }

    @Override
    public ChartChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_charts_child, parent, false);
        return new ChartChildViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(ChartChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.update(((ChartsModel)group).getList().get(childIndex),childIndex);
    }

    @Override
    public void onBindGroupViewHolder(ChartGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.update((ChartsModel) group,flatPosition);
    }

    class ChartGroupViewHolder extends GroupViewHolder {

        private TextView name;
        private ImageView arrow;

        public ChartGroupViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            arrow=itemView.findViewById(R.id.arrow);
        }

        public void update(ChartsModel model,int position) {
            name.setText(model.getName());
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
            arrow.setAnimation(rotate);
        }

        private void animateCollapse() {
            RotateAnimation rotate =
                    new RotateAnimation(90, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }
    }

    class ChartChildViewHolder extends ChildViewHolder {

        private View title;
        private TextView name,code,cur_price;
        private CustomQuotesView  item;

        public ChartChildViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
            code = itemView.findViewById(R.id.code);
            cur_price = itemView.findViewById(R.id.cur_price);
            itemView.setOnClickListener(v ->{
                UiUtils.goChartsDetails(context);
            });
        }

        public void update(ChartItemModel model,int position) {
            if (position==0){
                title.setVisibility(View.VISIBLE);
            }else {
                title.setVisibility(View.GONE);
            }
            name.setText(model.getStock_name());
            code.setText(model.getStock_code());
            cur_price.setText(model.getOffset_size());
        }
    }
}
