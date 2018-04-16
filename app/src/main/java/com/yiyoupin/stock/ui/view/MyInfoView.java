package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.FlipListener;
import com.yiyoupin.stock.comment.ViewLoadingListener;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.ui.util.ImageLoderUtil;
import com.yiyoupin.stock.ui.util.UiUtils;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class MyInfoView extends ConstraintLayout {

    private CompositeSubscription mCompositeSubscription=new CompositeSubscription();

    protected TextView username;
    protected TextView consume;
    protected TextView balance;
    protected TextView editPassword;
    protected TextView payInfo;
    protected TextView checkUpdate;
    protected TextView aboutUs;
    protected TextView msgList;
    protected TextView exit;
    private ImageView iconHead;
    private TextView editPersion;
    private Context mContext;
    public MyInfoView(Context context) {
        this(context, null);
    }

    public MyInfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAction();
    }
    
    private void initView(Context context){
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_my_info, this);

        iconHead = findViewById(R.id.icon_head);
        editPersion = findViewById(R.id.edit_info);
        username = (TextView) findViewById(R.id.username);
        consume = (TextView) findViewById(R.id.consume);
        balance = (TextView) findViewById(R.id.balance);
        editPassword = (TextView) findViewById(R.id.edit_password);
        payInfo = (TextView) findViewById(R.id.pay_info);
        checkUpdate = (TextView) findViewById(R.id.check_update);
        aboutUs = (TextView) findViewById(R.id.about_us);
        msgList = (TextView) findViewById(R.id.msg_list);
        exit = (TextView) findViewById(R.id.exit);
        
    }
    
    private void initAction(){
        iconHead.setOnClickListener(v -> {
//            goActivity(null, LoginActivity.class);
        });

        editPersion.setOnClickListener(v -> {
            UiUtils.goEditInfo(mContext);
        });

        aboutUs.setOnClickListener(v -> {
            UiUtils.goAboutUs(mContext);
        });

        payInfo.setOnClickListener(v -> {
            UiUtils.goPayList(mContext);
        });

        editPassword.setOnClickListener(v -> {
            UiUtils.goChangePass(mContext);
        });

        msgList.setOnClickListener(v -> {
            UiUtils.goMsgList(mContext);
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLogin();
            }
        });
    }

    private void exitLogin(){
        if (flipListener!=null){
            flipListener.flip(false);
            return;
        }
        if (loadingListener!=null){
            loadingListener.showLoading();
        }
        addNetwork(Api.getInstance().getService(ApiService.class).loginOut()
                , new Action1<NoDataModel>() {
                    @Override
                    public void call(NoDataModel userDataModel) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        if (userDataModel.getCode()==0) {
                            UserInfoDelegate.getInstance().clearUser();
                            Toast.makeText(mContext,"退出成功",Toast.LENGTH_SHORT).show();
                            if (flipListener!=null){
                                flipListener.flip(false);
                            }
                        }else {
                            Toast.makeText(mContext,userDataModel.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        Toast.makeText(mContext,"退出失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getUserInfo(){
        if (loadingListener!=null){
            loadingListener.showLoading();
        }
        addNetwork(Api.getInstance().getService(ApiService.class).getUserInfo(new HashMap<>())
                , new Action1<UserDataModel>() {
                    @Override
                    public void call(UserDataModel userDataModel) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        if (userDataModel.getCode()==0){
                            UserInfoDelegate.getInstance().saveUserInfo(userDataModel.getData());
                            username.setText(userDataModel.getData().getName());
                            ImageLoderUtil.loadCircleImage(mContext, iconHead, userDataModel.getData().getUser_picture(), R.mipmap.ic_launcher_round);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (loadingListener!=null){
                            loadingListener.hideLoading();
                        }
                        Toast.makeText(mContext,"退出失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public <T extends BaseModel> void addNetwork(rx.Observable<T> observable, Action1<T> next, Action1<Throwable> error){
        mCompositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(next, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                LogUtil.e("throwable",throwable.getMessage());
                                if (error!=null) {
                                    error.call(throwable);
                                }
                            }
                        })
        );
    }

    @Override
    protected void onDetachedFromWindow() {
        mCompositeSubscription.clear();
        super.onDetachedFromWindow();
    }

    private ViewLoadingListener loadingListener;

    public void setLoadingListener(ViewLoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    private FlipListener flipListener;

    public void setFlipListener(FlipListener flipListener) {
        this.flipListener = flipListener;
    }
}