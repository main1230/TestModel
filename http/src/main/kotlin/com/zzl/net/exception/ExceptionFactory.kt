package com.zzl.net.exception

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by zhangzl
 * 时间： 2017/10/28 20:52
 * 描述：
 */
class ExceptionFactory() {

    //解析错误
    val PARSE_ERROR = 1001
    //网络错误
    val NETWORK_ERROR = 1002
    //服务器出错
    val SERVER_ERROR = 1003
    //未知错误
    val UNKNOWN = 1004

    class ServerException(val code: Int, val msg: String) : RuntimeException()

    fun create(throwable: Throwable): ApiException {
        val apiException: ApiException
        if (throwable is ConnectException
                || throwable is SocketTimeoutException
                || throwable is UnknownHostException) {
            apiException = ApiException(throwable, NETWORK_ERROR, "网络连接错误")
        } else if (throwable is HttpException) {
            apiException = ApiException(throwable, SERVER_ERROR, "服务器出错: ${throwable.code()}")
        } else if (throwable is JsonParseException
                || throwable is JsonSyntaxException
                || throwable is JsonIOException
                || throwable is JSONException
                || throwable is ParseException) {
            apiException = ApiException(throwable, PARSE_ERROR, "Json 解析错误")

        } else if (throwable is ServerException) {
            apiException = ApiException(throwable, throwable.code, throwable.msg)

        } else {
            apiException = ApiException(throwable, UNKNOWN, "未知错误")
        }

        return apiException
    }
}