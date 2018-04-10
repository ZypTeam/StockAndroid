package com.yiyoupin.stock.model;

import com.yiyoupin.stock.expandablerecycleradapter.model.ExpandableListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class ChartsModel implements ExpandableListItem {
    private boolean expanded=false;
    private String name;
    private String id;
    private List<ChartItemModel> list;
    @Override
    public List<?> getChildItemList() {
        return this.list;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        expanded=isExpanded;
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
}
