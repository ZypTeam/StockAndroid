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
        private List<ChartItemModel> detail_list;

        public List<ChartItemModel> getDetail_list() {
            return detail_list;
        }

        public void setDetail_list(List<ChartItemModel> detail_list) {
            this.detail_list = detail_list;
        }
    }
}
