package com.guoziwei.klinelib.chart.my.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.guoziwei.klinelib.chart.my.model.LineModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1911:11
 * @Email zyp@jusfoun.com
 * @Description ${五日数据}
 */
public class FiveDayModel extends BaseModel implements Serializable {


    public List<FiveDayItemModel> data;

    public class FiveDayItemModel extends BaseModel implements Serializable {
        public String name;
        public List<LineModel> dapandata;
    }

}
