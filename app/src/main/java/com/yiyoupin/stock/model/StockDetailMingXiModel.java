package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.yiyoupin.stock.ui.view.kline.model.LineModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1714:32
 * @Email zyp@jusfoun.com
 * @Description ${股票详情}
 */
public class StockDetailMingXiModel extends BaseModel implements Serializable {


    public StockDetailDataModel data;

    public class StockDetailDataModel extends BaseModel implements Serializable {

        public List<LineModel> dapandata;
        public StockTopModel stock_detail;
        public List<TradeDetailModel> trade_detail;
        public List<MyTacticsModel>my_tactics;
    }

    public class StockTopModel extends BaseModel implements Serializable {

        public String name;
        public String stock_name;
        public String market_value;
        public String stock_code;
        public String stock_price;
        public String offset_size;
        public String increase;
        public String todayMax;
        public String todayMin;
        public String todayStartPri;
        public String traNumber;
        public String traAmount;
        public String buyOne;
        public String buyOnePri;
        public String buyTwo;
        public String buyTwoPri;
        public String buyThree;
        public String buyThreePri;
        public String buyFour;
        public String buyFourPri;
        public String buyFive;
        public String buyFivePri;
        public String sellOne;
        public String sellOnePri;
        public String sellTwo;
        public String sellTwoPri;
        public String sellThree;
        public String sellThreePri;
        public String sellFour;
        public String sellFourPri;
        public String sellFive;
        public String sellFivePri;
    }

    public class TradeDetailModel extends BaseModel implements Serializable {
        public String trade_time;
        public String stock_price;
        public String offset_size;
        public String trade_volumn;
    }
}
