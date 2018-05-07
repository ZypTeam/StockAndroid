package com.guoziwei.klinelib.chart;


import com.guoziwei.klinelib.model.HisData;
import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/5/321:59
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class FuTuModel extends BaseModel implements Serializable {

    public FutuData data;
    public List<List<HisData>> lists;

    public class FutuData implements Serializable {
        public LineData stickline_bb;
        public LineData stickline_ema;
        public TrendLineListmData trendline;
        public TrendLineListmData longtrendline;
    }

    public class LineData implements Serializable {
        public List<LineItemData> line_data;
    }

    public class LineItemData implements Serializable {

        public String stickline_date;
        public double high;
        public double low;
        public int mWidth;
        public String mColor;

        public List<Integer> width;
        public List<String> color;
    }


    public class TrendLineListmData implements Serializable {
        public List<TrendLineItemData> line_data;
    }


    public class TrendLineItemData implements Serializable {

        public String trend_date;
        public double trend_data;
    }


}
