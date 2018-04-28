package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/1821:09
 * @Email zyp@jusfoun.com
 * @Description ${新闻model}
 */
public class NewModel extends BaseModel implements Serializable {

    public NewsDataModel data;

    public class NewsDataModel extends BaseModel implements Serializable {
        public List<NewsItemModel> daily_list;
        public List<NewsItemStopModel> stop_list;
        public List<NewsItemNoticeModel> notice_list;
    }

    public class NewsItemModel extends BaseModel implements Serializable {
        public String daily_id;
        public String daily_date;
        public String describe;
        public String title;
        public String view_count;
        public String url;
    }


    public class NewsItemStopModel extends BaseModel implements Serializable {
        public String stop_time;
        public String stock_name;
        public String url;
    }



    public class NewsItemNoticeModel extends BaseModel implements Serializable {
        public String notice_time;
        public String title;
        public String url;
    }
}
