package com.yiyoupin.stock.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.ui.HomeListModel;
import com.yiyoupin.stock.ui.adapter.NewsAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.kline.FiveDayChartFragment;
import com.yiyoupin.stock.ui.view.kline.KLineChartFragment;
import com.yiyoupin.stock.ui.view.kline.SimpleFragmentPagerAdapter;
import com.yiyoupin.stock.ui.view.kline.TimeLineChartFragment;
import com.yiyoupin.stock.ui.view.kline.view.NoTouchScrollViewpager;

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
    private NewsAdapter newsAdapter;

    public static String ID = "id";
    public static String CODE = "code";
    private String stockCode = "";
    private String stockID = "";
    private boolean fromSeach = false;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_stock_show;
    }

    @Override
    public void initDatas() {
        newsAdapter = new NewsAdapter(getSupportFragmentManager());
        stockCode = getIntent().getStringExtra(CODE);
        stockID = getIntent().getStringExtra(ID);

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

    }

    @Override
    public void initAction() {
        titlebar.setTitle("个股展示");

        tab.setTabTextColors(0xff9a9a9a, mContext.getResources().getColor(R.color.color_red));
        tab.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.color_red));
        Fragment[] fragments = {TimeLineChartFragment.newInstance(1), FiveDayChartFragment.newInstance(),
                KLineChartFragment.newInstance(1), KLineChartFragment.newInstance(7),
                KLineChartFragment.newInstance(30)};

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

        viewpagerNews.setAdapter(newsAdapter);
        tabNews.setupWithViewPager(viewpagerNews);


        layoutRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UiUtils.goRemindActivity(StockShowActivity.this);
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
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        hideLoadDialog();
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
                , new Action1<HomeListModel>() {
                    @Override
                    public void call(HomeListModel model) {
                        hideLoadDialog();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });
    }


}
