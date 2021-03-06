package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1016:18
 * @Email zyp@jusfoun.com
 * @Description ${技术形态 更多}
 */
public class TechnologyMoreModel extends BaseModel implements Serializable {

     public StrategiesDataModel data;

    public class  StrategiesDataModel extends BaseModel implements Serializable{
        public String page_index;
        public String page_size;
        public int total_number;
        public List<HomeModel.TechnologyItemModel> rows;
    }

}
