package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.model.MyTacticsModel;
import com.yiyoupin.stock.ui.adapter.ShowCelueAdapter;

import java.util.List;

/**
 * @author zhaoyapeng
 * @version create time:18/4/2810:36
 * @Email zyp@jusfoun.com
 * @Description ${股票详情页面 策略列表popwindow}
 */
public class CeluePopupWindow extends PopupWindow {
    protected RecyclerView recyclerView;
    private Context mContext;
    private ShowCelueAdapter adapter;

    public CeluePopupWindow(Context context) {
        super(context);
        mContext = context;
        initViews();
        initAvtions();
    }

    public CeluePopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
        initAvtions();
    }

    public CeluePopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
        initAvtions();
    }

    private void initViews() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_pop_celue, null);
        setContentView(view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    private void initAvtions() {
        adapter = new ShowCelueAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        setOutsideTouchable(true);
    }



    public void setData(List<MyTacticsModel> list){
        adapter.refreshList(list);
    }
}
