package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class ReplayListModel extends BaseModel {

    private ReplayData data;

    public ReplayData getData() {
        return data;
    }

    public void setData(ReplayData data) {
        this.data = data;
    }

    public class ReplayData{
        private int total_number;
        private List<ReplayModel> rows;

        public int getTotal_number() {
            return total_number;
        }

        public void setTotal_number(int total_number) {
            this.total_number = total_number;
        }

        public List<ReplayModel> getRows() {
            return rows;
        }

        public void setRows(List<ReplayModel> rows) {
            this.rows = rows;
        }
    }
}
