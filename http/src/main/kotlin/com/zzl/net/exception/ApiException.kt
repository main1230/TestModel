package com.zzl.net.exception

/**
 * Created by zhangzl
 * 时间： 2017/10/28 20:46
 * 描述：统一网络异常处理
 */

class ApiException(throwable: Throwable,
                   val errorCode: Int,
                   val errorMsg: String? = null) : Exception(throwable) {
    override fun toString(): String {
        return "ApiException(errorCode=$errorCode, errorMsg=$errorMsg)"
    }
}
