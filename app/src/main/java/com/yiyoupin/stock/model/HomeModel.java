package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/918:19
 * @Email zyp@jusfoun.com
 * @Description ${首页model}
 */
public class HomeModel extends BaseModel implements Serializable {


    public HomeDataModel data;


    public class HomeDataModel extends BaseModel implements Serializable{
        public List<PlateindexItemModel> plateindex;
        public List<StocktacticsItemModel> stocktactics;
        public List<BuyselectionItemModel> buyselection;
        public List<TechnologyItemModel> technology;

    }

    public class PlateindexItemModel extends BaseModel implements Serializable{
        public String plate_name;
        public String plate_index;
        public String plate_growth;
        public String plate_growth_rate;
    }

    public class StocktacticsItemModel extends BaseModel implements Serializable{
        public String tactics_id;
        public String yield_rate;
        public String tactics_name;
    }

    public class BuyselectionItemModel extends BaseModel implements Serializable{
        public String choiceness_id;
        public String choiceness_name;
        public String yield_rate;
    }

    public class TechnologyItemModel extends BaseModel implements Serializable{
        public String technology_id;
        public String technology_name;
    }

}


