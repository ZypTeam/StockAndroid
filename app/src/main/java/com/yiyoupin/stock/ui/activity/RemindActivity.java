package com.yiyoupin.stock.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jusfoun.baselibrary.net.Api;
import com.jusfoun.baselibrary.permissiongen.Utils;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.model.VipModel;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.RemindView;

import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3015:47
 * @Email zyp@jusfoun.com
 * @Description ${个股提醒}
 */
public class RemindActivity extends BaseTakeActivity {
    protected RemindView viewZhang;
    protected RemindView viewDie;
    protected RemindView viewRizhang;
    protected RemindView viewRidie;
    protected RemindView viewGonggao;
    protected RemindView viewBuy;
    protected RemindView viewMai;
    protected RemindView viewVip;
    protected BackTitleView titlebar;


    private String stock_code;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_remind;
    }

    @Override
    public void initDatas() {
        stock_code = getIntent().getStringExtra("stock_code");
    }

    @Override
    public void initView() {
        viewZhang = (RemindView) findViewById(R.id.view_zhang);
        viewDie = (RemindView) findViewById(R.id.view_die);
        viewRizhang = (RemindView) findViewById(R.id.view_rizhang);
        viewRidie = (RemindView) findViewById(R.id.view_ridie);
        viewGonggao = (RemindView) findViewById(R.id.view_gonggao);
        viewBuy = (RemindView) findViewById(R.id.view_buy);
        viewMai = (RemindView) findViewById(R.id.view_mai);
        viewVip = (RemindView) findViewById(R.id.view_vip);
        titlebar = (BackTitleView) findViewById(R.id.titlebar);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("设置提醒");
        titlebar.setRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setRemindNet();
                isVip();
            }
        });

        viewZhang.setTitle(RemindView.TYPE_ZHANG);
        viewDie.setTitle(RemindView.TYPE_DIE);
        viewRizhang.setTitle(RemindView.TYPE_RIZHANG);
        viewRidie.setTitle(RemindView.TYPE_RIDIE);
        viewGonggao.setTitle(RemindView.TYPE_GONGGAO);
        viewBuy.setTitle(RemindView.TYPE_BUYDIAN);
        viewMai.setTitle(RemindView.TYPE_MAIDIAN);
        viewVip.setTitle(RemindView.TYPE_VIP);

    }


    protected void setRemindNet() {
        if (TextUtils.isEmpty(viewZhang.getText())) {
            Toast.makeText(mContext, "请输入股票涨到", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(viewZhang.getText())) {
            Toast.makeText(mContext, "请输入股票跌到", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(viewZhang.getText())) {
            Toast.makeText(mContext, "请输入日涨幅超", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(viewZhang.getText())) {
            Toast.makeText(mContext, "请输入日跌幅超", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(viewBuy.getText())) {
            Toast.makeText(mContext, "请输入买点提醒", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(viewMai.getText())) {
            Toast.makeText(mContext, "请输入卖点提醒", Toast.LENGTH_SHORT).show();
            return;
        }
        showLoadDialog();

        HashMap<String, String> params = new HashMap<>();
        params.put("up_to", viewZhang.getText());
        params.put("down_to", viewDie.getText());
        params.put("day_up_rate", viewRizhang.getText());
        params.put("day_down_rate", viewRidie.getText());
        params.put("up_to_remind", viewZhang.getState());
        params.put("down_to_remind", viewRidie.getState());
        params.put("day_up_rate_remind", viewRizhang.getState());
        params.put("day_down_rate_remind", viewRizhang.getState());
        params.put("stock_code", stock_code);
        addNetwork(Api.getInstance().getService(ApiService.class).setRemindNet(params)
                , new Action1<UserDataModel>() {
                    @Override
                    public void call(UserDataModel userDataModel) {
                        hideLoadDialog();
                        if (userDataModel.getCode() == 0) {
                            showToast("保存成功");
                            finish();
                        } else {
                            showToast(userDataModel.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });

    }


    protected void isVip() {
        showLoadDialog();

        addNetwork(Api.getInstance().getService(ApiService.class).getVipState()
                , new Action1<VipModel>() {
                    @Override
                    public void call(VipModel userDataModel) {
                        hideLoadDialog();
                        if (userDataModel.getCode() == 0) {


                            if (userDataModel.data != null && "1".equals(userDataModel.data.isvip)) {
                                setRemindNet();
                            }else{
                                goActivity(null,BayVipActivity.class);
                            }
                        } else {
                            showToast(userDataModel.getMsg());
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
