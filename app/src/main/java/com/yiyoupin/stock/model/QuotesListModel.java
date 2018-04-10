package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class QuotesListModel extends BaseModel {

    private QuotesListModel.Data data;

    public final class Data{
        private List<QuotesModel> billlist;

        private List<ZhengquanModel> plateindex;

        public List<QuotesModel> getTopcharts() {
            return billlist;
        }

        public void setTopcharts(List<QuotesModel> topcharts) {
            this.billlist = topcharts;
        }

        public List<ZhengquanModel> getPlateindex() {
            return plateindex;
        }

        public void setPlateindex(List<ZhengquanModel> plateindex) {
            this.plateindex = plateindex;
        }
    }

    public QuotesListModel.Data getData() {
        return data;
    }

    public void setData(QuotesListModel.Data data) {
        this.data = data;
    }
}
