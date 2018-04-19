package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/19
 * @describe
 */
public class MsgListModel extends BaseModel {

    private MsgData data;

    public MsgData getData() {
        return data;
    }

    public void setData(MsgData data) {
        this.data = data;
    }

    public class MsgData implements Serializable{
        private List<MsgModel> rows;
        private int total_number;

        public List<MsgModel> getRows() {
            return rows;
        }

        public void setRows(List<MsgModel> rows) {
            this.rows = rows;
        }

        public int getTotal_number() {
            return total_number;
        }

        public void setTotal_number(int total_number) {
            this.total_number = total_number;
        }
    }
}
