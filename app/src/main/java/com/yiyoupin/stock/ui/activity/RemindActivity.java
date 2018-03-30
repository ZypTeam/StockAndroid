package com.yiyoupin.stock.ui.activity;

import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.RemindView;

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

    @Override
    public int getLayoutResId() {
        return R.layout.activity_remind;
    }

    @Override
    public void initDatas() {

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
}
