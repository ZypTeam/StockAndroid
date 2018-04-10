package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/9
 * @describe
 */

public class ChartItemModel implements Serializable {


    /**
     * yc_end_date : 2018-03-19
     * yc_start_date : 2018-03-15
     * stock_name : 中材节能
     * trade_amount : 1215559926.00
     * trade_volumn : 121368410
     * detail_id : 5D48049BB6F1B701C684BE3DCBCF071D
     * stock_code : 603126
     * offset_size : 20.99
     * trade_date : 2018-03-19
     */

    private String yc_end_date;
    private String yc_start_date;
    private String stock_name;
    private String trade_amount;
    private String trade_volumn;
    private String detail_id;
    private String stock_code;
    private String offset_size;
    private String trade_date;

    public String getYc_end_date() {
        return yc_end_date;
    }

    public void setYc_end_date(String yc_end_date) {
        this.yc_end_date = yc_end_date;
    }

    public String getYc_start_date() {
        return yc_start_date;
    }

    public void setYc_start_date(String yc_start_date) {
        this.yc_start_date = yc_start_date;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
    }

    public String getTrade_volumn() {
        return trade_volumn;
    }

    public void setTrade_volumn(String trade_volumn) {
        this.trade_volumn = trade_volumn;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOffset_size() {
        return offset_size;
    }

    public void setOffset_size(String offset_size) {
        this.offset_size = offset_size;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }
}
