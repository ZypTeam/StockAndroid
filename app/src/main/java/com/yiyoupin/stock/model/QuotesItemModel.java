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

    private String stock_name;
    private String stock_price;
    private String stock_code;
    private String offset_size;

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOffset_size() {
        return offset_size;
    }

    public void setOffset_size(String offset_size) {
        this.offset_size = offset_size;
    }

    protected QuotesItemModel(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
