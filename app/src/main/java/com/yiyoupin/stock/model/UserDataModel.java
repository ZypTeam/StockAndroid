package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

/**
 * @author wangcc
 * @date 2018/4/9
 * @describe
 */

public class UserDataModel extends BaseModel {

    private UserModel data;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
