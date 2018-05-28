package com.guoziwei.klinelib.util;

import android.util.Log;

import com.guoziwei.klinelib.chart.FuTuModel;
import com.guoziwei.klinelib.chart.my.Util;
import com.guoziwei.klinelib.chart.my.model.FuAllModel;
import com.guoziwei.klinelib.model.HisData;
import com.guoziwei.klinelib.model.KDJ;
import com.guoziwei.klinelib.model.MACD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by dell on 2017/11/9.
 */

public class DataUtils {


    /**
     * calculate average price and ma data
     */
    public static List<HisData> calculateHisData(List<HisData> list, HisData lastData) {


        List<Double> ma5List = calculateMA(5, list);
        List<Double> ma10List = calculateMA(10, list);
        List<Double> ma20List = calculateMA(20, list);
        List<Double> ma30List = calculateMA(30, list);

        MACD macd = new MACD(list);
        List<Double> bar = macd.getMACD();
        List<Double> dea = macd.getDEA();
        List<Double> dif = macd.getDIF();


        KDJ kdj = new KDJ(list);
        ArrayList<Double> d = kdj.getD();
        ArrayList<Double> k = kdj.getK();
        ArrayList<Double> j = kdj.getJ();

        float amountVol = 0;
        if (lastData != null) {
            amountVol = lastData.getAmountVol();
        }
        for (int i = 0; i < list.size(); i++) {
            HisData hisData = list.get(i);

            hisData.setMa5(ma5List.get(i));
            hisData.setMa10(ma10List.get(i));
            hisData.setMa20(ma20List.get(i));
            hisData.setMa30(ma30List.get(i));

            hisData.setMacd(bar.get(i));
            hisData.setDea(dea.get(i));
            hisData.setDif(dif.get(i));

            hisData.setD(d.get(i));
            hisData.setK(k.get(i));
            hisData.setJ(j.get(i));

            amountVol += hisData.getVol();
            hisData.setAmountVol(amountVol);
            if (i > 0) {
                double total = hisData.getVol() * hisData.getClose() + list.get(i - 1).getTotal();
                hisData.setTotal(total);
                double avePrice = total / amountVol;
//                hisData.setAvePrice(avePrice);
            } else if (lastData != null) {
                double total = hisData.getVol() * hisData.getClose() + lastData.getTotal();
                hisData.setTotal(total);
                double avePrice = total / amountVol;
//                hisData.setAvePrice(avePrice);
            } else {
                hisData.setAmountVol(hisData.getVol());
//                hisData.setAvePrice(hisData.getClose());
                hisData.setTotal(hisData.getAmountVol() * hisData.getAvePrice());
            }


        }
        return list;
    }

    public static List<HisData> calculateHisData(List<HisData> list) {
        return calculateHisData(list, null);
    }

    /**
     * according to the history data list, calculate a new data
     */
    public static HisData calculateHisData(HisData newData, List<HisData> hisDatas) {

        HisData lastData = hisDatas.get(hisDatas.size() - 1);
        float amountVol = lastData.getAmountVol();

        newData.setMa5(calculateLastMA(5, hisDatas));
        newData.setMa10(calculateLastMA(10, hisDatas));
        newData.setMa20(calculateLastMA(20, hisDatas));
        newData.setMa30(calculateLastMA(30, hisDatas));

        amountVol += newData.getVol();
        newData.setAmountVol(amountVol);

        double total = newData.getVol() * newData.getClose() + lastData.getTotal();
        newData.setTotal(total);
        double avePrice = total / amountVol;
        newData.setAvePrice(avePrice);

        MACD macd = new MACD(hisDatas);
        List<Double> bar = macd.getMACD();
        newData.setMacd(bar.get(bar.size() - 1));
        List<Double> dea = macd.getDEA();
        newData.setDea(dea.get(dea.size() - 1));
        List<Double> dif = macd.getDIF();
        newData.setDif(dif.get(dif.size() - 1));
        KDJ kdj = new KDJ(hisDatas);
        ArrayList<Double> d = kdj.getD();
        newData.setD(d.get(d.size() - 1));
        ArrayList<Double> k = kdj.getK();
        newData.setK(k.get(k.size() - 1));
        ArrayList<Double> j = kdj.getJ();
        newData.setJ(j.get(j.size() - 1));

        return newData;
    }

    /**
     * calculate MA value, return a double list
     *
     * @param dayCount for example: 5, 10, 20, 30
     */
    public static List<Double> calculateMA(int dayCount, List<HisData> data) {
        dayCount--;
        List<Double> result = new ArrayList<>(data.size());
        for (int i = 0, len = data.size(); i < len; i++) {
            if (i < dayCount) {
                result.add(Double.NaN);
                continue;
            }
            double sum = 0;
            for (int j = 0; j < dayCount; j++) {
                sum += data.get(i - j).getOpen();
            }
            result.add(+(sum / dayCount));
        }
        return result;
    }

