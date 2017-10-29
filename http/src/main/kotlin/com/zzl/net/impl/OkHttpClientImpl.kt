package com.zzl.net.impl

import com.alibaba.fastjson.JSON
import com.orhanobut.logger.Logger
import com.zzl.net.*
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:39
 * 描述：
 */
class OkHttpClientImpl : IHttpClient {
    val TAG = "OkHttpClientImpl"

    private var mOkHttp = OkHttpFactory.okhttp

    override fun get(request: IRequest): IResponse {
        request.setMethod(HttpConst.GET)
        var builder = Request.Builder()
        builder.url(request.getUrl()).get()

        return execute(builder.build())
    }

    override fun post(request: IRequest): IResponse {
        request.setMethod(HttpConst.POST)

        var builder = Request.Builder()
        //获取参数
        val formBuilder = FormBody.Builder()
        val params = request.getParams()
        for ((key, value) in params) {
            formBuilder.add(key, value.toString())
        }

        builder.url(request.getUrl()).post(formBuilder.build())
        return execute(builder.build())
    }

    override fun postJson(request: IRequest): IResponse {
        request.setMethod(HttpConst.POST)

        val mediaType = MediaType.parse("application/json; charset=utf-8")
        //添加参数
        val body = RequestBody.create(mediaType, JSON.toJSONString(request.getParams()))

        val builder = Request.Builder()
        builder.url(request.getUrl()).post(body)

        return execute(builder.build())
    }

    /**
     * 请求执行过程
     *
     * @param request
     * @return
     */
    private fun execute(request: Request): IResponse {
        val commonResponse = BaseResponse()
        try {
            val response = mOkHttp.newCall(request).execute()
            // 设置状态码
            commonResponse.setCode(response.code())
            val body = response.body()!!.string()
            if (HttpConst.DEBUG) Logger.i(TAG, "body:" + body)
            // 设置响应数据
            commonResponse.setData(body)
        } catch (e: Exception) {
            e.printStackTrace()
            commonResponse.setCode(HttpConst.SERVER_UNKNOW_ERROR)
            commonResponse.setData(e.message)
        }

        return commonResponse
    }
}