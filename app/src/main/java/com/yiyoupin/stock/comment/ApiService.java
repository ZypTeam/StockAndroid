package com.yiyoupin.stock.comment;

import com.guoziwei.klinelib.chart.FuTuModel;
import com.jusfoun.baselibrary.base.NoDataModel;
import com.yiyoupin.stock.model.AboutModel;
import com.yiyoupin.stock.model.BuySelectionMoreModel;
import com.yiyoupin.stock.model.ChartDetailModel;
import com.yiyoupin.stock.model.ChartsListModel;
import com.yiyoupin.stock.model.CheckVersionModel;
import com.yiyoupin.stock.model.FiveDayModel;
import com.yiyoupin.stock.model.KLineModel;
import com.yiyoupin.stock.model.MsgListModel;
import com.yiyoupin.stock.model.MsgModel;
import com.yiyoupin.stock.model.NewModel;
import com.yiyoupin.stock.model.NewsPaperListModel;
import com.yiyoupin.stock.model.NoticeListModel;
import com.yiyoupin.stock.model.PayListModel;
import com.yiyoupin.stock.model.QuotesListModel;
import com.yiyoupin.stock.model.ReplayListModel;
import com.yiyoupin.stock.model.SearchListModel;
import com.yiyoupin.stock.model.StockDetailModel;
import com.yiyoupin.stock.model.StrategiesDetailModel;
import com.yiyoupin.stock.model.UploadDataModel;
import com.yiyoupin.stock.model.ChartsModel;
import com.yiyoupin.stock.model.HomeModel;
import com.yiyoupin.stock.model.QuotesModel;
import com.yiyoupin.stock.model.StrategiesMoreModel;
import com.yiyoupin.stock.model.TechnologyMoreModel;
import com.yiyoupin.stock.model.UserDataModel;
import com.yiyoupin.stock.ui.HomeListModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author wangcc
 * @date 2018/4/9
 * @describe
 */

public interface ApiService {

    /**
     * 获取手机验证码
     * @param params
     * @return
     */
    @GET("/stock/login/phone_validate_code.do")
    Observable<NoDataModel> getPhoneCode(@QueryMap Map<String,String> params);

    /**
     * 登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/login/login_third.do")
    Observable<UserDataModel> thridLogin(@FieldMap Map<String,String> params);

    /**
     * 登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/login/login_password.do")
    Observable<UserDataModel> login(@FieldMap Map<String,String> params);

    /**
     * 注册
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/login/registered.do")
    Observable<UserDataModel> registered(@FieldMap Map<String,String> params);

    /**
     * 退出登录
     * @return
     */
    @POST("/stock/login/login_out.do")
    Observable<NoDataModel> loginOut();

    /**
     * 搜索
     * @param params
     * @return
     */
    @GET("stock/home/search.do")
    Observable<SearchListModel> searchList(@QueryMap Map<String,String> params);

    /**
     * 热门搜索
     * @param params
     * @return
     */
    @GET("stock/home/hot_lately_list.do")
    Observable<SearchListModel> hotSearchList(@QueryMap Map<String,String> params);

    /**
     * 添加热门搜索
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("stock/home/add_hot_stock.do")
    Observable<NoDataModel> addHotSearch(@FieldMap Map<String,String> params);

    /**
     * 早晚报列表
     * @param params
     * @return
     */
    @GET("/stock/daily/list.do")
    Observable<NewsPaperListModel> dailyList(@QueryMap Map<String,String> params);

    /**
     * 龙虎榜 列表
     * @param params
     * @return
     */
    @GET("/stock/topcharts/list.do")
    Observable<ChartsListModel> chartsList(@QueryMap Map<String,String> params);

    /**
     * 龙虎榜 详情
     * @param params
     * @return
     */
    @GET("/stock/topcharts/detail.do")
    Observable<ChartDetailModel> chartsDetail(@QueryMap Map<String,String> params);

    /**
     * 公告列表
     * @param params
     * @return
     */
    @GET("/stock/news/list.do")
    Observable<NoticeListModel> newsList(@QueryMap Map<String,String> params);

    /**
     * 复盘
     * @param params
     * @return
     */
    @GET("stock/checking/list.do")
    Observable<ReplayListModel> checkList(@QueryMap Map<String,String> params);

    /**
     * 行情列表
     * @param params
     * @return
     */
    @GET("/stock/market/info.do")
    Observable<QuotesListModel> quotesList(@QueryMap Map<String,String> params);

