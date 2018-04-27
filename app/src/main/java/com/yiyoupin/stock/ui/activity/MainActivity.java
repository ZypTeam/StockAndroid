package com.yiyoupin.stock.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.huawei.hms.support.api.push.TokenResult;
import com.jusfoun.baselibrary.view.HomeViewPager;
import com.umeng.socialize.UMShareAPI;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.FragmentCallback;
import com.yiyoupin.stock.ui.adapter.HomeAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;

public class MainActivity extends BaseStockActivity implements FragmentCallback {

    protected HomeViewPager viewpager;
    protected ImageView imgHome;
    protected TextView textHome;
    protected RelativeLayout layoutHome;
    protected ImageView imgQuotes;
    protected TextView textQuotes;
    protected RelativeLayout layoutQuotes;
    protected ImageView imgOptional;
    protected TextView textOptional;
    protected RelativeLayout layoutOptional;
    protected ImageView imgMy;
    protected TextView textMy;
    protected RelativeLayout layoutMy;
    private HomeAdapter homeAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initDatas() {
        homeAdapter = new HomeAdapter(getSupportFragmentManager());

        Log.e("tag"," MiPushClient="+ MiPushClient.getRegId(this));

//        HMSAgent.Push.getToken(new GetTokenHandler() {
//            @Override
//            public void onResult(int rtnCode, TokenResult tokenResult) {
//                Log.e("tag""get token: end" + rtnCode);
//            }
//        });

        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rtnCode) {
                Log.e("tag","get token: end" + rtnCode);
            }
        });
    }

    @Override
    public void initView() {
        viewpager = (HomeViewPager) findViewById(R.id.viewpager);
        imgHome = (ImageView) findViewById(R.id.img_home);
        textHome = (TextView) findViewById(R.id.text_home);
        layoutHome = (RelativeLayout) findViewById(R.id.layout_home);
        imgQuotes = (ImageView) findViewById(R.id.img_quotes);
        textQuotes = (TextView) findViewById(R.id.text_quotes);
        layoutQuotes = (RelativeLayout) findViewById(R.id.layout_quotes);
        imgOptional = (ImageView) findViewById(R.id.img_optional);
        textOptional = (TextView) findViewById(R.id.text_optional);
        layoutOptional = (RelativeLayout) findViewById(R.id.layout_optional);
        imgMy = (ImageView) findViewById(R.id.img_my);
        textMy = (TextView) findViewById(R.id.text_my);
        layoutMy = (RelativeLayout) findViewById(R.id.layout_my);
    }

    @Override
    public void initAction() {
        viewpager.setNotTouchScoll(true);
        viewpager.setOffscreenPageLimit(4);

        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(0);
            }
        });
        layoutQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(1);
            }
        });
        layoutOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(2);
            }
        });
        layoutMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(3);
            }
        });
        viewpager.setAdapter(homeAdapter);

        select(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
    }

    private void select(int position) {
        viewpager.setCurrentItem(position, false);

        imgHome.setImageResource(R.drawable.img_home_normal);
        textHome.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutHome.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgQuotes.setImageResource(R.drawable.img_quotes_noraml);
        textQuotes.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutQuotes.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgOptional.setImageResource(R.drawable.img_optional_normal);
        textOptional.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutOptional.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgMy.setImageResource(R.drawable.img_my_normal);
        textMy.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutMy.setBackgroundResource(R.color.home_bottom_btn_normal);

        switch (position) {
            case 0:
                imgHome.setImageResource(R.drawable.img_home_select);
                textHome.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutHome.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 1:
                imgQuotes.setImageResource(R.drawable.img_quotes_selecter);
                textQuotes.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutQuotes.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 2:
                imgOptional.setImageResource(R.drawable.img_optional_select);
                textOptional.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutOptional.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 3:
                imgMy.setImageResource(R.drawable.img_my_select);
                textMy.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutMy.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            default:
                break;
        }
    }

    private long mLastTime;

    /**
     * 退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mLastTime > 0 && System.currentTimeMillis() - mLastTime <= 2000) {
                StockApplication.getBaseApplication().removeAll();
            } else {
                Toast.makeText(mContext, R.string.app_exit_string, Toast.LENGTH_SHORT).show();
                mLastTime = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onCallback(Fragment fragment) {


//        viewpager.setCurrentItem(1);
    }
}
