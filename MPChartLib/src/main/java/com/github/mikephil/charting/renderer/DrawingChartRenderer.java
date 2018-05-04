
package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.*;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.my.DrawingData;
import com.github.mikephil.charting.renderer.LineScatterCandleRadarRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;


/**
 * @author zhaoyapeng
 * @version create time:18/3/2815:49
 * @Email zyp@jusfoun.com
 * @Description ${附图 renderer}
 */
public class DrawingChartRenderer extends LineScatterCandleRadarRenderer {

    public com.github.mikephil.charting.interfaces.dataprovider.DrawingDataProvider mChart;

    private float[] mShadowBuffers = new float[8];
    private float[] mBodyBuffers = new float[4];
    private float[] mRangeBuffers = new float[4];
    private float[] mOpenBuffers = new float[4];
    private float[] mCloseBuffers = new float[4];


    protected Paint mRenderPaint1;

    public DrawingChartRenderer(com.github.mikephil.charting.interfaces.dataprovider.DrawingDataProvider chart, ChartAnimator animator,
                                ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        mChart = chart;



        mRenderPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaint1.setStyle(Paint.Style.STROKE);
        mRenderPaint1.setColor(Color.BLACK);
        mRenderPaint1.setStrokeWidth(1);

    }

    @Override
    public void initBuffers() {

    }

    @Override
    public void drawData(Canvas c) {

        DrawingData candleData = mChart.getDrawingData();

        for (ICandleDataSet set : candleData.getDataSets()) {

            if (set.isVisible())
                drawDataSet(c, set);
        }
    }

    @SuppressWarnings("ResourceAsColor")
    protected void drawDataSet(Canvas c, ICandleDataSet dataSet) {



        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        float phaseY = mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();
        boolean showCandleBar = dataSet.getShowCandleBar();

        mXBounds.set(mChart, dataSet);

        mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());

