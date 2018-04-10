package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1016:18
 * @Email zyp@jusfoun.com
 * @Description ${选股策略 更多}
 */
public class StrategiesMoreModel extends BaseModel implements Serializable {

    public StrategiesDataModel data;

    public class  StrategiesDataModel extends BaseModel implements Serializable{
        public String page_index;
        public String page_size;
        public int total_number;
        public List<StrategiesItemModel> rows;

    }

    public class StrategiesItemModel extends BaseModel implements Serializable {
        public String tactics_id;
        public String yield_rate;
        public String tactics_name;
        public String description;


    }
}