    /**
     * 自选列表
     * @param params
     * @return
     */
    @GET("/stock/optional/list.do")
    Observable<HomeListModel> optionalList(@QueryMap Map<String,String> params);

    /**
     * 缴费记录
     * @param params
     * @return
     */
    @GET("/stock/user/fee_records.do")
    Observable<PayListModel> feeRecords(@QueryMap Map<String,String> params);

    /**
     * 找回密码
     * @param params
     * @return
     */
    @POST("/stock/user/find_password.do")
    Observable<NoDataModel> findPassword(@QueryMap Map<String,String> params);

    /**
     * 绑定手机号
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/user/bind_phone.do")
    Observable<NoDataModel> bindPhone(@FieldMap Map<String,String> params);

    /**
     * 修改密码
     * @param params
     * @return
     */
    @POST("/stock/user/login_password_modify.do")
    Observable<NoDataModel> editPassword(@QueryMap Map<String,String> params);

    /**
     * 修改密码
     * @param params
     * @return
     */
    @GET("/stock/user/info.do")
    Observable<UserDataModel> getUserInfo(@QueryMap Map<String,String> params);

    /**
     * 上传头像
     * @param params
     * @return
     */
    @Multipart
    @POST("/stock/user/upload_user_picture.do")
    Observable<UploadDataModel> uploadHead(@PartMap Map<String, RequestBody> map, @Part("picture") MultipartBody body);

    /**
     * 修改信息
     * @param params
     * @return
     */
    @POST("/stock/user/modify_info.do")
    Observable<NoDataModel> editInfo(@QueryMap Map<String,String> params);

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
    @GET("/stock/technology/list.do")
    Observable<TechnologyMoreModel> getTechnologyNet(@QueryMap Map<String,String> params);


    /**
     * 策略详情
     * @param params
     * @return
     */
    @GET("/stock/buyselection/detail.do")
    Observable<StrategiesDetailModel> getStrategiesDetailNet(@QueryMap Map<String,String> params);


    /**
     * 技术形态股票列表
     * @param params
     * @return
     */
    @GET("/stock/technology/stock_list.do")
    Observable<HomeListModel> getJiShuXingtAINet(@QueryMap Map<String,String> params);

    /**
     * 添加自选
     * @param params
     * @return
     */
    @POST("/stock/optional/add_stock.do")
    Observable<HomeListModel> addZiXun(@QueryMap Map<String,String> params);

    /**
     * 添加自选
     * @param params
     * @return
     */
    @POST("/stock/optional/delete_stock.doo")
    Observable<HomeListModel> deleteZiXun(@QueryMap Map<String,String> params);

    /**
     * 股票详情新闻列表
     * @param params
     * @return
     */
    @GET("/stock/news_detail.do")
    Observable<NewModel> getNewsList(@QueryMap Map<String,String> params);



    /**
     * 股票详情
     * @param params
     * @return
     */
    @GET("/stock/detail.do")
    Observable<StockDetailModel> getStockDetails(@QueryMap Map<String,String> params);

    /**
     * 关于我们
     * @return
     */
    @GET("/stock/user/about.do")
    Observable<AboutModel> aboutUs();


    /**
     * 股票详情-五日分时图接口
     * @param params
     * @return
     */
    @GET("/stock/five-detail.do")
    Observable<FiveDayModel> getFiveDayDetialNet(@QueryMap Map<String,String> params);


    /**
     * 股票详情-K先图
     * @param params
     * @return
     */
    @GET("/stock/k-detail.do")
    Observable<KLineModel> getKLineDetialNet(@QueryMap Map<String,String> params);

    /**
     * 检查更新
     * @param params
     * @return
     */
    @POST("/stock/setting/checkUpdate.do")
    Observable<CheckVersionModel> checkVersion(@QueryMap Map<String,String> params);

    /**
     *  消息列表
     * @param params
     * @return
     */
    @GET("")
    Observable<MsgListModel> getMsgList(@QueryMap Map<String,String> params);


    /**
     * 设置提醒
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/optional/set_stock.do")
    Observable<UserDataModel> setRemindNet(@FieldMap Map<String,String> params);


    /**
     * 发送推送id
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/stock/user/setPushId.do")
    Observable<UserDataModel> sendPushId(@FieldMap Map<String,String> params);



    /**
     * 股票详情-K先图
     * @param params
     * @return
     */
    @GET("/stock/ftcl/detail.doo")
    Observable<UserDataModel> getFtNet();

}
