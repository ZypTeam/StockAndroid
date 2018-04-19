package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

/**
 * @author wangcc
 * @date 2018/4/19
 * @describe
 */
public class CheckVersionModel extends BaseModel {

    private VersionModel data;

    public VersionModel getData() {
        return data;
    }

    public void setData(VersionModel data) {
        this.data = data;
    }
}
