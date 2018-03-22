package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.HomeBottomQuotesView;

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
        viewShangzheng.setData();
        viewShenzheng.setData();
        viewChuangye.setData();
    }
}
