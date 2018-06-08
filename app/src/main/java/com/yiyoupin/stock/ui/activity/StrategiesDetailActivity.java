package com.yiyoupin.stock.ui.activity;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.StrategiesDetailModel;
import com.yiyoupin.stock.ui.adapter.HomeListAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.Calendar;
import java.util.HashMap;

import rx.functions.Action1;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2814:32
 * @Email zyp@jusfoun.com
 * @Description ${策略详情activity}
 */
public class StrategiesDetailActivity extends BaseStockActivity {
    protected BackTitleView titlebar;
    protected TextView textTitle;
    protected TextView textDes;
    protected TextView textHisToday;
    protected RecyclerView recyclerToday;
    protected TextView textHisPhone;
    protected RecyclerView recyclerPhone;
    protected RecyclerView recyclerHis;
    protected RelativeLayout layoutNoVipToday;
    protected RelativeLayout layoutNoVipPhone;
    protected ImageView imgRiqi;
    protected Button btnBuy;
    protected EditText editPhone;
    protected ImageView imgKaiguan;
    protected RelativeLayout layoutPushPhone;
    protected LinearLayout layoutBuy;


    private HomeListAdapter todayAdapter, phoneAdapter, hisAdapter;

    public static String CHOICENESS_ID = "choiceness_id";
    private String choiceness_id = "";

    private boolean isOpen = false;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_strategies_detail;
    }

    @Override
    public void initDatas() {
        todayAdapter = new HomeListAdapter(mContext);
        phoneAdapter = new HomeListAdapter(mContext);
        hisAdapter = new HomeListAdapter(mContext);

        choiceness_id = getIntent().getStringExtra(CHOICENESS_ID);
    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);
        textTitle = (TextView) findViewById(R.id.text_title);
        textDes = (TextView) findViewById(R.id.text_des);
        textHisToday = (TextView) findViewById(R.id.text_his_today);
        recyclerToday = (RecyclerView) findViewById(R.id.recycler_today);
        textHisPhone = (TextView) findViewById(R.id.text_his_phone);
        recyclerPhone = (RecyclerView) findViewById(R.id.recycler_phone);
        recyclerHis = (RecyclerView) findViewById(R.id.recycler_his);
        layoutNoVipToday = (RelativeLayout) findViewById(R.id.layout_no_vip_today);
        layoutNoVipPhone = (RelativeLayout) findViewById(R.id.layout_no_vip_phone);
        imgRiqi = (ImageView) findViewById(R.id.img_riqi);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        imgKaiguan = (ImageView) findViewById(R.id.img_kaiguan);
        layoutPushPhone = (RelativeLayout) findViewById(R.id.layout_push_phone);
        layoutBuy = (LinearLayout) findViewById(R.id.layout_buy);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("策略详情");
        recyclerToday.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerToday.setAdapter(todayAdapter);

        recyclerPhone.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerPhone.setAdapter(phoneAdapter);

        recyclerHis.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerHis.setAdapter(hisAdapter);


        recyclerToday.setHasFixedSize(true);
        recyclerToday.setNestedScrollingEnabled(false);

        recyclerPhone.setHasFixedSize(true);
        recyclerPhone.setNestedScrollingEnabled(false);

        recyclerHis.setHasFixedSize(true);
        recyclerHis.setNestedScrollingEnabled(false);

        todayAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        phoneAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        hisAdapter.setType(HomeListAdapter.TYPE_STRATEGIES_DETAIL);

        todayAdapter.setChoicenessId(choiceness_id);
        phoneAdapter.setChoicenessId(choiceness_id);
        hisAdapter.setChoicenessId(choiceness_id);

        getTechnologyNet();


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgRiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(StrategiesDetailActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                if (view.isShown()) {
                                    Log.e(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);
                                    String  m = month+"";
                                    if(month<10){
                                        m = "0"+month;
                                    }

                                    String d = dayOfMonth+"";
                                    if(dayOfMonth<10){
                                      d ="0"+dayOfMonth;
                                    }

                                    getHisNet(year+"-"+m+"-"+d);
                                    calendar.set(Calendar.YEAR, year);
                                    calendar.set(Calendar.MONTH, month);
                                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                }

//                                mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        imgKaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOpen) {
                    isOpen  =!isOpen;
                    imgKaiguan.setImageResource(R.drawable.img_remid_open);
                } else {
                    isOpen  =!isOpen;
                    imgKaiguan.setImageResource(R.drawable.img_remid_close);
                }

            }
        });
    }


    /**
     * 策略详情
     */
    private void getTechnologyNet() {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("security_selection_id", choiceness_id);
        addNetwork(Api.getInstance().getService(ApiService.class).getStrategiesDetailNet(map)
                , new Action1<StrategiesDetailModel>() {
                    @Override
                    public void call(StrategiesDetailModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                todayAdapter.refreshList(model.data.today_stock);
                                phoneAdapter.refreshList(model.data.to_phone_stock);
                                hisAdapter.refreshList(model.data.history_stock);
                                if (model.data.tactic != null) {
                                    if (!TextUtils.isEmpty(model.data.tactic.tactics_name)) {
                                        textTitle.setText(model.data.tactic.tactics_name);
                                    }

                                    if (!TextUtils.isEmpty(model.data.tactic.description)) {
                                        textDes.setText(model.data.tactic.description);
                                    }
                                }


                                if (model.data.vip_status != null) {

                                    if (model.data.vip_status.isvip == 1) {
                                        layoutNoVipToday.setVisibility(View.GONE);
                                        layoutNoVipPhone.setVisibility(View.GONE);
                                        recyclerToday.setVisibility(View.VISIBLE);
                                        layoutPushPhone.setVisibility(View.VISIBLE);
                                        layoutBuy.setVisibility(View.GONE);
                                    } else {
                                        layoutNoVipToday.setVisibility(View.VISIBLE);
                                        layoutNoVipPhone.setVisibility(View.VISIBLE);
                                        recyclerToday.setVisibility(View.GONE);
                                        layoutPushPhone.setVisibility(View.GONE);
                                        layoutBuy.setVisibility(View.VISIBLE);
                                    }
                                }


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

    /**
     * 历史
     */
    private void getHisNet(String history_date) {
        showLoadDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("tactics_id", choiceness_id);
        map.put("page_size", "5");
        map.put("page_index", "1");
        map.put("history_date", history_date);
        addNetwork(Api.getInstance().getService(ApiService.class).gethistoryListNet(map)
                , new Action1<StrategiesDetailModel>() {
                    @Override
                    public void call(StrategiesDetailModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (model.data != null) {
                                hisAdapter.refreshList(model.data.rows);
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
