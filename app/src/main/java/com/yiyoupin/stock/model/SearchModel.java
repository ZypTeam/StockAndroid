package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class SearchModel implements Serializable {


    /**
     * stock_id : 1
     * stock_code : 600008
     * stock_name : 白云机场
     * stock_price : 12.34
     * offset_size : 20
     */

    private int stock_id;
    private String stock_code;
    private String stock_name;
    private double stock_price;
    private double offset_size;

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
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

    @Override
    public String toString() {
        return "id=="+stock_id
                +"name=="+stock_name;
    }
}
