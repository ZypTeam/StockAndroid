package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class StockModel extends BaseModel implements Serializable {

    /**
     * user_id : 1
     * stock_id : 3
     * stock_name : 东风汽车
     * stock_code : 600006
     * stock_price : 17.83
     * offset_size : 4.83
     */

    private int user_id;
    private String stock_id;
    private String stock_name;
    private String stock_code;
    private double stock_price;
    private double offset_size;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public double getStock_price() {
        return stock_price;
    }

    public void setStock_price(double stock_price) {
        this.stock_price = stock_price;
    }

    public double getOffset_size() {
        return offset_size;
    }

    public void setOffset_size(double offset_size) {
        this.offset_size = offset_size;
    }
}
