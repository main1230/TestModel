package com.zzl.net

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:05
 * 描述：
 */
interface IResponse {

    /**
     * 状态码
     */
    fun getCode(): Int

    /**
     * 数据体
     */
    fun getData(): String

    fun setCode(code: Int)

    fun setData(data: String?)
}