package com.yiyoupin.stock.comment;

import com.yiyoupin.stock.model.BuySelectionMoreModel;
import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.model.StrategiesMoreModel;
import com.yiyoupin.stock.model.TechnologyMoreModel;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.model.UserModel;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author wangcc
 * @date 2018/4/9
 * @describe
 */

public interface ApiService {

    /**
     * 龙虎榜 列表
     * @param params
     * @return
     */
    @GET("/stock/topcharts/list.do")
    Observable<ChartsListModel> chartsList(@QueryMap Map<String,String> params);

    /**
     * 登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/login/login_password.do")
    Observable<UserDataModel> login(@FieldMap Map<String,String> params);

    /**
     * 首页
     * @param params
     * @return
     */
    @GET("/stock/home/home.do")
    Observable<HomeModel> getHomeNet();


    /**
     * 选股策略更多
     * @param params
     * @return
     */
    @GET("/stock/securityselection/list.do")
    Observable<StrategiesMoreModel> getStrategiesNet(@QueryMap Map<String,String> params);

    /**
     * 买点精选更多
     * @param params
     * @return
     */
    @GET("/stock/buyselection/list.do")
    Observable<BuySelectionMoreModel> getBuyselectionNet(@QueryMap Map<String,String> params);



    /**
     * 选股策略更多
     * @param params
     * @return
     */
    @GET("/stock/home/technology/stock_list.do")
    Observable<TechnologyMoreModel> getTechnologyNet(@QueryMap Map<String,String> params);




}
