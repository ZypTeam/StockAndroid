package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class PayListModel extends BaseModel{

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable{
        private List<PayModel> consume_records;

        public List<PayModel> getConsume_records() {
            return consume_records;
        }

        public void setConsume_records(List<PayModel> consume_records) {
            this.consume_records = consume_records;
        }
    }
}
