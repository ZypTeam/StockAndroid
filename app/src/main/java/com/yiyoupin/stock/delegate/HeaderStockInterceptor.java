package com.yiyoupin.stock.delegate;

import android.text.TextUtils;
import android.util.Log;

import com.jusfoun.baselibrary.Util.LogUtil;
import com.jusfoun.baselibrary.Util.StringUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class HeaderStockInterceptor implements Interceptor {
    private String USER_ID="user_id";
    private String user_id;

    public HeaderStockInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        user_id=UserInfoDelegate.getInstance().getUserId();
        if (StringUtil.isEmpty(user_id)){
            user_id="0";
        }
        LogUtil.e("HeaderStockInterceptor","user_id=="+user_id);
        Request request;
        request=chain.request().newBuilder()
                .addHeader(USER_ID, user_id)
                .build();

        return chain.proceed(request);
    }
}
