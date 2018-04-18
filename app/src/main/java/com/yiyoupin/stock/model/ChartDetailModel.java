package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class ChartDetailModel extends BaseModel {

    private ChartDetailData data;

    public ChartDetailData getData() {
        return data;
    }

    public void setData(ChartDetailData data) {
        this.data = data;
    }

    public class ChartDetailData{
        private String name;
        private List<ChartItemModel> buy_list;
        private List<ChartItemModel> sale_list;

        public List<ChartItemModel> getDetail_list() {
            return buy_list;
        }

        public void setDetail_list(List<ChartItemModel> detail_list) {
            this.buy_list = detail_list;
        }

        public List<ChartItemModel> getSale_list() {
            return sale_list;
        }

        public void setSale_list(List<ChartItemModel> sale_list) {
            this.sale_list = sale_list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
