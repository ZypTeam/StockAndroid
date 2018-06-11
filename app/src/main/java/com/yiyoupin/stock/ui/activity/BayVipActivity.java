package com.yiyoupin.stock.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.BuyModel;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.ImageLoderUtil;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.FormView;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class BayVipActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView aboutUs;
    protected FormView nameUser;
    protected FormView startTime;
    protected FormView endTime;
    protected FormView price;
    protected ImageView qrCode;
    protected View view;
    protected View bottomLine;
    protected ImageView qrCodeZfb;
    private UserModel userModel;

    private String tactics_id = "0";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    public void initDatas() {
//        tactics_id = getIntent().getStringExtra("tactics_id");
        userModel = UserInfoDelegate.getInstance().getUserInfo();
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        aboutUs = (TextView) findViewById(R.id.about_us);
        nameUser = (FormView) findViewById(R.id.name_user);
        startTime = (FormView) findViewById(R.id.start_time);
        endTime = (FormView) findViewById(R.id.end_time);
        price = (FormView) findViewById(R.id.price);
        qrCode = (ImageView) findViewById(R.id.qr_code);
        view = (View) findViewById(R.id.view);
        bottomLine = (View) findViewById(R.id.bottom_line);
        qrCodeZfb = (ImageView) findViewById(R.id.qr_code_zfb);

    }

    @Override
    public void initAction() {
        titleView.setTitle("购买VIP服务器");

        if (userModel != null && !TextUtils.isEmpty(userModel.getNick_name()))
            nameUser.setContent(userModel.getNick_name());

        startTime.setName("开始时间");


        endTime.setName("结束时间");

        nameUser.setName("姓名");

        price.setName("价格");


        getStrategiesNet();
    }


    /**
     * 选股策略 历史
     */
    private void getStrategiesNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("tactics_id", tactics_id + "");
        addNetwork(Api.getInstance().getService(ApiService.class).getBuyInfo(map)
                , new Action1<BuyModel>() {
                    @Override
                    public void call(BuyModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                nameUser.setContent(model.data.user_name);
                                startTime.setContent(model.data.begin_time);
                                endTime.setContent(model.data.end_time);
                                price.setContent(model.data.price);

                                ImageLoderUtil.loadNormalImg(BayVipActivity.this,qrCode,model.data.wx_code_url,R.mipmap.logo);
                                ImageLoderUtil.loadNormalImg(BayVipActivity.this,qrCodeZfb,model.data.alipay_code_url,R.mipmap.logo);
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
