package com.yiyoupin.stock.model;

import com.zaihuishou.expandablerecycleradapter.model.ExpandableListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class QuotesModel implements ExpandableListItem {
    private boolean expanded=false;
    @Override
    public List<?> getChildItemList() {
        List<QuotesItemModel> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new QuotesItemModel());
        }
        return list;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        expanded=isExpanded;
    }
}
