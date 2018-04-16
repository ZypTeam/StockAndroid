package com.yiyoupin.stock.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jusfoun.baselibrary.view.HomeViewPager;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.comment.FragmentCallback;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.adapter.MainAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.fragment.LoginFragment;
import com.yiyoupin.stock.ui.fragment.MainFragment;
import com.yiyoupin.stock.ui.util.TransformUtil;
import com.yiyoupin.stock.ui.view.transformer.FlipHorizontalTransformer;

import rx.functions.Action1;

public class MainActivity extends BaseStockActivity implements FragmentCallback {


    protected HomeViewPager viewpager;
    protected FrameLayout login;
    protected FrameLayout main;
    private MainAdapter homeAdapter;

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3, valueAnimator4;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initDatas() {

        rxManage.on(Constant.REGISTER_SUC, new Action1<Object>() {
            @Override
            public void call(Object o) {
                login.postDelayed(()->{
                    valueAnimator1.start();
                },1000);
            }
        });

    }

    @Override
    public void initView() {
        viewpager = (HomeViewPager) findViewById(R.id.viewpager);
        login = (FrameLayout) findViewById(R.id.login_fragment);
        main = (FrameLayout) findViewById(R.id.main_fragment);

        UserModel userModel=UserInfoDelegate.getInstance().getUserInfo();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.login_fragment,LoginFragment.getInstance(),"login")
                .add(R.id.main_fragment, MainFragment.getInstance(userModel==null?null:new Bundle()),"main")
                .commitAllowingStateLoss();

        if (userModel==null){
            login.setVisibility(View.VISIBLE);
            main.setVisibility(View.INVISIBLE);
        }else {
            login.setVisibility(View.INVISIBLE);
            main.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initAction() {

        /*homeAdapter = new MainAdapter(getSupportFragmentManager());
        viewpager.setAdapter(homeAdapter);
        viewpager.setNotTouchScoll(true);
        TransformUtil.reverse(viewpager, new FlipHorizontalTransformer());*/

        valueAnimator1 = ObjectAnimator.ofFloat(login, "rotationY", 0, 90f);
        valueAnimator2 = ObjectAnimator.ofFloat(main, "rotationY", -90, 0f);
        valueAnimator3 = ObjectAnimator.ofFloat(login, "rotationY", 90f, 0f);
        valueAnimator4 = ObjectAnimator.ofFloat(main, "rotationY", 0f, -90f);

        valueAnimator2.setInterpolator(new LinearInterpolator());
        valueAnimator2.setDuration(500);
        valueAnimator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });

        valueAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                login.setVisibility(View.GONE);
                main.setVisibility(View.VISIBLE);
                valueAnimator2.start();
            }
        });
        valueAnimator1.setInterpolator(new LinearInterpolator());
        valueAnimator1.setDuration(500);

        valueAnimator3.setInterpolator(new LinearInterpolator());
        valueAnimator3.setDuration(500);
        valueAnimator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.e("tag", "flipCardTwo2");
            }
        });

        valueAnimator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.e("tag", "flipCardTwo1");
                login.setVisibility(View.VISIBLE);
                main.setVisibility(View.GONE);
                valueAnimator3.start();
            }
        });

        valueAnimator4.setInterpolator(new LinearInterpolator());
        valueAnimator4.setDuration(500);
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

        if (fragment instanceof LoginFragment){
            valueAnimator1.start();
        }else {
            fragment.setArguments(null);
            valueAnimator4.start();
        }
//        viewpager.setCurrentItem(1);
    }
}
