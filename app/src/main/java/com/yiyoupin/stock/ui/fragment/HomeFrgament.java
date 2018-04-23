package com.yiyoupin.stock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.widget.GlideCircleTransform;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.ui.activity.HomeMoreActivity;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.HomeBottomQuotesView;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${首页}
 */
public class HomeFrgament extends BaseStockFragment {


    protected ImageView imgHead;
    protected TextView editSearch;
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
    private View newspaper, charts, notice, replay;

    private HomeListAdapter adapter;

    private HomeModel.HomeDataModel homeDataModel;

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
        editSearch = (TextView) rootView.findViewById(R.id.edit_search);
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

        newspaper = rootView.findViewById(R.id.newspaper);
        charts = rootView.findViewById(R.id.charts);
        notice = rootView.findViewById(R.id.notice);
        replay = rootView.findViewById(R.id.replay);

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

                if (homeDataModel != null) {
                    adapter.setType(HomeListAdapter.TYPE_STRATEGIES);
                    adapter.refreshList(homeDataModel.stocktactics);
                    adapter.notifyDataSetChanged();

                    if(homeDataModel.stocktactics!=null&&homeDataModel.stocktactics.size()>5){
                        textMore.setVisibility(View.VISIBLE);
                    }else{
                        textMore.setVisibility(View.GONE);
                    }

                }

            }
        });

        newspaper.setOnClickListener(v -> {
            UiUtils.goNewsPaparList(mContext);
        });

        charts.setOnClickListener(v -> {
            UiUtils.goCharrtsList(mContext);
        });

        notice.setOnClickListener(v -> {
            UiUtils.goNoticeList(mContext);
        });

        replay.setOnClickListener(v -> {
            UiUtils.goReplayList(mContext);
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


                if (homeDataModel != null) {
                    adapter.setType(HomeListAdapter.TYPE_FEATURED);
                    adapter.refreshList(homeDataModel.buyselection);
                    adapter.notifyDataSetChanged();
                    if(homeDataModel.buyselection!=null&&homeDataModel.buyselection.size()>5){
                        textMore.setVisibility(View.VISIBLE);
                    }else{
                        textMore.setVisibility(View.GONE);
                    }
                }
            }
        });

//        editSearch.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
//                    if (!TextUtils.isEmpty(editSearch.getText().toString())){
//                        UiUtils.goSearch(mContext,editSearch.getText().toString());
//                    }else {
//                        showToast("不能为空");
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });

        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtils.goSearch(mContext, editSearch.getText().toString());
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

                if (homeDataModel != null) {
                    adapter.setType(HomeListAdapter.TYPE_FORM);
                    adapter.refreshList(homeDataModel.technology);
                    adapter.notifyDataSetChanged();

                    if(homeDataModel.technology!=null&&homeDataModel.technology.size()>5){
                        textMore.setVisibility(View.VISIBLE);
                    }else{
                        textMore.setVisibility(View.GONE);
                    }
                }

            }
        });

        textMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(HomeMoreActivity.TYPE, adapter.getType());
                goActivity(bundle, HomeMoreActivity.class);
            }
        });


        Glide.with(mContext)
                .load(R.mipmap.logo)
                .transform(new CenterCrop(mContext), new GlideCircleTransform(mContext))
               /* .placeholder(errorResId)
                .error(errorResId)*/
                .crossFade()
                .into(imgHead);

        getListHome();
    }


    private void getListHome() {
        showLoadDialog();
        addNetwork(Api.getInstance().getService(ApiService.class).getHomeNet()
                , new Action1<HomeModel>() {
                    @Override
                    public void call(HomeModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            adapter.setType(HomeListAdapter.TYPE_STRATEGIES);
                            if (model.data != null) {
                                homeDataModel = model.data;
                                adapter.refreshList(model.data.stocktactics);


                                if(homeDataModel.stocktactics!=null&&homeDataModel.stocktactics.size()>5){
                                    textMore.setVisibility(View.VISIBLE);
                                }else{
                                    textMore.setVisibility(View.GONE);
                                }

                                if (model.data.plateindex != null) {
                                    if (model.data.plateindex.size() >= 1) {
                                        viewShangzheng.setVisibility(View.VISIBLE);
                                        viewShangzheng.setData(model.data.plateindex.get(0));
                                    }

                                    if (model.data.plateindex.size() >= 2) {
                                        viewShenzheng.setVisibility(View.VISIBLE);
                                        viewShenzheng.setData(model.data.plateindex.get(1));
                                    }

                                    if (model.data.plateindex.size() >= 3) {
                                        viewChuangye.setVisibility(View.VISIBLE);
                                        viewChuangye.setData(model.data.plateindex.get(2));
                                    }

                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });

    }


}
