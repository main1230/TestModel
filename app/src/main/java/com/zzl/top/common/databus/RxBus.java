package com.zzl.top.common.databus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:07.
 * 描述：
 */

public class RxBus {
    private static final String TAG = "RxBus";

    private static volatile RxBus INSTANCE;

    /**
     * 订阅者集合
     */
    private Set<Object> subscribers;

    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    public synchronized void unreguster(Object subscriber) {
        subscribers.remove(subscriber);
    }

    private RxBus() {
        subscribers = new CopyOnWriteArraySet<>();
    }

    public static synchronized RxBus getDefault() {
        if (INSTANCE == null) {
            synchronized (RxBus.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxBus();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * 包装处理过程
     * @param function
     */
    public void chainProcess(Function function) {
        Flowable.just("")
                .subscribeOn(Schedulers.io())
                .map(function)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o == null) return;
                        sendData(o);
                    }
                });
    }

    /**
     * 发送数据
     * @param data
     */
    private void sendData(Object data) {
        for (Object subscriber : subscribers) {
            // 扫描注解，将数据发送到注册的对象的标记方法
            callMethodByAnnotiation(subscriber, data);
        }
    }

    /**
     * 反射获取对象方法列表，判断：
     * 1 是否被注解修饰
     * 2 参数类型是否和 data 类型一致
     * @param target
     * @param data
     */
    private void callMethodByAnnotiation(Object target, Object data) {
        Method[] methods = target.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(RegisterBus.class)) {
                try {
                    Class paramType = method.getParameterTypes()[0];
                    if (data.getClass().getName().equals(paramType.getName())) {
                        method.invoke(target, new Object[]{data});
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
