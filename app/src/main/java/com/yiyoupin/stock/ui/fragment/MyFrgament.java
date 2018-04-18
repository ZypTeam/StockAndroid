package com.yiyoupin.stock.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.comment.FlipListener;
import com.yiyoupin.stock.comment.ViewLoadingListener;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.LoginView;
import com.yiyoupin.stock.ui.view.MyInfoView;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${我的}
 */
public class MyFrgament extends BaseStockFragment {

    protected LoginView loginView;
    protected MyInfoView myInfoView;

    private ValueAnimator valueAnimator1, valueAnimator2, valueAnimator3, valueAnimator4;
    private UserModel userModel;


    public static MyFrgament getInstance() {
        MyFrgament fragment = new MyFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {
        if (userModel==null){
            loginView.setVisibility(View.VISIBLE);
            myInfoView.setVisibility(View.INVISIBLE);
        }else {
            loginView.setVisibility(View.INVISIBLE);
            myInfoView.setVisibility(View.VISIBLE);
            myInfoView.getUserInfo();
        }

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initDatas() {
        userModel=UserInfoDelegate.getInstance().getUserInfo();
        rxManage.on(Constant.REGISTER_NAME, new Action1<Object>() {
            @Override
            public void call(Object o) {
                userModel= UserInfoDelegate.getInstance().getUserInfo();
                loginView.postDelayed(()->{
                    valueAnimator1.start();
                },1000);
            }
        });
    }

    @Override
    public void initView(View rootView) {
        loginView = (LoginView) rootView.findViewById(R.id.login_view);
        myInfoView = (MyInfoView) rootView.findViewById(R.id.my_info_view);

    }

    @Override
    public void initAction() {

        valueAnimator1 = ObjectAnimator.ofFloat(loginView, "rotationY", 0, 90f);
        valueAnimator2 = ObjectAnimator.ofFloat(myInfoView, "rotationY", -90, 0f);
        valueAnimator3 = ObjectAnimator.ofFloat(loginView, "rotationY", 90f, 0f);
        valueAnimator4 = ObjectAnimator.ofFloat(myInfoView, "rotationY", 0f, -90f);

        valueAnimator2.setInterpolator(new LinearInterpolator());
        valueAnimator2.setDuration(500);
        valueAnimator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myInfoView.postDelayed(() -> {
                    myInfoView.getUserInfo();
                },100);
            }
        });

        valueAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                loginView.setVisibility(View.GONE);
                myInfoView.setVisibility(View.VISIBLE);
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

            }
        });

        valueAnimator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                loginView.setVisibility(View.VISIBLE);
                myInfoView.setVisibility(View.GONE);
                valueAnimator3.start();
            }
        });

        valueAnimator4.setInterpolator(new LinearInterpolator());
        valueAnimator4.setDuration(500);

        myInfoView.setFlipListener(new FlipListener() {
            @Override
            public void flip(boolean flip) {
                valueAnimator4.start();
            }
        });

        myInfoView.setLoadingListener(new ViewLoadingListener() {
            @Override
            public void showLoading() {
                showLoadDialog();
            }

            @Override
            public void hideLoading() {
                hideLoadDialog();
            }
        });

        loginView.setFlipListener(new FlipListener() {
            @Override
            public void flip(boolean flip) {
                valueAnimator1.start();
            }
        });

        loginView.setLoadingListener(new ViewLoadingListener() {
            @Override
            public void showLoading() {
                showLoadDialog();
            }

            @Override
            public void hideLoading() {
                hideLoadDialog();
            }
        });

        WindowManager window = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = window.getDefaultDisplay().getWidth();
        loginView.setCameraDistance(10 * screenWidth);
        myInfoView.setCameraDistance(10 * screenWidth);
    }
}
