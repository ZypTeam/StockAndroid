package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.util.List;

import retrofit2.http.QueryMap;

/**
 * @author wangcc
 * @date 2018/4/9
 * @describe
 */

public class ChartsListModel extends BaseModel {

    private Data data;

    public final class Data{
        private List<ChartsModel> topcharts;

        public List<ChartsModel> getTopcharts() {
            return topcharts;
        }

        public void setTopcharts(List<ChartsModel> topcharts) {
            this.topcharts = topcharts;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
