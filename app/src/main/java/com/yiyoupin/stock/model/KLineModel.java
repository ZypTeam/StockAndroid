package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.guoziwei.klinelib.chart.my.model.KModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1915:39
 * @Email zyp@jusfoun.com
 * @Description ${K线model}
 */
public class KLineModel extends BaseModel implements Serializable {
    public List<KModel> data;
}
