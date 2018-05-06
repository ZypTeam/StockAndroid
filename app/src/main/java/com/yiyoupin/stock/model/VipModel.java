package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;

/**
 * @author zhaoyapeng
 * @version create time:18/5/614:58
 * @Email zyp@jusfoun.com
 * @Description ${vip model}
 */
public class VipModel extends BaseModel implements Serializable {
    public VipData data;

    public class VipData extends BaseModel implements Serializable{
        public String end_time;
        public String begin_time;
        public String isvip;
    }
}
