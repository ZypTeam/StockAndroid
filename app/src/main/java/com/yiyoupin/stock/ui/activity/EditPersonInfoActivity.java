package com.yiyoupin.stock.ui.activity;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.model.CropOptions;
import com.jusfoun.baselibrary.Util.IOUtil;
import com.jusfoun.baselibrary.Util.PhoneUtil;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;

import java.io.File;

/**
 * @author wangcc
 * @date 2018/3/25
 * @describe
 */

public class EditPersonInfoActivity extends BaseTakeActivity {

    private TextView head;
    private ImageView iconHead;

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
    }

    @Override
    public void initAction() {
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
}
