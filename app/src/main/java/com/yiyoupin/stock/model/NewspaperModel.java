package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe
 */

public class NewspaperModel implements Serializable {

    /**
     * daily_id : 1
     * title : 白云机场新闻
     * daily_date : 2018-03-14
     * view_count : 201
     * url : http://www.baidu.com
     */

    private int daily_id;
    private String title;
    private String describe;
    private String daily_date;
    private int view_count;
    private String url;

    public int getDaily_id() {
        return daily_id;
    }

    public void setDaily_id(int daily_id) {
        this.daily_id = daily_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDaily_date() {
        return daily_date;
    }

    public void setDaily_date(String daily_date) {
        this.daily_date = daily_date;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
