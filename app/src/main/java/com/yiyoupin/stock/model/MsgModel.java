package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/31
 * @describe
 */

public class MsgModel implements Serializable {

    /**
     * message_content : 600036(招商银行)股价32.12，涨幅为0.24%
     * message_time : 2018-05-06
     * stock_name : 招商银行
     * user_id : 1
     * message_title : 股票提醒
     * message_type : 1
     * id : 2
     * stock_id : 1
     * stock_code : 600036
     */

    private String message_content;
    private String message_time;
    private String stock_name;
    private int user_id;
    private String message_title;
    private int message_type;
    private int id;
    private int stock_id;
    private String stock_code;

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
