package com.zzl.net.impl

import com.zzl.net.IRequest
import com.zzl.net.HttpConst

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:08
 * 描述：
 */
class BaseRequest : IRequest {

    private var method = HttpConst.POST
    private var url: String
    private var params: MutableMap<String, Any>

    constructor(url: String) {
        this.url = url
        this.params = mutableMapOf()
    }

    override fun setMethod(method: String) {
        this.method = method
    }

    override fun addParam(params: MutableMap<String, Any>) {
        if (params == null) return
        this.params = params
    }

    override fun getUrl(): String {
        if (this.method == HttpConst.GET) {
            if (params != null && !params.isEmpty()) {
                val sb = StringBuilder()
                sb.append(url).append("?")
                for ((key, value) in params) {
                    sb.append(key).append("=").append(value).append("&")
                }
                return sb.toString()
            }
        }
        return this.url
    }

    override fun getParams(): MutableMap<String, Any> {
        return this.params
    }

}