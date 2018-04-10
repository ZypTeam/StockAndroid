package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.jusfoun.baselibrary.base.NoDataModel;

import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class NoticeListModel extends BaseModel {

    private NoticeData data;

    public NoticeData getData() {
        return data;
    }

    public void setData(NoticeData data) {
        this.data = data;
    }

    public class NoticeData{
        private int total_number;
        private List<NoticeModel> rows;

        public int getTotal_number() {
            return total_number;
        }

        public void setTotal_number(int total_number) {
            this.total_number = total_number;
        }

        public List<NoticeModel> getRows() {
            return rows;
        }

        public void setRows(List<NoticeModel> rows) {
            this.rows = rows;
        }
    }
}
