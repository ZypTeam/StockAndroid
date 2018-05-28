package com.guoziwei.klinelib.chart.my.model;

import com.guoziwei.klinelib.chart.FuTuModel;
import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/5/2720:11
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class FuAllModel extends BaseModel implements Serializable {

    public List<FuTuModel.TrendLineListmData> line;
    public List<FuTuModel.TrendLineListmData> column;

    public List<LineModel> time_basic;

    public List<FiveDayModel.FiveDayItemModel> five_basic;

}


