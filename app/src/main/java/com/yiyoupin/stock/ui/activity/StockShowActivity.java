package com.yiyoupin.stock.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.event.IEvent;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.MyTacticsModel;
import com.yiyoupin.stock.model.NewModel;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.NewsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.event.FuTuEvent;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.CeluePopupWindow;
import com.yiyoupin.stock.ui.view.ShowBottomView;
import com.yiyoupin.stock.ui.view.StocksTopView;
import com.yiyoupin.stock.ui.view.kline.DrawingChartFragment;
import com.yiyoupin.stock.ui.view.kline.FiveDayChartFragment;
import com.yiyoupin.stock.ui.view.kline.KLineChartFragment;
import com.yiyoupin.stock.ui.view.kline.SimpleFragmentPagerAdapter;
import com.yiyoupin.stock.ui.view.kline.TimeLineChartFragment;
import com.yiyoupin.stock.ui.view.kline.view.NoTouchScrollViewpager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2815:49
 * @Email zyp@jusfoun.com
 * @Description ${个股展示}
 */
public class StockShowActivity extends BaseStockActivity {

    protected BackTitleView titlebar;
    protected TabLayout tab;
    protected NoTouchScrollViewpager viewPager;
    protected ViewPager viewpagerNews;
    protected TabLayout tabNews;
    protected CoordinatorLayout coordinatorLayout;
    protected RelativeLayout layoutRemind;
    protected RelativeLayout layoutAdd;
    protected RelativeLayout layoutDelete;
    protected StocksTopView viewTop;
    protected ShowBottomView viewShowBottom;
    protected TextView textBottomTitle;
    protected TextView textPrice;
    protected TextView textZhangdie1;
    protected TextView textZhangdie2;
    protected TextView textCelueName;
    protected RelativeLayout layoutCelue;
    private NewsAdapter newsAdapter;
    private RelativeLayout bottomLineLayout;

    public static String ID = "id";
    public static String CODE = "code";
    public static String CHOICENESS_ID = "choiceness_id";//策略id


    private String stockCode = "";//股票code
    private String stockID = "";//股票id
    private String choiceness_id = "";//策略id


    private boolean fromSeach = false;
    private Fragment[] fragments;

    private CeluePopupWindow celuePopupWindow;


    public static String  NO_TACTICS_ID="0";

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_stock_show;
    }

    @Override
    public void initDatas() {
        newsAdapter = new NewsAdapter(getSupportFragmentManager());
        stockCode = getIntent().getStringExtra(CODE);
        stockID = getIntent().getStringExtra(ID);
        choiceness_id = getIntent().getStringExtra(CHOICENESS_ID);
        fragments = new Fragment[5];

        celuePopupWindow = new CeluePopupWindow(mContext);

    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (NoTouchScrollViewpager) findViewById(R.id.view_pager);
        viewpagerNews = (ViewPager) findViewById(R.id.viewpager_news);
        tabNews = (TabLayout) findViewById(R.id.tab_news);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        layoutRemind = (RelativeLayout) findViewById(R.id.layout_remind);
        layoutAdd = (RelativeLayout) findViewById(R.id.layout_add);
        layoutDelete = (RelativeLayout) findViewById(R.id.layout_delete);
        viewTop = (StocksTopView) findViewById(R.id.view_top);
        bottomLineLayout = (RelativeLayout) findViewById(R.id.layout_time_line_bottom);
        viewShowBottom = (ShowBottomView) findViewById(R.id.view_show_bottom);
        textBottomTitle = (TextView) findViewById(R.id.text_bottom_title);
        textPrice = (TextView) findViewById(R.id.text_bottom_price);
        textZhangdie1 = (TextView) findViewById(R.id.text_zhangdie1);
        textZhangdie2 = (TextView) findViewById(R.id.text_zhangdie2);
        textCelueName = (TextView) findViewById(R.id.text_celue_name);
        layoutCelue = (RelativeLayout) findViewById(R.id.layout_celue);

    }

    @Override
    public void initAction() {


        tab.setTabTextColors(0xff9a9a9a, mContext.getResources().getColor(R.color.color_red));
        tab.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.color_red));
        fragments[0] = TimeLineChartFragment.newInstance(stockCode,NO_TACTICS_ID,0);
        fragments[1] = FiveDayChartFragment.newInstance(stockID, NO_TACTICS_ID,stockCode,1);
        fragments[2] = KLineChartFragment.newInstance(1, stockID,NO_TACTICS_ID, stockCode,2);
        fragments[3] = KLineChartFragment.newInstance(7, stockID, NO_TACTICS_ID,stockCode,3);
        fragments[4] = KLineChartFragment.newInstance(30, stockID, NO_TACTICS_ID,stockCode,4);
        String[] titles = {"分时图", "5日", "日K", "周K", "月"};
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(fragments.length);

        tab.setupWithViewPager(viewPager);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FullScreenChartActivity.launch(MainActivity.this, viewPager.getCurrentItem());
