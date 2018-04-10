package com.yiyoupin.stock.model;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/4/10
 * @describe
 */

public class ZhengquanModel implements Serializable {


    /**
     * plate_index : 3190.32
     * plate_name : 上证指数
     * plate_growth_rate : 1.66
     * plate_growth : 52.03
     */

    private double plate_index;
    private String plate_name;
    private double plate_growth_rate;
    private double plate_growth;

    public double getPlate_index() {
        return plate_index;
    }

    public void setPlate_index(double plate_index) {
        this.plate_index = plate_index;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public double getPlate_growth_rate() {
        return plate_growth_rate;
    }

    public void setPlate_growth_rate(double plate_growth_rate) {
        this.plate_growth_rate = plate_growth_rate;
    }

    public double getPlate_growth() {
        return plate_growth;
    }

    public void setPlate_growth(double plate_growth) {
        this.plate_growth = plate_growth;
    }
}
