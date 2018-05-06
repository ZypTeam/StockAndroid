package com.yiyoupin.stock.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class QuotesItemModel extends BaseModel implements Parcelable,Serializable {

    /**
     * average : 16.87
     * amplitude : 7.84
     * stock_name : 永吉股份
     * trade_amount : 14886.24
     * trade_volumn : 850.41
     * stock_code : 603058
     * close : 17.88
     * offset_size : 10.03
     * stock_id : 1024
     * open : 16.87
     * change_rate : 3.73
     */

    private double average;
    private double amplitude;
    private String stock_name;
    private double trade_amount;
    private double trade_volumn;
    private String stock_code;
    private double close;
    private double offset_size;
    private String stock_id;
    private double open;
    private double change_rate;
    private String stock_price;

    protected QuotesItemModel(Parcel in) {
        average = in.readDouble();
        amplitude = in.readDouble();
        stock_name = in.readString();
        trade_amount = in.readDouble();
        trade_volumn = in.readDouble();
        stock_code = in.readString();
        close = in.readDouble();
        offset_size = in.readDouble();
        stock_id = in.readString();
        open = in.readDouble();
        change_rate = in.readDouble();
    }

    public static final Creator<QuotesItemModel> CREATOR = new Creator<QuotesItemModel>() {
        @Override
        public QuotesItemModel createFromParcel(Parcel in) {
            return new QuotesItemModel(in);
        }

        @Override
        public QuotesItemModel[] newArray(int size) {
            return new QuotesItemModel[size];
        }
    };

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public double getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(double trade_amount) {
        this.trade_amount = trade_amount;
    }

    public double getTrade_volumn() {
        return trade_volumn;
    }

    public void setTrade_volumn(double trade_volumn) {
        this.trade_volumn = trade_volumn;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getOffset_size() {
        return offset_size;
    }

    public void setOffset_size(double offset_size) {
        this.offset_size = offset_size;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getChange_rate() {
        return change_rate;
    }

    public void setChange_rate(double change_rate) {
        this.change_rate = change_rate;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(average);
        dest.writeDouble(amplitude);
        dest.writeString(stock_name);
        dest.writeDouble(trade_amount);
        dest.writeDouble(trade_volumn);
        dest.writeString(stock_code);
        dest.writeDouble(close);
        dest.writeDouble(offset_size);
        dest.writeString(stock_id);
        dest.writeDouble(open);
        dest.writeDouble(change_rate);
    }
}
