package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class SearchListModel extends BaseModel {

    private SearchData data;

    public SearchData getData() {
        return data;
    }

    public void setData(SearchData data) {
        this.data = data;
    }

    public class SearchData implements Serializable{
        private List<SearchModel> rows;
        private int total_number;

        public List<SearchModel> getRows() {
            return rows;
        }

        public void setRows(List<SearchModel> rows) {
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
