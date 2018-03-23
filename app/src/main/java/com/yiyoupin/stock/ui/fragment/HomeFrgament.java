package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.activity.HomeMoreActivity;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.HomeBottomQuotesView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${首页}
 */
public class HomeFrgament extends BaseStockFragment {


    protected ImageView imgHead;
    protected EditText editSearch;
    protected ImageView imgCommnet;
    protected TextView textCelue;
    protected View lineCelue;
    protected TextView textJingxuan;
    protected View lineJingxuan;
    protected RelativeLayout layoutJingxuan;
    protected TextView textXingtai;
    protected View lineXingtai;
    protected RelativeLayout layoutXingtai;
    protected RelativeLayout layoutCelue;
    protected RecyclerView recyclerView;
    protected TextView textMore;
    protected HomeBottomQuotesView viewShangzheng;
    protected HomeBottomQuotesView viewShenzheng;
    protected HomeBottomQuotesView viewChuangye;

    private HomeListAdapter adapter;

    public static HomeFrgament getInstance() {
        HomeFrgament fragment = new HomeFrgament();
        return fragment;
    }


    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initDatas() {
        adapter = new HomeListAdapter(mContext);

    }

    @Override
    public void initView(View rootView) {
        imgHead = (ImageView) rootView.findViewById(R.id.img_head);
        editSearch = (EditText) rootView.findViewById(R.id.edit_search);
        imgCommnet = (ImageView) rootView.findViewById(R.id.img_commnet);
        textCelue = (TextView) rootView.findViewById(R.id.text_celue);
        lineCelue = (View) rootView.findViewById(R.id.line_celue);
        textJingxuan = (TextView) rootView.findViewById(R.id.text_jingxuan);
        lineJingxuan = (View) rootView.findViewById(R.id.line_jingxuan);
        layoutJingxuan = (RelativeLayout) rootView.findViewById(R.id.layout_jingxuan);
        textXingtai = (TextView) rootView.findViewById(R.id.text_xingtai);
        lineXingtai = (View) rootView.findViewById(R.id.line_xingtai);
        layoutXingtai = (RelativeLayout) rootView.findViewById(R.id.layout_xingtai);
        layoutCelue = (RelativeLayout) rootView.findViewById(R.id.layout_celue);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        textMore = (TextView) rootView.findViewById(R.id.text_more);
        viewShangzheng = (HomeBottomQuotesView) rootView.findViewById(R.id.view_shangzheng);
        viewShenzheng = (HomeBottomQuotesView) rootView.findViewById(R.id.view_shenzheng);
        viewChuangye = (HomeBottomQuotesView) rootView.findViewById(R.id.view_chuangye);

    }

    @Override
    public void initAction() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        layoutCelue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCelue.setTextColor(0xffff4b36);
                textJingxuan.setTextColor(mContext.getResources().getColor(R.color.write));
                textXingtai.setTextColor(mContext.getResources().getColor(R.color.write));

                lineCelue.setVisibility(View.VISIBLE);
                lineJingxuan.setVisibility(View.GONE);
                lineXingtai.setVisibility(View.GONE);

                adapter.setType(HomeListAdapter.TYPE_STRATEGIES);
                adapter.notifyDataSetChanged();

            }
        });

        layoutJingxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCelue.setTextColor(mContext.getResources().getColor(R.color.write));
                textJingxuan.setTextColor(0xffff4b36);
                textXingtai.setTextColor(mContext.getResources().getColor(R.color.write));

                lineCelue.setVisibility(View.GONE);
                lineJingxuan.setVisibility(View.VISIBLE);
                lineXingtai.setVisibility(View.GONE);

                adapter.setType(HomeListAdapter.TYPE_FEATURED);
                adapter.notifyDataSetChanged();
            }
        });

        layoutXingtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textCelue.setTextColor(mContext.getResources().getColor(R.color.write));
                textJingxuan.setTextColor(mContext.getResources().getColor(R.color.write));
                textXingtai.setTextColor(0xffff4b36);

                lineCelue.setVisibility(View.GONE);
                lineJingxuan.setVisibility(View.GONE);
                lineXingtai.setVisibility(View.VISIBLE);

                adapter.setType(HomeListAdapter.TYPE_FORM);
                adapter.notifyDataSetChanged();
            }
        });

        textMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(HomeMoreActivity.TYPE,adapter.getType());
                goActivity(bundle,HomeMoreActivity.class);
            }
        });

        List<BaseModel> list = new ArrayList<>();
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        list.add(new HomeListModel());
        adapter.setType(HomeListAdapter.TYPE_STRATEGIES);
        adapter.refreshList(list);

        viewShangzheng.setData();
        viewShenzheng.setData();
        viewChuangye.setData();
    }
}
