package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.my.DrawingData;

/**
 * @author zhaoyapeng
 * @version create time:18/5/321:30
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public interface DrawingDataProvider extends BarLineScatterCandleBubbleDataProvider {

    DrawingData getDrawingData();
}
