package com.yiyoupin.stock.delegate;

import android.net.Uri;
import android.util.Log;

import com.jusfoun.baselibrary.Util.ImageCompressUtil;
import com.jusfoun.baselibrary.net.Api;
import com.yiyoupin.stock.StockApplication;
import com.yiyoupin.stock.comment.ApiService;
import com.yiyoupin.stock.model.UploadDataModel;
import com.yiyoupin.stock.model.UploadModel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author wangcc
 * @date 2018/4/11
 * @describe
 */

public class ApiUploadFiles {

    public static final Observable<UploadDataModel> uploadFiles(String...files){
        final Map<String, RequestBody> map=new HashMap<>();
        //添加上传图片参数
        if (UserInfoDelegate.getInstance().getUserInfo()!=null) {
            map.put("userid", RequestBody.create(MediaType.parse("multipart/form-data"),
                    UserInfoDelegate.getInstance().getUserInfo() != null ? UserInfoDelegate.getInstance().getUserId() : ""));
        }
        final MultipartBody.Builder builder=new MultipartBody.Builder();
        return Observable.from(files)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //压缩图片
                        String imagePath= ImageCompressUtil.saveAndCompressPath(StockApplication.getBaseApplication(),s);
                        Uri uri=Uri.parse(imagePath);
                        File file=new File(uri.getPath());
                        RequestBody requestFile=RequestBody.create(MultipartBody.FORM,file);
                        builder.addFormDataPart("picture",file.getName(),requestFile);//将图片加入multipart
                        return imagePath;
                    }
                })
                .last()//最后调用一次上传接口
                .flatMap(new Func1<String, Observable<UploadDataModel>>() {
                    @Override
                    public Observable<UploadDataModel> call(String s) {
                        //上传图片
                        return Api.getInstance().getService(ApiService.class).uploadHead(map,builder.build());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 压缩上传
     * @param files
     * @return
     */
    public static final Observable<UploadDataModel> uploadFiles(List<String> files){
        final Map<String, RequestBody> map=new HashMap<>();
        //添加上传图片参数
        Log.e("filesize",files.size()+"");
        if (UserInfoDelegate.getInstance().getUserInfo()!=null) {
            map.put("userid", RequestBody.create(MediaType.parse("multipart/form-data"), UserInfoDelegate.getInstance().getUserId()));
        }
        final MultipartBody .Builder builder=new MultipartBody.Builder();
        return Observable.from(files)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //压缩图片
                        String imagePath=ImageCompressUtil.saveAndCompressPath(StockApplication.getBaseApplication(),s);
                        Uri uri=Uri.parse(imagePath);
                        File file=new File(uri.getPath());
                        RequestBody requestFile=RequestBody.create(MultipartBody.FORM,file);
                        builder.addFormDataPart("picture",file.getName(),requestFile);
                        Log.e("filepath",imagePath);
                        return imagePath;
                    }
                })
                .last()
                .flatMap(new Func1<String, Observable<UploadDataModel>>() {
                    @Override
                    public Observable<UploadDataModel> call(String s) {
                        //上传图片
                        return Api.getInstance().getService(ApiService.class).uploadHead(map,builder.build());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 不压缩直接上传
     * @param files
     * @return
     */
    public static final Observable<UploadDataModel> uploadFilesNoCompress(List<String> files){
        final Map<String, RequestBody> map=new HashMap<>();
        //添加上传图片参数
        Log.e("filesize",files.size()+"");
        if (UserInfoDelegate.getInstance().getUserInfo()!=null) {
            map.put("userid", RequestBody.create(MediaType.parse("multipart/form-data"), UserInfoDelegate.getInstance().getUserId()));
        }
        final MultipartBody .Builder builder=new MultipartBody.Builder();
        return Observable.from(files)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //压缩图片
                        File file=new File(s);
                        RequestBody requestFile=RequestBody.create(MultipartBody.FORM,file);
                        builder.addFormDataPart("picture",file.getName(),requestFile);
                        Log.e("filepath",s);
                        return s;
                    }
                })
                .last()
                .flatMap(new Func1<String, Observable<UploadDataModel>>() {
                    @Override
                    public Observable<UploadDataModel> call(String s) {
                        //上传图片
                        return Api.getInstance().getService(ApiService.class).uploadHead(map,builder.build());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * 不压缩直接上传
     * @param files
     * @return
     */
    public static final Observable<UploadDataModel> uploadFilesNoCompress(String...files){
        final Map<String, RequestBody> map=new HashMap<>();
        //添加上传图片参数
//        Log.e("filesize",files()+"");
        if (UserInfoDelegate.getInstance().getUserInfo()!=null) {
            map.put("userid", RequestBody.create(MediaType.parse("multipart/form-data"), UserInfoDelegate.getInstance().getUserId()));
        }
        final MultipartBody .Builder builder=new MultipartBody.Builder();
        return Observable.from(files)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //压缩图片
                        File file=new File(s);
                        RequestBody requestFile=RequestBody.create(MultipartBody.FORM,file);
                        builder.addFormDataPart("picture",file.getName(),requestFile);
                        Log.e("filepath",s);
                        return s;
                    }
                })
                .last()
                .flatMap(new Func1<String, Observable<UploadDataModel>>() {
                    @Override
                    public Observable<UploadDataModel> call(String s) {
                        //上传图片
                        return Api.getInstance().getService(ApiService.class).uploadHead(map,builder.build());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
