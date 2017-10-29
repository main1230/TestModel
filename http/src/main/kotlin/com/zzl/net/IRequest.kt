package com.zzl.net

/**
 * Created by zhangzl
 * 时间： 2017/10/29 13:59
 * 描述：
 */

interface IRequest {

    /**
     * 指定请求方式
     */
    open fun setMethod(method: String)

    /**
     * 指定请求参数
     * @param params
     */
    open fun addParam(params: MutableMap<String, Any>)

    /**
     * 提供给执行库请求行URL
     * @return
     */
    open fun getUrl(): String

    /**
     * 提供给执行库请求参数
     * @return
     */
    open fun getParams(): MutableMap<String, Any>
}