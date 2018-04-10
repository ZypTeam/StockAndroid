package com.yiyoupin.stock.model;

import com.jusfoun.baselibrary.base.BaseModel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsModel extends ExpandableGroup {
    private String name;
    private String id;
    private List<ChartItemModel> list;

    public ChartsModel(String title, List items) {
        super(title, items);
    }

    public List<ChartItemModel> getList() {
        return this.list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public List getItems() {
        return list;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
