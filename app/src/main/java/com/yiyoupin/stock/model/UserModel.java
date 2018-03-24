package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 用户信息
 */

public class UserModel implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
