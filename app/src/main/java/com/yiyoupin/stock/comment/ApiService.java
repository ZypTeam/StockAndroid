package com.yiyoupin.stock.comment;

import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.model.QuotesModel;
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
}
