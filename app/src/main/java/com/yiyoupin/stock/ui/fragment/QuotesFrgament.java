package com.yiyoupin.stock.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.comment.Constant;
import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.QuotesItemModel;
import com.yiyoupin.stock.model.QuotesListModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.model.ZhengquanModel;
import com.yiyoupin.stock.ui.adapter.ChartsAdapter;
import com.yiyoupin.stock.ui.adapter.QuotesAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.view.BackTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${行情}
 */
public class QuotesFrgament extends BaseStockFragment {

    protected BackTitleView titleView;
    protected TextView shangzheng;
    protected TextView shenzheng;
    protected TextView chuangye;
    protected RecyclerView list;

    private int page = 0;

    private QuotesAdapter adapter;
    private List<QuotesModel> models=new ArrayList<>();

    public static QuotesFrgament getInstance() {
        QuotesFrgament fragment = new QuotesFrgament();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_quotes;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView(View rootView) {
        titleView = (BackTitleView) rootView.findViewById(R.id.title_view);
        shangzheng = (TextView) rootView.findViewById(R.id.shangzheng);
        shenzheng = (TextView) rootView.findViewById(R.id.shenzheng);
        chuangye = (TextView) rootView.findViewById(R.id.chuangye);
        list = (RecyclerView) rootView.findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void initAction() {
        shangzheng.setText(getZhishu("上证指数\n","3422.02\n","+52.53% +12.3%"));
        shenzheng.setText(getZhishu("深证指数\n","3422.02\n","+52.53% +12.3%"));
        chuangye.setText(getZhishu("创业板\n","3422.02\n","+52.53% +12.3%"));

        titleView.setTitle("行情");
        titleView.setLeftGone();
    }

    @Override
    protected void refreshData() {
        refresh(true,true);
    }

    private void refresh(boolean showLoading, boolean refresh) {

        if (showLoading){
            showLoadDialog();
        }
        HashMap<String, String> params = new HashMap();
        params.put(Constant.PAGE_PARAMS, Constant.PAGE_SIZE);
        params.put(Constant.PAGE_INDEX, refresh ? "1" : (page + 1) + "");
        addNetwork(Api.getInstance().getService(ApiService.class).quotesList(params)
                , new Action1<QuotesListModel>() {
                    @Override
                    public void call(QuotesListModel model) {
                        hideLoadDialog();
                        if (model.getCode() == 0) {
                            if (refresh){
                                refreshList(model.getData().getTopcharts());
                            }else {
                                addList(model.getData().getTopcharts());
                            }
                            adapter = new QuotesAdapter(mContext,models);
                            list.setAdapter(adapter);
                            if (refresh){
                                page=0;
                            }else {
                                page+=1;
                            }

                            updateZhishu(model.getData().getPlateindex());


                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadDialog();
                    }
                });

    }

    private void updateZhishu(List<ZhengquanModel> list){
        if (list==null||list.size()==0){
            return;
        }

        if (list.size()==1){
            shangzheng.setText(getZhishu(list.get(0).getPlate_name(),list.get(0).getPlate_index()+""
            ,list.get(0).getPlate_growth()+" "+list.get(0).getPlate_growth_rate()));
            return;
        }

        if (list.size()==2){
            shangzheng.setText(getZhishu(list.get(0).getPlate_name(),list.get(0).getPlate_index()+""
                    ,list.get(0).getPlate_growth()+" "+list.get(0).getPlate_growth_rate()));
            shenzheng.setText(getZhishu(list.get(1).getPlate_name(),list.get(1).getPlate_index()+""
                    ,list.get(1).getPlate_growth()+" "+list.get(1).getPlate_growth_rate()));
            return;
        }

        if (list.size()==3){
            shangzheng.setText(getZhishu(list.get(0).getPlate_name(),list.get(0).getPlate_index()+""
                    ,list.get(0).getPlate_growth()+" "+list.get(0).getPlate_growth_rate()));
            shenzheng.setText(getZhishu(list.get(1).getPlate_name(),list.get(1).getPlate_index()+""
                    ,list.get(1).getPlate_growth()+" "+list.get(1).getPlate_growth_rate()));
            chuangye.setText(getZhishu(list.get(2).getPlate_name(),list.get(2).getPlate_index()+""
                    ,list.get(2).getPlate_growth()+" "+list.get(2).getPlate_growth_rate()));
        }
    }

    private SpannableStringBuilder getZhishu(String name,String num,String pre){
        if (!name.endsWith("\n")){
            name+="\n";
        }
        if (!num.endsWith("\n")){
            num+="\n";
        }
        int len1=name.length();
        int len2 =name.length()+num.length();
        int len3=name.length()+num.length()+pre.length();
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(name+num+pre);

        stringBuilder.setSpan(new AbsoluteSizeSpan(19,true),0,len1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new AbsoluteSizeSpan(15,true),len1,len2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new AbsoluteSizeSpan(12,true),len2,len3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

    public void refreshList(List<QuotesModel> models){
        if (models==null){
            models=new ArrayList<>();
        }
        this.models.clear();
        this.models.addAll(models);
    }

    public void addList(List<QuotesModel> models){
        if (models==null){
            models=new ArrayList<>();
        }
        this.models.addAll(models);
    }

}