//            }
//        });


        tabNews.setTabTextColors(0xffe2e2e2, mContext.getResources().getColor(R.color.color_red));
        tabNews.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.color_red));




        layoutRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("stock_code", stockCode);
                UiUtils.goRemindActivity(StockShowActivity.this, bundle);
            }
        });
        layoutAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZiX();
//                Toast.makeText(mContext, "已添加至自选股", Toast.LENGTH_SHORT).show();
            }
        });
        layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteZiXun();
//                Toast.makeText(mContext, "已移除至自选股", Toast.LENGTH_SHORT).show();
            }
        });

        bottomLineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewShowBottom.getVisibility() == View.VISIBLE) {
                    viewShowBottom.setVisibility(View.GONE);
                } else {
                    viewShowBottom.setVisibility(View.VISIBLE);
                }
            }
        });

        layoutCelue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                celuePopupWindow.showAsDropDown(textCelueName);


            }
        });


        celuePopupWindow.setCallBack(new CeluePopupWindow.CallBack() {
            @Override
            public void onClick(MyTacticsModel model) {
                celuePopupWindow.dismiss();
                FuTuEvent event = new FuTuEvent();
                event.currentIndex = viewPager.getCurrentItem();
                event.tactics_id = model.tactics_id;

                textCelueName.setText(model.tactics_name);
                EventBus.getDefault().post(event);
            }
        });
        getDetailsNet();

    }


    private void addZiX() {
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("stock_code", stockCode);


        addNetwork(Api.getInstance().getService(ApiService.class).addZiXun(params)
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            Toast.makeText(mContext, "已添加至自选股", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, model.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }

    private void deleteZiXun() {
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("stock_code", stockCode);


        addNetwork(Api.getInstance().getService(ApiService.class).deleteZiXun(params)
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            Toast.makeText(mContext, "已移除至自选股", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, model.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }


    private void getDetailsNet() {
        if (TextUtils.isEmpty(stockID)) {
            return;
        }
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("stock_id", stockID);
        params.put("tactics_id", stockID);
        if (fromSeach)
            params.put("fromSeach", "true");


        addNetwork(Api.getInstance().getService(ApiService.class).getStockDetails(params)
                , new Action1<StockDetailModel>() {
                    @Override
                    public void call(StockDetailModel model) {
                        hideLoadDialog();
                        if (model.data != null && model.data.dapandata != null)
                            ((TimeLineChartFragment) fragments[0]).setData(model.data);

                        if (model.data != null) {
                            viewTop.setData(model.data);

                        }


                        if(model.data!=null&&model.data.stock_detail!=null){
                            titlebar.setTitle(model.data.stock_detail.stock_name);
                        }

                        textCelueName.setText("无策略");
                        if (model.data != null && model.data.my_tactics != null && model.data.my_tactics.size() > 0) {
                            celuePopupWindow.setData(model.data.my_tactics);
                        }

                        setBottomTitleData();

                        getNewsList();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                        getNewsList();
                    }
                });
    }


    private void getNewsList() {
        if (TextUtils.isEmpty(stockID)) {
            return;
        }
        showLoadDialog();
        HashMap<String, String> params = new HashMap();
        params.put("stock_id", stockID);

        addNetwork(Api.getInstance().getService(ApiService.class).getNewsList(params)
                , new Action1<NewModel>() {
                    @Override
                    public void call(NewModel model) {
                        hideLoadDialog();
                        newsAdapter.setData(model);

                        viewpagerNews.setAdapter(newsAdapter);
                        tabNews.setupWithViewPager(viewpagerNews);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }

    private void setBottomTitleData() {
        viewShowBottom.setData();
        textBottomTitle.setText("沪");
        textPrice.setText("3253.62");
        textZhangdie1.setText("+64.5");
        textZhangdie2.setText("+2.09%");

    }

    @Override
    public boolean isNeedSwipe() {
        return false;
    }




}
