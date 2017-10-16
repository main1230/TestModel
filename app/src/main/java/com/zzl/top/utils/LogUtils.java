package com.zzl.top.utils;

import com.orhanobut.logger.Logger;
import com.zzl.top.Const;

/**
 * Created by zhangzl
 * time: 2017/10/16 21:34.
 * 描述：
 */

public class LogUtils {
    public static void d() {
    }

    public static void v() {
    }

    public static void i(String msg, Object... args) {
        if (Const.DEBUG) {
            Logger.i(msg, args);
        }
    }

    public static void e(Throwable throwable, String msg, Object... args) {
        if (Const.DEBUG) {
            Logger.e(throwable, msg, args);
        }
    }
}
