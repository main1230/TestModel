package com.zzl.net

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:07
 * 描述：
 */
interface IHttpClient {
    open fun get(request: IRequest): IResponse

    open fun post(request: IRequest): IResponse

    open fun postJson(request: IRequest): IResponse
}