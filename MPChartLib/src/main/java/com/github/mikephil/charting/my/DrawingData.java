package com.github.mikephil.charting.my;

import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;

import java.util.List;
/**
 *  自定义附图data
 * */

public class DrawingData extends BarLineScatterCandleBubbleData<ICandleDataSet> {

    public DrawingData() {
        super();
    }

    public DrawingData(List<ICandleDataSet> dataSets) {
        super(dataSets);
    }

    public DrawingData(ICandleDataSet... dataSets) {
        super(dataSets);
    }
}
