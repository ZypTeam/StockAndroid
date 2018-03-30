package com.yiyoupin.stock.ui.activity;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.model.CropOptions;
import com.jusfoun.baselibrary.Util.IOUtil;
import com.jusfoun.baselibrary.Util.PhoneUtil;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.ImageLoderUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangcc
 * @date 2018/3/25
 * @describe
 */

public class EditPersonInfoActivity extends BaseTakeActivity {

    private TextView head;
    private ImageView iconHead;

    private Subscription timer;
    protected TextView timerTxt;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_edit_person_info;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        head = findViewById(R.id.txt_head);
        iconHead = findViewById(R.id.icon_head);
        timerTxt = (TextView) findViewById(R.id.timer);
    }

    @Override
    public void initAction() {

        ImageLoderUtil.loadCircleImage(mContext,iconHead,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522439396278&di=25e6651bf56bc21ebaaf4c3df36f2502&imgtype=0&src=http%3A%2F%2Fpic38.nipic.com%2F20140306%2F251960_125327345000_2.jpg",R.mipmap.ic_launcher_round);
        timerTxt.setOnClickListener(v ->{
            timer = Observable.interval(1, TimeUnit.SECONDS)
                    .take(120)
                    .map(new Func1<Long, Long>() {
                        @Override
                        public Long call(Long aLong) {
                            return 120 - aLong;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {

                        @Override
                        public void onCompleted() {
                            timerTxt.setEnabled(true);
                            timerTxt.setText("重发");
                        }

                        @Override
                        public void onError(Throwable e) {
                            timerTxt.setEnabled(true);
                            timerTxt.setText("重发");
                        }

                        @Override
                        public void onNext(Long aLong) {
                            timerTxt.setEnabled(false);
                            timerTxt.setText(aLong+"s");
                        }
                    });
        });
        head.setOnClickListener(v -> {
            CropOptions cropOptions = new CropOptions.Builder()
                    .setAspectX(1)
                    .setAspectY(1)
                    .setOutputX(PhoneUtil.dip2px(mContext,200))
                    .setOutputY(PhoneUtil.dip2px(mContext,200))
                    .setWithOwnCrop(true)
                    .create();
            takePhoto.onPickFromGallery();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && timer.isUnsubscribed()) {
            timer.unsubscribe();
        }
    }
}
