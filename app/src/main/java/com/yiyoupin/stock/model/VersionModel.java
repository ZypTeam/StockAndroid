package com.yiyoupin.stock.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/19
 * @describe
 */
public class VersionModel implements Serializable {

    private int update;
    private String describe;
    private String url;
    private String versionname;
    private String titletext;
    private String cacletext;
    private String updatetext;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getTitletext() {
        return titletext;
    }

    public void setTitletext(String titletext) {
        this.titletext = titletext;
    }

    public String getCacletext() {
        return cacletext;
    }

    public void setCacletext(String cacletext) {
        this.cacletext = cacletext;
    }

    public String getUpdatetext() {
        return updatetext;
    }

    public void setUpdatetext(String updatetext) {
        this.updatetext = updatetext;
    }
}
