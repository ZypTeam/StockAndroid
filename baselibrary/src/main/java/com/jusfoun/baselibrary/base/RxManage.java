package com.jusfoun.baselibrary.base;


import android.text.TextUtils;
import android.widget.Toast;

import com.jusfoun.baselibrary.BaseApplication;
import com.jusfoun.baselibrary.Util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author  wangchenchen
 * CreateDate 2016/7/6.
 * Email wcc@jusfoun.com
 * Description 管理rxjava 相关代码生命周期
 */
public class RxManage {

    public RxBus mRxbus=RxBus.getInstance();

    private Map<Object, Observable<?>> mObservables=new HashMap<>();

    private CompositeSubscription mCompositeSubscription=new CompositeSubscription();

    public void on(String eventName, Action1<Object> action1){
        Observable<?> mObservable=mRxbus.register(eventName);
        mObservables.put(eventName,mObservable);
        mCompositeSubscription.add(
                mObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(action1, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                LogUtil.e(getClass().getSimpleName(),throwable.getMessage());
                            }
                        })
        );
    }

    public void add(Subscription m){
        mCompositeSubscription.add(m);
    }

    /**
     * 添加网络请求
     * @param observable
     * @param action1
     * @param error
     */
    public <T extends BaseModel> void add(Observable<T> observable, final Action1<T> action1, final Action1<Throwable> error){
        mCompositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<T>() {
                            @Override
                            public void call(T t) {
                                if (t.getCode()!=0){
                                    Toast.makeText(BaseApplication.getBaseApplication(),t.getMsg(),Toast.LENGTH_LONG).show();
                                }
                                action1.call(t);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                LogUtil.e("throwable", throwable.getMessage());
                                if (error != null) {
                                    error.call(throwable);
                                }
                            }
                        })
        );
    }

    public void post(Object tag, Object content){
        mRxbus.post(tag, content);
    }

    public void post(Object event){
        mRxbus.post(event);
    }

    public void clear(){
        mCompositeSubscription.unsubscribe();
        for (Map.Entry<Object,Observable<?>> entry:mObservables.entrySet()){
            mRxbus.unRegister(entry.getKey(),entry.getValue());
        }
    }
}
