package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1716:41
 * @Email zyp@jusfoun.com
 * @Description ${明细model}
 */
public class MingXiModel extends BaseModel implements Serializable {

    public List<MingXiItemModel> list;

    public static class MingXiItemModel extends BaseModel implements Serializable {
        public String name;
        public String price;
        public String count;

    }


}


