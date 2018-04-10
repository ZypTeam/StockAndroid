package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.yiyoupin.stock.expandablerecycleradapter.model.ExpandableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class QuotesModel extends ExpandableGroup {
    private boolean expanded=false;
    private String bill_name;
    private String bill_id;
    private List<QuotesItemModel> stock_list;

    public QuotesModel(String title, List items) {
        super(title, items);
    }

    public List<QuotesItemModel> getStock_list() {
        return stock_list;
    }

    public void setStock_list(List<QuotesItemModel> stock_list) {
        this.stock_list = stock_list;
    }

    public String getName() {
        return bill_name;
    }

    public void setName(String name) {
        this.bill_name = name;
    }

    public String getId() {
        return bill_id;
    }

    public void setId(String id) {
        this.bill_id = id;
    }

    @Override
    public List getItems() {
        return stock_list;
    }

    @Override
    public String getTitle() {
        return bill_name;
    }

    @Override
    public int getItemCount() {
        return stock_list==null?0:stock_list.size();
    }
}
