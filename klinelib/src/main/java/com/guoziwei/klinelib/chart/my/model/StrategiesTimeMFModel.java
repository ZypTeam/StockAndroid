package com.guoziwei.klinelib.chart.my.model;

import com.guoziwei.klinelib.chart.FuTuModel;
import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/5/2109:14
 * @Email zyp@jusfoun.com
 * @Description ${分时，主图附图分时model }
 */
public class StrategiesTimeMFModel extends BaseModel implements Serializable{

    public StrategiesTimeMFDataModel data;

    public static class StrategiesTimeMFDataModel extends BaseModel implements Serializable{

        public StrategiesTimeMFMainMainDataModel mainData;
        public StrategiesTimeMFMainFuDataModel incidentalData;
    }


    public static class StrategiesTimeMFMainMainDataModel extends BaseModel implements Serializable{

        public  int type;

        public List<LineModel> data1;

        public FuTuModel.FutuData data2;
    }

    public static class StrategiesTimeMFMainFuDataModel extends BaseModel implements Serializable{

        public  int type;

        public List<KModel> data1;

        public FuTuModel.FutuData data2;
    }
}


