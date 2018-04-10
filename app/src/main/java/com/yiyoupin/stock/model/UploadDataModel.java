package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class UploadDataModel extends BaseModel {
    private UploadModel data;

    public UploadModel getData() {
        return data;
    }

    public void setData(UploadModel data) {
        this.data = data;
    }
}
