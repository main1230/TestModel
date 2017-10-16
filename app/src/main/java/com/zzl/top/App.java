package com.zzl.top;

import android.app.Application;

/**
 * Created by zhangzl
 * time: 2017/10/16 21:30.
 * 描述：
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
