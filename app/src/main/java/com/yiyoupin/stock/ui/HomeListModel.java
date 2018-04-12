package com.yiyoupin.stock.ui;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.model.StockModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2215:33
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class HomeListModel extends BaseModel implements Serializable {

    private HomeData data;

    public HomeData getData() {
        return data;
    }

    public void setData(HomeData data) {
        this.data = data;
    }

    public class HomeData extends BaseModel implements Serializable{
        public int total_number;
        private List<StockModel> stock_list;
        public List<StockModel> rows;
        public List<StockModel> getStock_list() {
            return stock_list;
        }
        public void setStock_list(List<StockModel> stock_list) {
            this.stock_list = stock_list;
        }
    }
}
