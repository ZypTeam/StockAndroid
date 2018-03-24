package com.yiyoupin.stock.delegate;

import com.google.gson.Gson;
import com.jusfoun.baselibrary.Util.SharePrefenceUtils;
import com.yiyoupin.stock.comment.SharePrefenceConstant;
import com.yiyoupin.stock.model.UserModel;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 用户信息
 */

public class UserInfoDelegate {

    private UserModel userModel;

    private static final class SingletonHolder{
        private static UserInfoDelegate delegate=new UserInfoDelegate();
    }

    private UserInfoDelegate(){
        String string = SharePrefenceUtils.getInstance().getString(SharePrefenceConstant.USER_MODEL);
        userModel = new Gson().fromJson(string, UserModel.class);
    }

    public static UserInfoDelegate getInstance(){
        return SingletonHolder.delegate;
    }

    public UserModel getUserInfo(){
        return userModel;
    }

    public void saveUserInfo(UserModel userModel){
        if (userModel == null) {
            return;
        }
        this.userModel=userModel;
        String string = new Gson().toJson(userModel);
        SharePrefenceUtils.getInstance().setString(SharePrefenceConstant.USER_MODEL, string);


    }

    public String getUserId() {
        String userId = null;
        if (userModel != null) {
            userId = userModel.getId();
        }
        return userId;
    }
}
