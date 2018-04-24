package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseView;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3015:54
 * @Email zyp@jusfoun.com
 * @Description ${个股提醒 View}
 */
public class RemindView extends BaseView {
    protected View rootView;
    protected TextView textTitle;
    protected TextView textMoneyType;
    protected EditText editIinput;
    protected TextView textLvType;
    protected LinearLayout layoutEdit;


    public static int TYPE_ZHANG = 1;
    public static int TYPE_DIE = 2;
    public static int TYPE_RIZHANG = 3;
    public static int TYPE_RIDIE = 4;
    public static int TYPE_GONGGAO = 5;
    public static int TYPE_BUYDIAN = 6;
    public static int TYPE_MAIDIAN = 7;
    public static int TYPE_VIP = 8;
    protected ImageView imgKaiguan;

    protected int isRemind = 0;// 0 不提醒 1提醒


    public RemindView(Context context) {
        super(context);
    }

    public RemindView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RemindView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.view_remid, this, true);
        initView(this);
    }

    @Override
    protected void initActions() {

    }

    private void initView(View rootView) {
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textMoneyType = (TextView) rootView.findViewById(R.id.text_money_type);
        editIinput = (EditText) rootView.findViewById(R.id.editIinput);
        textLvType = (TextView) rootView.findViewById(R.id.text_lv_type);
        layoutEdit = (LinearLayout) rootView.findViewById(R.id.layout_edit);
        imgKaiguan = (ImageView) rootView.findViewById(R.id.img_kaiguan);


        imgKaiguan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRemind == 0) {
                    isRemind = 1;
                    imgKaiguan.setImageResource(R.drawable.img_remid_open);
                } else {
                    isRemind = 0;
                    imgKaiguan.setImageResource(R.drawable.img_remid_close);
                }

            }
        });
    }


    public void setTitle(int type) {
        if (type == TYPE_ZHANG) {
            textTitle.setText("股价涨到");
            layoutEdit.setVisibility(VISIBLE);
            textMoneyType.setVisibility(VISIBLE);
            textLvType.setVisibility(GONE);
        } else if (type == TYPE_DIE) {
            textTitle.setText("股价跌到");
            layoutEdit.setVisibility(VISIBLE);
            textMoneyType.setVisibility(VISIBLE);
            textLvType.setVisibility(GONE);
        } else if (type == TYPE_RIZHANG) {
            textTitle.setText("日涨幅超");
            layoutEdit.setVisibility(VISIBLE);
            textMoneyType.setVisibility(GONE);
            textLvType.setVisibility(VISIBLE);
        } else if (type == TYPE_RIDIE) {
            textTitle.setText("日跌幅超");
            layoutEdit.setVisibility(VISIBLE);
            textMoneyType.setVisibility(GONE);
            textLvType.setVisibility(VISIBLE);
        } else if (type == TYPE_GONGGAO) {
            textTitle.setText("公告提醒");
            layoutEdit.setVisibility(GONE);
        } else if (type == TYPE_BUYDIAN) {
            textTitle.setText("买点提醒");
            layoutEdit.setVisibility(GONE);
        } else if (type == TYPE_MAIDIAN) {
            textTitle.setText("卖点提醒");
            layoutEdit.setVisibility(GONE);
        } else if (type == TYPE_VIP) {
            textTitle.setText("VIP服务推送");
            layoutEdit.setVisibility(GONE);
        }

    }


    public String getText() {
        return editIinput.getText().toString();
    }

    public String getState() {
        return isRemind + "";
    }
}
