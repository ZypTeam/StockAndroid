package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1214:33
 * @Email zyp@jusfoun.com
 * @Description ${策略详情}
 */
public class StrategiesDetailModel extends BaseModel implements Serializable {

    public StrategiesDetailDataModel data;

    public class StrategiesDetailDataModel extends BaseModel implements Serializable {
        public List<QuotesItemModel> today_stock;
        public List<QuotesItemModel> to_phone_stock;
        public List<QuotesItemModel> history_stock;
        public TacticReviewModel tactic_review;
        public BuyselectionModel buyselection;
        public BuyselectionModel tactic;


    }


    public class TacticReviewModel extends BaseModel implements Serializable {
        public String today_stock;
        public String choiceness_gross_rate;
        public String description;
        public String this_choiceness;
        public String CSI_300_index;

    }

    public class BuyselectionModel extends BaseModel implements Serializable {
        public String choiceness_id;
        public String yield_rate;
        public String choiceness_name;
        public String description;
        public String tactics_name;


    }
}
