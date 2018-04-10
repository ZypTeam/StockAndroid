package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class ReplayModel implements Serializable {


    /**
     * checking_id : 1
     * title : 白云机场新闻
     * checking_date : 2018-03-14
     * view_count : 201
     * url : http://www.baidu.com
     */

    private int checking_id;
    private String title;
    private String checking_date;
    private int view_count;
    private String url;

    public int getChecking_id() {
        return checking_id;
    }

    public void setChecking_id(int checking_id) {
        this.checking_id = checking_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChecking_date() {
        return checking_date;
    }

    public void setChecking_date(String checking_date) {
        this.checking_date = checking_date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
