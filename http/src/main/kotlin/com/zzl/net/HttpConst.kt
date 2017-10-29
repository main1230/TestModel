package com.zzl.net

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:28
 * 描述：
 */
object HttpConst {
    val POST = "POST"
    val GET = "GET"

    var DEBUG = true

    /**
     * 服务器正常返回值
     */
    val SERVER_OK = 1000
    /**
     * 服务器内部出错了
     */
    val SERVER_ERROR = 1001
    /**
     * 未知错误
     */
    val SERVER_UNKNOW_ERROR = 1002
}