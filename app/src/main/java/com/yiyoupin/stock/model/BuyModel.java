package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/6/1008:23
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class BuyModel extends BaseModel implements Serializable{


    public BuyDataModel data;
    public class  BuyDataModel extends BaseModel implements Serializable{

        public String wx_code_url;
        public String begin_time;
        public String end_time;
        public String user_name;
        public String price;
        public String alipay_code_url;

    }
}
