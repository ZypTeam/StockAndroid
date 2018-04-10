package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class NewsPaperListModel extends BaseModel {

    private PaperData data;

    public PaperData getData() {
        return data;
    }

    public void setData(PaperData data) {
        this.data = data;
    }

    public class PaperData implements Serializable{
        private int total_number;
        private List<NewspaperModel> rows;

        public int getTotal_number() {
            return total_number;
        }

        public void setTotal_number(int total_number) {
            this.total_number = total_number;
        }

        public List<NewspaperModel> getRows() {
            return rows;
        }

        public void setRows(List<NewspaperModel> rows) {
            this.rows = rows;
        }
    }
}
