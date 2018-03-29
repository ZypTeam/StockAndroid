package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.QuotesItemModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.ui.adapter.QuotesAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${行情}
 */
public class QuotesFrgament extends BaseStockFragment {

    protected BackTitleView titleView;
    protected TextView shangzheng;
    protected TextView shenzheng;
    protected TextView chuangye;
    protected RecyclerView list;

    private QuotesAdapter adapter;

    public static QuotesFrgament getInstance() {
        QuotesFrgament fragment = new QuotesFrgament();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_quotes;
    }

    @Override
    public void initDatas() {
        adapter=new QuotesAdapter(mContext);
    }

    @Override
    public void initView(View rootView) {
        titleView = (BackTitleView) rootView.findViewById(R.id.title_view);
        shangzheng = (TextView) rootView.findViewById(R.id.shangzheng);
        shenzheng = (TextView) rootView.findViewById(R.id.shenzheng);
        chuangye = (TextView) rootView.findViewById(R.id.chuangye);
        list = (RecyclerView) rootView.findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);

    }

    @Override
    public void initAction() {
        shangzheng.setText(getZhishu("上证指数\n","3422.02\n","+52.53% +12.3%"));
        shenzheng.setText(getZhishu("深证指数\n","3422.02\n","+52.53% +12.3%"));
        chuangye.setText(getZhishu("创业板\n","3422.02\n","+52.53% +12.3%"));

        titleView.setTitle("行情");
        titleView.setRightIcon(R.mipmap.icon_search);
        titleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void refreshData() {
        List list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuotesModel model=new QuotesModel();
            if (i==0){
                model.setExpanded(true);
            }else {
                model.setExpanded(false);
            }
            list.add(model);
        }
        adapter.updateData(list);
    }

    private SpannableStringBuilder getZhishu(String name,String num,String pre){
        int len1=name.length();
        int len2 =name.length()+num.length();
        int len3=name.length()+num.length()+pre.length();
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(name+num+pre);

        stringBuilder.setSpan(new AbsoluteSizeSpan(19,true),0,len1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new AbsoluteSizeSpan(15,true),len1,len2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new AbsoluteSizeSpan(10,true),len2,len3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}
