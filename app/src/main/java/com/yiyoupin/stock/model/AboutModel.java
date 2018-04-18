package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/19
 * @describe
 */
public class AboutModel extends BaseModel {

    private AboutData data;

    public AboutData getData() {
        return data;
    }

    public void setData(AboutData data) {
        this.data = data;
    }

    public class AboutData implements Serializable{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