    /**
     * calculate last MA value, return a double value
     */
    public static double calculateLastMA(int dayCount, List<HisData> data) {
        dayCount--;
        double result = Double.NaN;
        for (int i = 0, len = data.size(); i < len; i++) {
            if (i < dayCount) {
                result = Double.NaN;
                continue;
            }
            double sum = 0;
            for (int j = 0; j < dayCount; j++) {
                sum += data.get(i - j).getOpen();
            }
            result = (+(sum / dayCount));
        }
        return result;
    }


    public static FuTuModel detailData(FuTuModel model) {

        if(model==null){
            return model;
        }
        if(model.data!=null){
            if(model.data.stickline_bb==null){
                model.data.stickline_bb = model.new LineData();
            }
            if(model.data.stickline_bb.line_data==null){
                model.data.stickline_bb.line_data = new ArrayList<>();
            }

            if(model.data.stickline_ema==null){
                model.data.stickline_ema = model.new LineData();
            }
            if(model.data.stickline_ema.line_data==null){
                model.data.stickline_ema.line_data = new ArrayList<>();
            }
        }
        List<FuTuModel.LineItemData> mListbb = model.data.stickline_bb.line_data;
        List<FuTuModel.LineItemData> mListema = model.data.stickline_ema.line_data;

        List<FuTuModel.LineItemData> minList = new ArrayList<>();
        Map<String, FuTuModel.LineItemData> minMap = new HashMap<>();
        if (mListbb != null && mListema != null) {
            if (mListbb.size() > mListema.size()) {
                for (int i = 0; i < mListema.size(); i++) {
                    minMap.put(mListema.get(i).stickline_date, mListema.get(i));
                }
                for (int i = 0; i < mListbb.size(); i++) {
                    if (minMap.containsKey(mListbb.get(i).stickline_date)) {
                        minList.add(minMap.get(mListbb.get(i).stickline_date));
                    } else {
                        FuTuModel.LineItemData data = new FuTuModel.LineItemData();
                        data.high = -1;
                        data.low = -1;
                        data.stickline_date = mListbb.get(i).stickline_date;
                        minList.add(data);
                    }
                }
                model.data.stickline_ema.line_data = minList;

            } else if(mListema.size() > mListbb.size()){

                for (int i = 0; i < mListbb.size(); i++) {
                    minMap.put(mListbb.get(i).stickline_date, mListbb.get(i));
                }
                for (int i = 0; i < mListema.size(); i++) {
                    if (minMap.containsKey(mListema.get(i).stickline_date)) {
                        minList.add(minMap.get(mListema.get(i).stickline_date));
                    } else {
                        FuTuModel.LineItemData data = new FuTuModel.LineItemData();
                        data.high = -1;
                        data.low = -1;
                        data.stickline_date = mListema.get(i).stickline_date;
                        minList.add(data);
                    }
                }
                model.data.stickline_bb.line_data = minList;
            }


        }
        List<List<HisData>> lists = new ArrayList<>();

        if (model.data != null && model.data.stickline_bb != null && model.data.stickline_bb.line_data != null) {
            List<HisData> hisData = new ArrayList<>();
            for (int i = 0; i < model.data.stickline_bb.line_data.size(); i++) {
                FuTuModel.LineItemData lineData = model.data.stickline_bb.line_data.get(i);

                HisData d = new HisData();
                d.setOpen(lineData.high);
                d.setClose(lineData.low);
                d.width = lineData.width;
                d.color = lineData.color;
                d.groupId = i;

                d.setDate(getStringToDate(lineData.stickline_date));
                hisData.add(0, d);
            }

            lists.add(hisData);
        }

        if (model.data != null && model.data.stickline_ema != null && model.data.stickline_ema.line_data != null) {
            List<HisData> hisData1 = new ArrayList<>();
            for (int i = 0; i < model.data.stickline_ema.line_data.size(); i++) {
                FuTuModel.LineItemData lineData = model.data.stickline_ema.line_data.get(i);

                HisData d = new HisData();
                d.setOpen(lineData.high);
                d.setClose(lineData.low);
                d.width = lineData.width;
                d.color = lineData.color;
                d.groupId = i;
                d.setDate(getStringToDate(lineData.stickline_date));
                hisData1.add(0, d);
            }
            lists.add(hisData1);
        }
        model.lists = lists;
        return model;
    }
    private static SimpleDateFormat sFormat5 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    public  static long getStringToDate(String dateString) {
        Date date = new Date();
        try{
            date = sFormat5.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return date.getTime();
    }


    public static List<List<HisData>> detailFuData(FuAllModel model) {

        if(model==null||model.column==null){
            return null;
        }
        List<List<HisData>> lists = new ArrayList<>();

        for(int i=0;i<model.column.size();i++){
            List<HisData> hisData = new ArrayList<>();

            if(model.column.get(i)!=null) {
                for (int j = 0; j < model.column.get(i).line_data.size(); j++) {
                    FuTuModel.LineItemData lineData =  model.column.get(i).line_data.get(j);

                    HisData d = new HisData();
                    d.setOpen(lineData.high);
                    d.setClose(lineData.low);
                    d.width = lineData.width;
                    d.color = lineData.color;
                    d.groupId = i;

                    d.setDate(getStringToDate(lineData.stickline_date));
                    hisData.add(0, d);
                }
                lists.add(hisData);
            }
        }
        return lists;
    }

}