        Log.e("tag","drawDataSet1"+mXBounds.min+" "+mXBounds.range);
        // draw the body
        for (int j = mXBounds.min; j <= mXBounds.range + mXBounds.min; j++) {
            Log.e("tag","drawDataSet2");
            // get the entry
            CandleEntry e = dataSet.getEntryForIndex(j);

            if (e == null)
                continue;

            for(int z = 0;z<e.width.size();z++) {
                final float xPos = e.getX();

                final float open = e.getOpen();
                final float close = e.getClose();
                final float high = e.getHigh();
                final float low = e.getLow();

                if (showCandleBar) {
                    // calculate the shadow
                    Log.e("tag", "drawDataSet3");
                    mShadowBuffers[0] = xPos;
                    mShadowBuffers[2] = xPos;
                    mShadowBuffers[4] = xPos;
                    mShadowBuffers[6] = xPos;

                    if (open > close) {
                        mShadowBuffers[1] = high * phaseY;
                        mShadowBuffers[3] = open * phaseY;
                        mShadowBuffers[5] = low * phaseY;
                        mShadowBuffers[7] = close * phaseY;
                    } else if (open < close) {
                        mShadowBuffers[1] = high * phaseY;
                        mShadowBuffers[3] = close * phaseY;
                        mShadowBuffers[5] = low * phaseY;
                        mShadowBuffers[7] = open * phaseY;
                    } else {
                        mShadowBuffers[1] = high * phaseY;
                        mShadowBuffers[3] = open * phaseY;
                        mShadowBuffers[5] = low * phaseY;
                        mShadowBuffers[7] = mShadowBuffers[3];
                    }

                    trans.pointValuesToPixel(mShadowBuffers);

                    // draw the shadows

                    if (dataSet.getShadowColorSameAsCandle()) {

                        if (open > close)
                            mRenderPaint.setColor(
                                    dataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE ?
                                            dataSet.getColor(j) :
                                            dataSet.getDecreasingColor()
                            );

                        else if (open < close)
                            mRenderPaint.setColor(
                                    dataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE ?
                                            dataSet.getColor(j) :
                                            dataSet.getIncreasingColor()
                            );

                        else
                            mRenderPaint.setColor(
                                    dataSet.getNeutralColor() == ColorTemplate.COLOR_NONE ?
                                            dataSet.getColor(j) :
                                            dataSet.getNeutralColor()
                            );

                    } else {
                        mRenderPaint.setColor(
                                dataSet.getShadowColor() == ColorTemplate.COLOR_NONE ?
                                        dataSet.getColor(j) :
                                        dataSet.getShadowColor()
                        );
                    }

                    mRenderPaint.setStyle(Paint.Style.FILL);

//                c.drawLines(mShadowBuffers, mRenderPaint);

                    // calculate the body

                    barSpace = 0f;
                    if(z==3){
                        mBodyBuffers[0] = xPos - 0.1f+ barSpace;
                        mBodyBuffers[2] = (xPos + 0.1f)-barSpace;
                        mRenderPaint.setColor(Color.parseColor("#FFB6C1"));

                    }else if(z==2){
                        mBodyBuffers[0] = xPos - 0.2f+ barSpace;
                        mBodyBuffers[2] = (xPos + 0.2f)-barSpace;
                        mRenderPaint.setColor(Color.parseColor("#DB7093"));
                    }else if(z==1){
                        mBodyBuffers[0] = xPos - 0.3f+ barSpace;
                        mBodyBuffers[2] = (xPos + 0.3f)-barSpace;
                        mRenderPaint.setColor(Color.parseColor("#8B008B"));
                    }else if(z==0){
                        mBodyBuffers[0] = xPos - 0.5f+ barSpace;
                        mBodyBuffers[2] = (xPos + 0.5f)- barSpace;
                        mRenderPaint.setColor(Color.parseColor("#0000FF"));
                    }
                    Log.e("tag","color="+Color.parseColor(e.color.get(z))+" "+e.color.get(z));
                    mRenderPaint.setColor(Color.parseColor(e.color.get(z)));

                    mBodyBuffers[1] = close * phaseY;
                    mBodyBuffers[3] = open * phaseY;

                    trans.pointValuesToPixel(mBodyBuffers);

                    // draw body differently for increasing and decreasing entry
                    if (open > close) { // decreasing

//                        if (dataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE) {
//                            mRenderPaint.setColor(dataSet.getColor(j));
//                        } else {
//                            mRenderPaint.setColor(dataSet.getDecreasingColor());
//                        }

//                        mRenderPaint.setStyle(dataSet.getDecreasingPaintStyle());

                    c.drawRect(
                            mBodyBuffers[0], mBodyBuffers[3],
                            mBodyBuffers[2], mBodyBuffers[1],
                            mRenderPaint);
//
//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[3],
//                                mBodyBuffers[2], mBodyBuffers[1], 20f, 20f, mRenderPaint);
//
//
//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[3],
//                                mBodyBuffers[2], mBodyBuffers[1], 20f, 20f, mRenderPaint1);
                    } else if (open < close) {

                        if (dataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE) {
                            mRenderPaint.setColor(dataSet.getColor(j));
                        } else {
                            mRenderPaint.setColor(dataSet.getIncreasingColor());
                        }

                        mRenderPaint.setStyle(dataSet.getIncreasingPaintStyle());

                    c.drawRect(
                            mBodyBuffers[0], mBodyBuffers[1],
                            mBodyBuffers[2], mBodyBuffers[3],
                            mRenderPaint);

//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[1],
//                                mBodyBuffers[2], mBodyBuffers[3], 20f, 20f, mRenderPaint);
//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[1],
//                                mBodyBuffers[2], mBodyBuffers[3], 20f, 20f, mRenderPaint1);
                    } else { // equal values

                        if (dataSet.getNeutralColor() == ColorTemplate.COLOR_NONE) {
                            mRenderPaint.setColor(dataSet.getColor(j));
                        } else {
                            mRenderPaint.setColor(dataSet.getNeutralColor());
                        }

                    c.drawLine(
                            mBodyBuffers[0], mBodyBuffers[1],
                            mBodyBuffers[2], mBodyBuffers[3],
                            mRenderPaint);
//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[1],
//                                mBodyBuffers[2], mBodyBuffers[3], 20f, 20f, mRenderPaint);
//                        c.drawRoundRect(mBodyBuffers[0], mBodyBuffers[1],
//                                mBodyBuffers[2], mBodyBuffers[3], 20f, 20f, mRenderPaint1);
                    }
                } else {
                    Log.e("tag", "drawDataSet4");
                    mRangeBuffers[0] = xPos;
                    mRangeBuffers[1] = high * phaseY;
                    mRangeBuffers[2] = xPos;
                    mRangeBuffers[3] = low * phaseY;

                    mOpenBuffers[0] = xPos - 0.5f + barSpace;
                    mOpenBuffers[1] = open * phaseY;
                    mOpenBuffers[2] = xPos;
                    mOpenBuffers[3] = open * phaseY;

                    mCloseBuffers[0] = xPos + 0.5f - barSpace;
                    mCloseBuffers[1] = close * phaseY;
                    mCloseBuffers[2] = xPos;
                    mCloseBuffers[3] = close * phaseY;

                    trans.pointValuesToPixel(mRangeBuffers);
                    trans.pointValuesToPixel(mOpenBuffers);
                    trans.pointValuesToPixel(mCloseBuffers);

                    // draw the ranges
                    int barColor;

                    if (open > close)
                        barColor = dataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE
                                ? dataSet.getColor(j)
                                : dataSet.getDecreasingColor();
                    else if (open < close)
                        barColor = dataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE
                                ? dataSet.getColor(j)
                                : dataSet.getIncreasingColor();
                    else
                        barColor = dataSet.getNeutralColor() == ColorTemplate.COLOR_NONE
                                ? dataSet.getColor(j)
                                : dataSet.getNeutralColor();

                    mRenderPaint.setColor(barColor);
                    c.drawLine(
                            mRangeBuffers[0], mRangeBuffers[1],
                            mRangeBuffers[2], mRangeBuffers[3],
                            mRenderPaint);
                    c.drawLine(
                            mOpenBuffers[0], mOpenBuffers[1],
                            mOpenBuffers[2], mOpenBuffers[3],
                            mRenderPaint);
                    c.drawLine(
                            mCloseBuffers[0], mCloseBuffers[1],
                            mCloseBuffers[2], mCloseBuffers[3],
                            mRenderPaint);
                }
            }
        }
    }

    /*@Override
    public void drawValues(Canvas c) {

        // if values are drawn
        if (isDrawingValuesAllowed(mChart)) {

            List<ICandleDataSet> dataSets = mChart.getCandleData().getDataSets();

            for (int i = 0; i < dataSets.size(); i++) {

                ICandleDataSet dataSet = dataSets.get(i);

                if (!shouldDrawValues(dataSet))
                    continue;

                // apply the text-styling defined by the DataSet
                applyValueTextStyle(dataSet);

                Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

                mXBounds.set(mChart, dataSet);

                float[] positions = trans.generateTransformedValuesCandle(
                        dataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), mXBounds.min, mXBounds.max);

                float yOffset = Utils.convertDpToPixel(5f);

                MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
                iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
                iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);

                for (int j = 0; j < positions.length; j += 2) {

                    float x = positions[j];
                    float y = positions[j + 1];

                    if (!mViewPortHandler.isInBoundsRight(x))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
                        continue;

                    CandleEntry entry = dataSet.getEntryForIndex(j / 2 + mXBounds.min);

                    if (dataSet.isDrawValuesEnabled()) {
                        drawValue(c,
                                dataSet.getValueFormatter(),
                                entry.getHigh(),
                                entry,
                                i,
                                x,
                                y - yOffset,
                                dataSet
                                        .getValueTextColor(j / 2));
                    }

                    if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {

                        Drawable icon = entry.getIcon();

                        Utils.drawImage(
                                c,
                                icon,
                                (int)(x + iconsOffset.x),
                                (int)(y + iconsOffset.y),
                                icon.getIntrinsicWidth(),
                                icon.getIntrinsicHeight());
                    }
                }

                MPPointF.recycleInstance(iconsOffset);
            }


        }


    }*/

    @Override
    public void drawValues(Canvas c) {

        List<ICandleDataSet> dataSets = mChart.getDrawingData().getDataSets();

        for (int i = 0; i < dataSets.size(); i++) {

            ICandleDataSet dataSet = dataSets.get(i);

            if (!dataSet.isDrawValuesEnabled() || dataSet.getEntryCount() == 0)
                continue;

            // apply the text-styling defined by the DataSet
            applyValueTextStyle(dataSet);

            Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

            int minx = (int) Math.max(dataSet.getXMin(), 0);
            int maxx = (int) Math.min(dataSet.getXMax(), dataSet.getEntryCount() - 1);

            float[] positions = trans.generateTransformedValuesCandle(
                    dataSet, mAnimator.getPhaseX(), mAnimator.getPhaseY(), minx, maxx);


            //计算最大值和最小值
            float maxValue = 0, minValue = 0;
            int maxIndex = 0, minIndex = 0;
            CandleEntry maxEntry = null;
            boolean firstInit = true;
            for (int j = 0; j < positions.length; j += 2) {

                float x = positions[j];
                float y = positions[j + 1];

                if (!mViewPortHandler.isInBoundsRight(x))
                    break;

                if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
                    continue;

                CandleEntry entry = dataSet.getEntryForIndex(j / 2 + minx);

                if (firstInit) {
                    maxValue = entry.getHigh();
                    minValue = entry.getLow();
                    firstInit = false;
                    maxEntry = entry;
                } else {
                    if (entry.getHigh() > maxValue) {
                        maxValue = entry.getHigh();
                        maxIndex = j;
                        maxEntry = entry;
                    }

                    if (entry.getLow() < minValue) {
                        minValue = entry.getLow();
                        minIndex = j;
                    }

                }
            }

            //绘制最大值和最小值
            float x = positions[minIndex];
            if (maxIndex > minIndex) {
                //画右边
                String highString = "← " + Float.toString(minValue);

                //计算显示位置
                //计算文本宽度
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[1] = minValue;
                trans.pointValuesToPixel(tPosition);
                mValuePaint.setColor(dataSet.getValueTextColor(minIndex / 2));
                c.drawText(highString, x + highStringWidth / 2, tPosition[1], mValuePaint);
            } else {
                //画左边
                String highString = Float.toString(minValue) + " →";

                //计算显示位置
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);
                float[] tPosition = new float[2];
                tPosition[1] = minValue;
                trans.pointValuesToPixel(tPosition);
                mValuePaint.setColor(dataSet.getValueTextColor(minIndex / 2));
                c.drawText(highString, x - highStringWidth / 2, tPosition[1], mValuePaint);
            }

            if (maxIndex > minIndex) {
                //画左边
                String highString = Float.toString(maxValue) + " →";

                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[0] = maxEntry == null ? 0f : maxEntry.getX();
                tPosition[1] = maxEntry == null ? 0f : maxEntry.getHigh();
                trans.pointValuesToPixel(tPosition);

                mValuePaint.setColor(dataSet.getValueTextColor(maxIndex / 2));
                c.drawText(highString, tPosition[0] - highStringWidth / 2, tPosition[1], mValuePaint);
            } else {
                //画右边
                String highString = "← " + Float.toString(maxValue);

                //计算显示位置
                int highStringWidth = Utils.calcTextWidth(mValuePaint, highString);

                float[] tPosition = new float[2];
                tPosition[0] = maxEntry == null ? 0f : maxEntry.getX();
                tPosition[1] = maxEntry == null ? 0f : maxEntry.getHigh();
                trans.pointValuesToPixel(tPosition);

                mValuePaint.setColor(dataSet.getValueTextColor(maxIndex / 2));
                c.drawText(highString, tPosition[0] + highStringWidth / 2, tPosition[1], mValuePaint);

            }

        }
//        }
    }


    @Override
    public void drawExtras(Canvas c) {
    }

    @Override
    public void drawHighlighted(Canvas c, Highlight[] indices) {

        DrawingData candleData = mChart.getDrawingData();

        for (Highlight high : indices) {

            ICandleDataSet set = candleData.getDataSetByIndex(high.getDataSetIndex());

            if (set == null || !set.isHighlightEnabled())
                continue;

            CandleEntry e = set.getEntryForXValue(high.getX(), high.getY());

            if (!isInBoundsX(e, set))
                continue;

            float lowValue = e.getLow() * mAnimator.getPhaseY();
            float highValue = e.getHigh() * mAnimator.getPhaseY();
            float y = (lowValue + highValue) / 2f;

            MPPointD pix = mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), y);

            high.setDraw((float) pix.x, (float) pix.y);

            // draw the lines
            drawHighlightLines(c, (float) pix.x, (float) pix.y, set);
        }
    }
}